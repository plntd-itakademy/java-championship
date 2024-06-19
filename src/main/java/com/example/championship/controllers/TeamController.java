package com.example.championship.controllers;

import com.example.championship.models.Championship;
import com.example.championship.models.Team;
import com.example.championship.repositories.ChampionshipRepository;
import com.example.championship.repositories.TeamRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamRepository teamRepository;
    private final ChampionshipRepository championshipRepository;

    @Autowired
    public TeamController(TeamRepository teamRepository, ChampionshipRepository championshipRepository) {
        this.teamRepository = teamRepository;
        this.championshipRepository = championshipRepository;
    }

    @GetMapping("/")
    public List<Team> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        if (teams.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No teams found");
        }
        return teams;
    }

    @GetMapping("/championship/{championshipId}")
    public List<Team> getTeamsByChampionshipId(@PathVariable Long championshipId) {
        List<Team> teams = teamRepository.findByChampionships_Id(championshipId);
        if (teams.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No teams found for championship with id: " + championshipId);
        }
        return teams;
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));
    }

    @PostMapping("/")
    public ResponseEntity<Team> createTeam(@Valid @RequestBody Team team, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }
        Team savedTeam = teamRepository.save(team);
        return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @Valid @RequestBody Team updatedTeam,
            BindingResult bindingResult) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }

        team.setName(updatedTeam.getName());
        team.setCreationDate(updatedTeam.getCreationDate());

        Team savedTeam = teamRepository.save(team);
        return new ResponseEntity<>(savedTeam, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));

        teamRepository.delete(team);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{teamId}/championship/{championshipId}")
    public ResponseEntity<Team> addTeamToChampionship(@PathVariable Long teamId, @PathVariable Long championshipId) {
        Championship championship = championshipRepository.findById(championshipId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Championship not found"));

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));

        // Add team to championship
        List<Team> currentTeams = championship.getTeams();
        currentTeams.add(team);

        championship.setTeams(currentTeams);
        championshipRepository.save(championship);

        return new ResponseEntity<>(team, HttpStatus.OK);
    }
}
