package org.curs20.exercise.DTO;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class AddNotaRequest {

    private String materie;

    @Range(min = 1, max = 10, message = "Nota trebuie sa fie intre 1 si 10")
    private Integer nota;
}
