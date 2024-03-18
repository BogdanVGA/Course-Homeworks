package org.curs24.securityApp.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {
    private String email;
    private String role;
    private String password;
}