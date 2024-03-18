package org.curs24.securityApp.controller;

import jakarta.validation.Valid;
import org.curs24.securityApp.DTO.AddressRequest;
import org.curs24.securityApp.DTO.StdRegRequest;
import org.curs24.securityApp.model.Adresa;
import org.curs24.securityApp.model.Materie;
import org.curs24.securityApp.model.Student;
import org.curs24.securityApp.model.StudentiToMaterie;
import org.curs24.securityApp.repository.MaterieRepository;
import org.curs24.securityApp.repository.StudentRepository;
import org.curs24.securityApp.repository.StudentiToMaterieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentRepository studentRepo;
    private final MaterieRepository materieRepo;
    private final StudentiToMaterieRepository stmRepo;

    @Autowired
    public StudentController(StudentRepository studentRepo,
                             MaterieRepository materieRepo,
                             StudentiToMaterieRepository stmRepo) {
        this.studentRepo = studentRepo;
        this.materieRepo = materieRepo;
        this.stmRepo = stmRepo;
    }

    @GetMapping(path = "/student/all")
    public @ResponseBody Iterable<Student> getStudents() {
        return studentRepo.findAll();
    }

    @GetMapping(path = "/student")
    public @ResponseBody Optional<Student> getStudentByNumeAndPrenume(
            @RequestParam(name = "nume") String nume,
            @RequestParam(name = "prenume", required = false) String prenume) {
        if (prenume == null) {
            return studentRepo.findByNume(nume);
        }
        return studentRepo.findByNumeAndPrenume(nume, prenume);
    }

    @GetMapping(path = "/student/localitate")
    public @ResponseBody List<Student> getStudentByNumeAndLocalitate(
            @RequestParam(name = "nume") String nume,
            @RequestParam(name = "localitate") String localitate) {
        return studentRepo.findByNumeAndLocalitate(nume, localitate);
    }

    @PostMapping(path = "/student/enroll")
    public ResponseEntity<StudentiToMaterie> enrollStudent(@RequestParam Integer studentId,
                                                           @RequestParam Integer materieId) {
        StudentiToMaterie stmRequest = new StudentiToMaterie();
        Optional<Student> studentOpt = studentRepo.findById(studentId);
        Optional<Materie> materieOpt = materieRepo.findById(materieId);
        if(studentOpt.isEmpty() || materieOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        stmRequest.setStudent(studentOpt.get());
        stmRequest.setMaterie(materieOpt.get());
        stmRepo.save(stmRequest);
        return new ResponseEntity<>(stmRequest, HttpStatus.CREATED);
    }

    @PostMapping(path = "/student")
    public ResponseEntity<Student> addStudent(@Valid @RequestBody StdRegRequest request) {
        Student toAdd = new Student();
        toAdd.setNume(request.getNume());
        toAdd.setPrenume(request.getPrenume());
        toAdd.setCnp(request.getCnp());
        toAdd.setAdresa(request.getAdresa());
        studentRepo.save(toAdd);
        return new ResponseEntity<>(toAdd, HttpStatus.CREATED);
    }

    @PutMapping(path = "student/{studentId}/adresa")
    public ResponseEntity<Adresa> updateAdresaStudent(@PathVariable Integer studentId,
                                                      @RequestBody AddressRequest request) {
        Optional<Student> studentOpt = studentRepo.findById(studentId);
        if(studentOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Adresa toUpdate = studentOpt.get().getAdresa();
        toUpdate.setStrada(request.getStrada());
        toUpdate.setNumar(request.getNumar());
        toUpdate.setLocalitate(request.getLocalitate());
        studentRepo.save(studentOpt.get());
        return new ResponseEntity<>(toUpdate, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        Optional<Student> studentOpt = studentRepo.findById(id);
        return studentOpt.map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/student/{id}")
    public ResponseEntity<Student> deleteStudentById(@PathVariable Integer id) {
        Optional<Student> studentOpt = studentRepo.findById(id);
        if(studentOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentRepo.deleteById(id);
        return new ResponseEntity<>(studentOpt.get(), HttpStatus.NO_CONTENT);
    }
}
