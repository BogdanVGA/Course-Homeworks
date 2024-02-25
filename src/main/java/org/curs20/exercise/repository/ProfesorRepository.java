package org.curs20.exercise.repository;

import org.curs20.exercise.entities.Materie;
import org.curs20.exercise.entities.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
}
