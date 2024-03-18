package org.curs24.securityApp.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.curs24.securityApp.DTO.LoginRequestDTO;
import org.curs24.securityApp.DTO.LoginResponseDTO;
import org.curs24.securityApp.DTO.RegisterRequestDTO;
import org.curs24.securityApp.model.User;
import org.curs24.securityApp.repository.UserRepository;
import org.curs24.securityApp.security.Role;
import org.curs24.securityApp.security.UserDetailsImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Locale;

@Slf4j
@Service
public class UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JWTService jwtHelper;

    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();
        System.out.println("User details: " + userDetails.toString());
        ResponseCookie jwtCookie = jwtHelper.generateJwtCookie(userDetails);
        System.out.println("jwtCookie: " + jwtCookie.toString());

        String role = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList().get(0);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new LoginResponseDTO(userDetails.getEmail(), jwtCookie.getValue(), userDetails.getId(), role));
    }

    @Transactional
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        if (userRepository.existsByEmail(registerRequestDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Email used");
        }

        String role = registerRequestDTO.getRole();
        // TODO: add more sanity checks (email, password, etc.)

        if (role == null
                || !(role.toUpperCase(Locale.ROOT).equals("USER")
                || role.toUpperCase(Locale.ROOT).equals("ADMIN")
                || role.toUpperCase(Locale.ROOT).equals("STAFF"))) {
            throw new RuntimeException("Invalid role");
        }

        User user = new User(null, registerRequestDTO.getEmail(),
                encoder.encode(registerRequestDTO.getPassword()), Role.valueOf(role));

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }
}
