package org.curs20.exercise.controller;

import jakarta.validation.Valid;
import org.curs20.exercise.DTO.AddNotaRequest;
import org.curs20.exercise.DTO.DateRangeRequest;
import org.curs20.exercise.entities.Materie;
import org.curs20.exercise.entities.Nota;
import org.curs20.exercise.entities.Student;
import org.curs20.exercise.error.ValidationError;
import org.curs20.exercise.repository.MaterieRepository;
import org.curs20.exercise.repository.NotaRepository;
import org.curs20.exercise.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class CatalogController {

    private final NotaRepository notaRepo;
    private final StudentRepository studentRepo;
    private final MaterieRepository materieRepo;

    @Autowired
    public CatalogController(NotaRepository notaRepo,
                             StudentRepository studentRepo,
                             MaterieRepository materieRepo) {
        this.notaRepo = notaRepo;
        this.studentRepo = studentRepo;
        this.materieRepo = materieRepo;
    }

    @GetMapping("student/{studentId}/note")
    public ResponseEntity<Set<Nota>> getNoteByStudent(@PathVariable Integer studentId) {
        Set<Nota> note = notaRepo.findByStudentId(studentId);
        if(note.isEmpty()) {
            throw  new ValidationError("Nu a fost găsită nicio notă pentru acest student.");
        }
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @PostMapping("student/{studentId}/note")
    public ResponseEntity<Nota> addNotaToStudentAtMaterie(@PathVariable Integer studentId,
                                                          @Valid @RequestBody AddNotaRequest request) {
        Optional<Student> studentOpt = studentRepo.findById(studentId);
        if(studentOpt.isEmpty()) {
            throw new ValidationError("Studentul nu a fost găsit.");
        }
        String numeMaterie = request.getMaterie();
        Optional<Materie> materieOpt = materieRepo.findByNume(numeMaterie);
        if(materieOpt.isEmpty()) {
            throw new ValidationError("Materia nu a fost găsită.");
        }
        Nota toAdd = new Nota();
        toAdd.setStudent(studentOpt.get());
        toAdd.setMaterie(materieOpt.get());
        toAdd.setNota(request.getNota());
        toAdd.setData(LocalDate.now());
        notaRepo.save(toAdd);
        return new ResponseEntity<>(toAdd, HttpStatus.ACCEPTED);
    }

    @GetMapping("student/{studentId}/note/{materieId}")
    public ResponseEntity<Set<Nota>> getNoteByStudentAndMaterie(@PathVariable int studentId,
                                                                @PathVariable int materieId) {
        Set<Nota> note = notaRepo.findByStudentIdAndMaterieId(studentId, materieId);
        if(note.isEmpty()) {
            throw new ValidationError("Nu a fost găsită nicio notă pentru această pereche student - materie.");
        }
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @GetMapping("student/{studentId}/note/byDate")
    public ResponseEntity<Set<Nota>> getNoteByStudentBetweenDates(@PathVariable int studentId,
                                                                  @RequestBody DateRangeRequest request) {
        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = LocalDate.parse(request.getStartDate());
            endDate = LocalDate.parse(request.getEndDate());
        } catch (DateTimeParseException exception) {
            throw new ValidationError("Format data invalid: " + exception.getParsedString() + ".");
        }

        Optional<Student> studentOpt = studentRepo.findById(studentId);
        if(studentOpt.isEmpty()) {
            throw new ValidationError("Studentul nu a fost găsit.");
        }
        Set<Nota> note = notaRepo.findNoteByStudentBetweenDate(studentId, startDate, endDate);
        if(note.isEmpty()) {
            throw new ValidationError("Studentul nu are note in intervalul solicitat.");
        }
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationError.class)
    public Map<String, String> handleValidationException(ValidationError error) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Validation Error", error.getMessage());
        return errors;
    }
}
