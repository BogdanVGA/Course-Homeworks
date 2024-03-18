package org.curs24.securityApp.DTO;

import lombok.Data;
import org.curs24.securityApp.model.Materie;

import java.util.Set;


@Data
public class StudentSuccessResponseDTO {
    private final Integer id;
    private final String nume;
    private final String prenume;
    private final Set<Materie> cursuriAlese;
}
