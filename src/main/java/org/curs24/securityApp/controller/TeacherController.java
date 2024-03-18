package org.curs24.securityApp.controller;

import jakarta.transaction.Transactional;
import org.curs24.securityApp.DTO.TeacherRequestDTO;
import org.curs24.securityApp.DTO.TeacherSuccesResponseDTO;
import org.curs24.securityApp.model.Materie;
import org.curs24.securityApp.model.Profesor;
import org.curs24.securityApp.repository.MaterieRepository;
import org.curs24.securityApp.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/teacher")
//@PreAuthorize("hasAnyAuthority('ADMIN')")
public class TeacherController {

    private final ProfesorRepository profesorRepository;
    private final MaterieRepository materieRepository;

    @Autowired
    public TeacherController(ProfesorRepository profesorRepository,
                             MaterieRepository materieRepository) {
        this.profesorRepository = profesorRepository;
        this.materieRepository = materieRepository;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    ResponseEntity<List<TeacherSuccesResponseDTO>> getTeachers() {
        Iterable<Profesor> teachers = profesorRepository.findAll();

        List<TeacherSuccesResponseDTO> response = new ArrayList<>();
        for (Profesor teacher : teachers) {
            response.add(new TeacherSuccesResponseDTO(teacher.getId(), teacher.getNume(), teacher.getMateriiPredate()));
        }

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/add")
    @Transactional
    public @ResponseBody
    ResponseEntity<TeacherSuccesResponseDTO> addTeacher(@RequestBody TeacherRequestDTO teacher) {

        Profesor profesor = new Profesor();
        profesor.setNume(teacher.getTeacherName());
        profesor = profesorRepository.save(profesor);

        Materie materie = new Materie();
        materie.setNume(teacher.getCourseName());
        materie.setProfesor(profesor);
        materieRepository.save(materie);

        return new ResponseEntity<>(new TeacherSuccesResponseDTO(profesor.getId(),
                profesor.getNume(),
                Set.of(materie)),
                HttpStatus.CREATED);
    }
}
