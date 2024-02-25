package org.curs20.exercise.controller;

import org.curs20.exercise.entities.Materie;
import org.curs20.exercise.entities.Profesor;
import org.curs20.exercise.repository.MaterieRepository;
import org.curs20.exercise.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ProfesorController {

    ProfesorRepository profesorRepo;
    MaterieRepository materieRepo;

    @Autowired
    public ProfesorController(ProfesorRepository profesorRepo,
                              MaterieRepository materieRepo) {
        this.profesorRepo = profesorRepo;
        this.materieRepo = materieRepo;
    }

    @GetMapping("/profesor/{id}")
    public ResponseEntity<Profesor> getProfById(@PathVariable Integer id) {
        Optional<Profesor> optProf = profesorRepo.findById(id);
        return optProf.map(
                profesor -> new ResponseEntity<>(profesor, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("/profesor/materii/{id}")
    public ResponseEntity<List<Materie>> listMateriiByProfId(@PathVariable Integer id) {
        List<Materie> materii = materieRepo.findByProfesorId(id);
        if(materii.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(materii, HttpStatus.OK);
    }
}
