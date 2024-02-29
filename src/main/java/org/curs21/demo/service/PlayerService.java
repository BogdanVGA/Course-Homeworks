package org.curs21.demo.service;

import org.curs21.demo.entity.Player;
import org.curs21.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PlayerService {

    private final PlayerRepository playerRepo;

    @Autowired
    public PlayerService(PlayerRepository playerRepo) {
        this.playerRepo = playerRepo;
    }

    public List<Player> getAllPlayers() {
        return playerRepo.findAll();
    }

    public Optional<Player> getPlayerById(Integer id) {
        return playerRepo.findById(id);
    }

    public Optional<Player> getPlayerByFirstAndLastName(String firstName, String lastName) {
        return playerRepo.findByFirstNameAndLastName(firstName, lastName);
    }

    public Optional<Player> deletePlayerById(Integer id) {
        Optional<Player> toDeleteOpt = playerRepo.findById(id);
        if(toDeleteOpt.isEmpty()) {
            return Optional.empty();
        }
        Player toDelete = toDeleteOpt.get();
        Set<Player> teamPlayers = toDelete.getTeam().getPlayers();
        teamPlayers.remove(toDelete);
        playerRepo.deleteById(id);
        return toDeleteOpt;
    }

    public void addPlayer(Player player) {
        playerRepo.save(player);
    }

    public void modifyPlayer(Player player) {
        playerRepo.save(player);
    }
}
