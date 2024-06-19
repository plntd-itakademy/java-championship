package com.example.championship.controllers;

import com.example.championship.models.Game;
import com.example.championship.models.Day;
import com.example.championship.repositories.GameRepository;
import com.example.championship.repositories.DayRepository;
import com.example.championship.repositories.ChampionshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameRepository gameRepository;
    private final DayRepository dayRepository;

    @Autowired
    public GameController(GameRepository gameRepository, DayRepository dayRepository,
            ChampionshipRepository championshipRepository) {
        this.gameRepository = gameRepository;
        this.dayRepository = dayRepository;
    }

    @GetMapping("/")
    public List<Game> getAllGames() {
        List<Game> games = gameRepository.findAll();
        if (games.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No games found");
        }
        return games;
    }

    @GetMapping("/championship/{championshipId}")
    public List<Game> getGamesByChampionshipId(@PathVariable Long championshipId) {
        List<Game> games = gameRepository.findByDay_ChampionshipId(championshipId);
        if (games.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No games found for championship with id: " + championshipId);
        }
        return games;
    }

    @GetMapping("/day/{dayId}")
    public List<Game> getGamesByDayId(@PathVariable Long dayId) {
        List<Game> games = gameRepository.findByDayId(dayId);
        if (games.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No games found for day with id: " + dayId);
        }
        return games;
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
    }

    @PostMapping("/day/{dayId}")
    public ResponseEntity<Game> createGame(@Valid @RequestBody Game game, @PathVariable Long dayId,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }

        Day day = dayRepository.findById(dayId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Day not found"));

        game.setDay(day);
        Game savedGame = gameRepository.save(game);

        return new ResponseEntity<>(savedGame, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @Valid @RequestBody Game updatedGame,
            BindingResult bindingResult) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }

        game.setTeam1(updatedGame.getTeam1());
        game.setTeam2(updatedGame.getTeam2());
        game.setTeam1Point(updatedGame.getTeam1Point());
        game.setTeam2Point(updatedGame.getTeam2Point());
        game.setDay(updatedGame.getDay());

        Game savedGame = gameRepository.save(game);
        return new ResponseEntity<>(savedGame, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));

        gameRepository.delete(game);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }
}
