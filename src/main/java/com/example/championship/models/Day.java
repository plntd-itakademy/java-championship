package com.example.championship.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le champ numéro ne peut pas être null")
    @NotBlank(message = "Le champ numéro ne peut pas être vide")
    private String number;

    @NotNull(message = "Le champ ID de championnat ne peut pas être null")
    private int idChampionship;

    @OneToMany(mappedBy = "day", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Game> games;

    public Day() {
    }

    public Day(String number, int idChampionship) {
        this.number = number;
        this.idChampionship = idChampionship;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getIdChampionship() {
        return idChampionship;
    }

    public void setIdChampionship(int idChampionship) {
        this.idChampionship = idChampionship;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
