package org.curs24.securityApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class TeacherRequestDTO {
    private String teacherName;
    private String courseName;
}
