package org.curs20.exercise.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.curs20.exercise.entities.Adresa;
import org.hibernate.validator.constraints.Length;

@Data
public class StdRegRequest {

    @NotNull(message = "Numele nu poate fi nul.")
    @NotBlank(message = "Numele nu poate fi fără niciun caracter.")
    private String nume;

    @NotNull(message = "Prenumele nu poate fi nul.")
    @NotBlank(message = "Prenumele nu poate fi fără niciun caracter.")
    private String prenume;

    @NotNull(message = "CNP nu poate fi nul.")
    @NotBlank(message = "CNP nu poate fi fără niciun caracter.")
    @Length(min = 4, max = 4)
    private String cnp;

    private Adresa adresa;
}
