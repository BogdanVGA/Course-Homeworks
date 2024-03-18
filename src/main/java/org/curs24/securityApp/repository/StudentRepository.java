package org.curs24.securityApp.repository;

import org.curs24.securityApp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByNume(String nume);

    Optional<Student> findByNumeAndPrenume(String nume, String prenume);

    @Query("SELECT s FROM Student s WHERE s.nume = ?1")
    List<Student> findByNumeUsingQuery(String nume);

    @Query(value = "SELECT * FROM studenti " +
            "JOIN adrese_studenti on studenti.id_adresa = adrese_studenti.id " +
            "WHERE " +
            "studenti.nume = ?1 " +
            "and adrese_studenti.localitate = ?2",
            nativeQuery = true)
    List<Student> findByNumeAndLocalitate(String nume, String localitate);

    @Query(value = "SELECT * FROM studenti  WHERE studenti.nume = ?1", nativeQuery = true)
    List<Optional<Student>> findByNumeSpecial2(String nume);
}
