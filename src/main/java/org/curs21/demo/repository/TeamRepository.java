package org.curs21.demo.repository;

import org.curs21.demo.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    Optional<Team> findBySourceId(Integer id);

    Optional<Team> findByAbbreviation(String abbreviation);
}
