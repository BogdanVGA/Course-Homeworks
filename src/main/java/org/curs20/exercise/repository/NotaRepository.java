package org.curs20.exercise.repository;

import org.curs20.exercise.entities.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Set;

public interface NotaRepository extends JpaRepository<Nota, Long> {

    Set<Nota> findByStudentId(int studentId);

    @Query("SELECT n FROM Nota n JOIN n.student s JOIN n.materie m WHERE s.id = ?1 AND m.id = ?2")
    Set<Nota> findByStudentIdAndMaterieId(int studentId, int materieId);

    @Query("SELECT n FROM Nota n WHERE n.student.id = ?1 AND n.data BETWEEN ?2 AND ?3")
    Set<Nota> findNoteByStudentBetweenDate(Integer studentId, LocalDate startDate, LocalDate endDate);
}
