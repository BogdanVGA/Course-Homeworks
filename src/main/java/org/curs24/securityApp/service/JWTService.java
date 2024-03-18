package org.curs24.securityApp.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.curs24.securityApp.model.User;
import org.curs24.securityApp.security.UserDetailsImplementation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class JWTService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.accessTokenValidityMS}")
    private int jwtExpirationMs;

    @Value("${jwt.cookieName}")
    private String jwtCookie;

    public ResponseCookie generateJwtCookie(UserDetailsImplementation userPrincipal) {
        String jwt = generateTokenForUser(userPrincipal.getUser());
        System.out.println("JWT: " + jwt);
        return ResponseCookie.from(jwtCookie, jwt).maxAge(60 * 60).httpOnly(true).build();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(authToken);
            return true;
        } catch (Exception e) {
            System.out.println("JWTService.class -> validateJwtToken error: " + e.getMessage());
        }
        return false;
    }

    public String generateTokenForUser(User user) {
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(secretKey).compact();
    }

    public String getEmailFromToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        String email = Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token).getPayload().get("email", String.class);
        System.out.println("getEmailFromToken: " + email);
        return email;
    }
}

/* https://www.baeldung.com/java-json-web-tokens-jjwt */