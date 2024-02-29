package org.curs21.demo.controller;

import jakarta.validation.Valid;
import org.curs21.demo.DTO.PlayerDTO;
import org.curs21.demo.DTO.TeamDTO;
import org.curs21.demo.entity.Player;
import org.curs21.demo.entity.Team;
import org.curs21.demo.error.ValidationError;
import org.curs21.demo.service.PlayerService;
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
public class PlayerController {

    private final PlayerService playerService;
    private final TeamService teamService;

    @Autowired
    public PlayerController(PlayerService playerService,
                            TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @GetMapping("/player/all")
    public ResponseEntity<List<Player>> allPlayers() {
        List<Player> players = playerService.getAllPlayers();
        if(players.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("player/{id}")
    public ResponseEntity<Player> playerById(@PathVariable Integer id) {
        Optional<Player> playerOpt = playerService.getPlayerById(id);
        return playerOpt.map(player -> new ResponseEntity<>(player, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/player/{id}")
    public ResponseEntity<Player> deletePlayerById(@PathVariable Integer id) {
        Optional<Player> toDelete = playerService.deletePlayerById(id);
        return toDelete.map(player -> new ResponseEntity<>(player, HttpStatus.NO_CONTENT))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PutMapping("/player/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Integer id,
                                             @Valid @RequestBody PlayerDTO playerDTO) {
        Optional<Player> playerOpt = playerService.getPlayerById(id);
        if(playerOpt.isEmpty()) {
            throw new ValidationError("Player not found!");
        }
        Player player = playerOpt.get();
        player.setFirstName(playerDTO.getFirstName());
        player.setLastName(playerDTO.getLastName());
        player.setPosition(playerDTO.getPosition());
        player.setHeight(playerDTO.getHeight());
        player.setWeight(playerDTO.getWeight());
        player.setJerseyNumber(playerDTO.getJerseyNumber());
        player.setCollege(playerDTO.getCollege());
        player.setCountry(playerDTO.getCountry());
        playerService.modifyPlayer(player);
        return new ResponseEntity<>(player, HttpStatus.ACCEPTED);
    }

    @PutMapping("/player/{id}/team")
    public ResponseEntity<Player> updateTeam(@PathVariable Integer id,
                                           @Valid @RequestBody TeamDTO teamDTO) {
        Optional<Player> playerOpt = playerService.getPlayerById(id);
        if(playerOpt.isEmpty()) {
            throw new ValidationError("Player not found!");
        }
        Player player = playerOpt.get();
        String abbreviation = teamDTO.getAbbreviation();
        Optional<Team> teamOpt = teamService.getTeamByAbbreviation(abbreviation);
        if(teamOpt.isEmpty()) {
            throw new ValidationError("Team not found! Please first add team into database!");
        }
        player.setTeam(teamOpt.get());
        playerService.modifyPlayer(player);
        return new ResponseEntity<>(player, HttpStatus.ACCEPTED);
    }

    @PostMapping("/player/add")
    public ResponseEntity<Player> addPlayer(@Valid @RequestBody PlayerDTO playerDTO) {
        String firstName = playerDTO.getFirstName();
        String lastName = playerDTO.getLastName();
        Optional<Player> playerOpt = playerService.getPlayerByFirstAndLastName(firstName, lastName);
        if(playerOpt.isPresent()) {
            throw new ValidationError("Player is already present!");
        }
        Player player = new Player();
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setPosition(playerDTO.getPosition());
        player.setHeight(playerDTO.getHeight());
        player.setWeight(playerDTO.getWeight());
        player.setJerseyNumber(playerDTO.getJerseyNumber());
        player.setCollege(playerDTO.getCollege());
        player.setCountry(playerDTO.getCountry());
        playerService.addPlayer(player);
        return new ResponseEntity<>(player, HttpStatus.CREATED);
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
    public Map<String, String> handleValidationExceptions(ValidationError error) {
        Map<String, String> errors = new HashMap<>();

        errors.put("Validation Error", error.getMessage());

        return errors;
    }
}
