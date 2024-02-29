package org.curs21.demo.config;

import org.curs21.demo.entity.Player;
import org.curs21.demo.entity.Team;
import org.curs21.demo.repository.PlayerRepository;
import org.curs21.demo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StartupRunner implements ApplicationRunner {

    private final JsonPlayerList jsonPlayers;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public StartupRunner(JsonPlayerList jsonPlayers,
                         PlayerRepository playerRepository,
                         TeamRepository teamRepository) {
        this.jsonPlayers = jsonPlayers;
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Loading data into database...");

        List<JsonPlayer> jsonPlayerList = jsonPlayers.getData();
        Set<Team> teams = new HashSet<>();

        for(JsonPlayer jsonPlayer : jsonPlayerList) {
            JsonTeam jsonTeam = jsonPlayer.getTeam();
            teams.add(getTeam(jsonTeam));
        }
        teamRepository.saveAll(teams);

        for(JsonPlayer jsonPlayer : jsonPlayerList) {
            Integer teamSourceId = jsonPlayer.getTeam().getId();
            Optional<Team> teamOpt = teamRepository.findBySourceId(teamSourceId);
            if(teamOpt.isEmpty()) {
                throw new Exception("Team not found!");
            }
            Player crtPlayer = getPlayer(jsonPlayer);
            crtPlayer.setTeam(teamOpt.get());
            playerRepository.save(crtPlayer);
        }

        jsonPlayerList.clear();
        System.out.println("Loading complete!");
    }

    private static Player getPlayer(JsonPlayer jsonPlayer) {
        Player crtPlayer = new Player();
        crtPlayer.setFirstName(jsonPlayer.getFirst_name());
        crtPlayer.setLastName(jsonPlayer.getLast_name());
        crtPlayer.setCollege(jsonPlayer.getCollege());
        crtPlayer.setCountry(jsonPlayer.getCountry());
        crtPlayer.setHeight(jsonPlayer.getHeight());
        crtPlayer.setWeight(jsonPlayer.getWeight());
        crtPlayer.setPosition(jsonPlayer.getPosition());
        crtPlayer.setJerseyNumber(jsonPlayer.getJersey_number());
        return crtPlayer;
    }

    private static Team getTeam(JsonTeam jsonTeam) {
        Team crtTeam = new Team();
        crtTeam.setAbbreviation(jsonTeam.getAbbreviation());
        crtTeam.setCity(jsonTeam.getCity());
        crtTeam.setConference(jsonTeam.getConference());
        crtTeam.setName(jsonTeam.getName());
        crtTeam.setFullName(jsonTeam.getFull_name());
        crtTeam.setDivision(jsonTeam.getDivision());
        crtTeam.setSourceId(jsonTeam.getId());
        return crtTeam;
    }
}
