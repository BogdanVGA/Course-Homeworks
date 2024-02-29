package org.curs21.demo.repository;

import org.curs21.demo.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Optional<Player> findByFirstNameAndLastName(String firstName, String lastName);
}
