package com.example.championship.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le champ nom ne peut pas être null")
    @NotBlank(message = "Le champ nom ne peut pas être vide")
    private String name;

    @NotNull(message = "Le champ date de création ne peut pas être null")
    @Temporal(value = TemporalType.DATE)
    private LocalDate creationDate;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TeamChampionship> teamChampionships;

    public Team() {
    }

    public Team(String name, LocalDate creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<TeamChampionship> getTeamChampionships() {
        return teamChampionships;
    }

    public void setTeamChampionships(List<TeamChampionship> teamChampionships) {
        this.teamChampionships = teamChampionships;
    }
}
