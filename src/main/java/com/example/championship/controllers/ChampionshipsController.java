package com.example.championship.controllers;

import com.example.championship.models.Championship;
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
@RequestMapping("/championships")
public class ChampionshipsController {

    private final ChampionshipRepository championshipRepository;

    @Autowired
    public ChampionshipsController(ChampionshipRepository championshipRepository) {
        this.championshipRepository = championshipRepository;
    }

    @GetMapping("/")
    public List<Championship> getAllChampionships() {
        List<Championship> championships = championshipRepository.findAll();
        if (championships.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No championships found");
        }
        return championships;
    }

    @GetMapping("/{id}")
    public Championship getChampionshipById(@PathVariable Long id) {
        return championshipRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Championship not found"));
    }

    @PostMapping("/")
    public ResponseEntity<Championship> createChampionship(@Valid @RequestBody Championship championship,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }
        Championship savedChampionship = championshipRepository.save(championship);
        return new ResponseEntity<>(savedChampionship, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Championship> updateChampionship(@PathVariable Long id,
            @Valid @RequestBody Championship updatedChampionship, BindingResult bindingResult) {
        Championship championship = championshipRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Championship not found"));

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }

        championship.setName(updatedChampionship.getName());
        championship.setStartDate(updatedChampionship.getStartDate());
        championship.setEndDate(updatedChampionship.getEndDate());

        Championship savedChampionship = championshipRepository.save(championship);
        return new ResponseEntity<>(savedChampionship, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Championship> deleteChampionship(@PathVariable Long id) {
        Championship championship = championshipRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Championship not found"));

        championshipRepository.delete(championship);
        return new ResponseEntity<>(championship, HttpStatus.OK);
    }
}
