package org.curs20.exercise.repository;

import org.curs20.exercise.entities.Materie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaterieRepository extends JpaRepository<Materie, Integer> {

    List<Materie> findByProfesorId(Integer profId);

    Optional<Materie> findByNume(String nume);
}

// https://www.bezkoder.com/jpa-manytoone/