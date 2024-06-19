package com.example.championship.controllers;

import com.example.championship.models.Championship;
import com.example.championship.models.Day;
import com.example.championship.repositories.ChampionshipRepository;
import com.example.championship.repositories.DayRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/days")
public class DayController {

    private final DayRepository dayRepository;
    private final ChampionshipRepository championshipRepository;

    @Autowired
    public DayController(DayRepository dayRepository, ChampionshipRepository championshipRepository) {
        this.dayRepository = dayRepository;
        this.championshipRepository = championshipRepository;
    }

    @GetMapping("/")
    public List<Day> getAllDays() {
        List<Day> days = dayRepository.findAll();
        if (days.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No days found");
        }
        return days;
    }

    @GetMapping("/championship/{championshipId}")
    public List<Day> getDaysByChampionshipId(@PathVariable Long championshipId) {
        List<Day> days = dayRepository.findByChampionshipId(championshipId);
        if (days.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No days found for championship with id: " + championshipId);
        }
        return days;
    }

    @GetMapping("/{id}")
    public Day getDayById(@PathVariable Long id) {
        return dayRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Day not found"));
    }

    @PostMapping("/championship/{championshipId}")
    public ResponseEntity<Day> createDay(@Valid @RequestBody Day day, @PathVariable Long championshipId,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }

        Championship championship = championshipRepository.findById(championshipId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Championship not found"));

        day.setChampionship(championship);
        Day savedDay = dayRepository.save(day);

        return new ResponseEntity<>(savedDay, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Day> updateDay(@PathVariable Long id, @Valid @RequestBody Day updatedDay,
            BindingResult bindingResult) {
        Day day = dayRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Day not found"));

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }

        day.setNumber(updatedDay.getNumber());

        Day savedDay = dayRepository.save(day);
        return new ResponseEntity<>(savedDay, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDay(@PathVariable Long id) {
        Day day = dayRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Day not found"));

        dayRepository.delete(day);
        return ResponseEntity.ok().build();
    }
}
