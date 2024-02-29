package org.curs21.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlayerDTO {

    @NotBlank(message = "first name must not be empty")
    private String firstName;

    @NotBlank(message = "last name name must not be empty")
    private String lastName;

    @NotBlank(message = "position must not be empty")
    private String position;

    @NotBlank(message = "height must not be empty")
    private String height;

    @NotBlank(message = "weight name must not be empty")
    private String weight;

    @NotBlank(message = "jersey number must not be empty")
    private String jerseyNumber;

    @NotBlank(message = "college must not be empty")
    private String college;

    @NotBlank(message = "country must not be empty")
    private String country;
}
