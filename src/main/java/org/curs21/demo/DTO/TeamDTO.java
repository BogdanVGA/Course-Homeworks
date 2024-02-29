package org.curs21.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class TeamDTO {

    @NotBlank(message = "conference must not be blank")
    private String conference;

    @NotBlank(message = "division must not be blank")
    private String division;

    @NotBlank(message = "city must not be blank")
    private String city;

    @NotBlank(message = "name must not be blank")
    private String name;

    @NotBlank(message = "full name must not be blank")
    private String fullName;

    @NotBlank(message = "abbreviation must not be blank")
    @Length(min = 3, max = 3, message = "abbreviation consists of three letters")
    private String abbreviation;
}
