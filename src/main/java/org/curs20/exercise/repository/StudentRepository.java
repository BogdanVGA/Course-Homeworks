package org.curs20.exercise.repository;

import org.curs20.exercise.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByNume(String nume);

    Optional<Student> findByNumeAndPrenume(String nume, String prenume);

    @Query("SELECT s FROM Student s WHERE s.nume = ?1")
    List<Student> findByNumeUsingQuery(String nume);

    @Query("SELECT s FROM Student s WHERE s.nume = ?1 AND s.adresa.localitate = ?2")
    List<Student> findByNumeAndLocalitate(String nume, String localitate);
}
