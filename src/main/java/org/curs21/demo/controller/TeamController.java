package org.curs21.demo.controller;

import jakarta.validation.Valid;
import org.curs21.demo.DTO.TeamDTO;
import org.curs21.demo.entity.Team;
import org.curs21.demo.error.ValidationError;
import org.curs21.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/team/all")
    public ResponseEntity<List<Team>> allTeams() {
        List<Team> teams = teamService.getAllTeams();
        if(teams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<Team> teamById(@PathVariable Integer id) {
        Optional<Team> teamOptional = teamService.getTeamById(id);
        return teamOptional.map(team -> new ResponseEntity<>(team, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/team/add")
    public ResponseEntity<Team> addTeam(@Valid @RequestBody TeamDTO teamDTO) {
        String teamAbbreviation = teamDTO.getAbbreviation();
        Optional<Team> teamOptional = teamService.getTeamByAbbreviation(teamAbbreviation);
        if(teamOptional.isPresent()) {
            throw new ValidationError("This team is already present!");
        }
        Team team = new Team();
        team.setConference(teamDTO.getConference());
        team.setDivision(teamDTO.getDivision());
        team.setCity(teamDTO.getCity());
        team.setName(teamDTO.getName());
        team.setFullName(teamDTO.getFullName());
        team.setAbbreviation(teamAbbreviation);
        teamService.addTeam(team);
        return new ResponseEntity<>(team, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationError.class)
    public Map<String, String> handleValidationsExceptions(ValidationError error) {
        Map<String, String> errors = new HashMap<>();

        errors.put("Validation Error", error.getMessage());

        return errors;
    }
}
