package org.curs21.demo.service;

import org.curs21.demo.entity.Team;
import org.curs21.demo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepo;

    @Autowired
    public TeamService(TeamRepository teamRepo) {
        this.teamRepo = teamRepo;
    }

    public List<Team> getAllTeams() {
        return teamRepo.findAll();
    }

    public Optional<Team> getTeamById(Integer id) {
        return teamRepo.findById(id);
    }

    public void addTeam(Team team) {
        teamRepo.save(team);
    }

    public Optional<Team> getTeamByAbbreviation(String abbreviation) {
        return teamRepo.findByAbbreviation(abbreviation);
    }
}
