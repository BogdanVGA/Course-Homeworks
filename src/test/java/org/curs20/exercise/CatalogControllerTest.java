package org.curs20.exercise;

import org.curs20.exercise.DTO.AddNotaRequest;
import org.curs20.exercise.controller.CatalogController;
import org.curs20.exercise.entities.Materie;
import org.curs20.exercise.entities.Nota;
import org.curs20.exercise.entities.Student;
import org.curs20.exercise.repository.MaterieRepository;
import org.curs20.exercise.repository.NotaRepository;
import org.curs20.exercise.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CatalogControllerTest {

    @Mock
    private NotaRepository notaRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private MaterieRepository materieRepository;

    @InjectMocks
    private CatalogController catalogController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetNoteByStudent() {
        int studentId = 1;
        Set<Nota> mockNoteSet = Collections.singleton(new Nota());

        when(notaRepository.findByStudentId(studentId)).thenReturn(mockNoteSet);

        ResponseEntity<Set<Nota>> responseEntity = catalogController.getNoteByStudent(studentId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockNoteSet, responseEntity.getBody());
    }

    @Test
    public void testAddNotaToStudentAtMaterie() {
        int studentId = 1;
        AddNotaRequest addNotaRequest = new AddNotaRequest();
        addNotaRequest.setMaterie("Matematica 2");
        addNotaRequest.setNota(9);

        Optional<Student> mockStudent = Optional.of(new Student());
        Optional<Materie> mockMaterie = Optional.of(new Materie());
        Nota mockNota = new Nota();
        mockNota.setStudent(mockStudent.get());
        mockNota.setMaterie(mockMaterie.get());
        mockNota.setNota(addNotaRequest.getNota());
        mockNota.setData(LocalDate.now());

        when(studentRepository.findById(studentId)).thenReturn(mockStudent);
        when(materieRepository.findByNume("Matematica 2")).thenReturn(mockMaterie);
        when(notaRepository.save(any(Nota.class))).thenReturn(mockNota);

        ResponseEntity<Nota> responseEntity = catalogController.addNotaToStudentAtMaterie(studentId, addNotaRequest);

        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals(mockNota, responseEntity.getBody());
    }
}
