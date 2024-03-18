package org.curs24.securityApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.curs24.securityApp.model.Materie;

import java.util.Set;

@AllArgsConstructor
@Getter
public class TeacherSuccesResponseDTO {
    private long id;
    private String name;
    private Set<Materie> courses;
}
