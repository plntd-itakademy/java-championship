package com.example.championship.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le champ points de l'équipe 1 ne peut pas être null")
    private int team1Point;

    @NotNull(message = "Le champ points de l'équipe 2 ne peut pas être null")
    private int team2Point;

    @NotNull(message = "Le champ ID de l'équipe 1 ne peut pas être null")
    private int idTeam1;

    @NotNull(message = "Le champ ID de l'équipe 2 ne peut pas être null")
    private int idTeam2;

    @NotNull(message = "Le champ ID du jour ne peut pas être null")
    private int idDay;

    public Game() {
    }

    public Game(int team1Point, int team2Point, int idTeam1, int idTeam2, int idDay) {
        this.team1Point = team1Point;
        this.team2Point = team2Point;
        this.idTeam1 = idTeam1;
        this.idTeam2 = idTeam2;
        this.idDay = idDay;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTeam1Point() {
        return team1Point;
    }

    public void setTeam1Point(int team1Point) {
        this.team1Point = team1Point;
    }

    public int getTeam2Point() {
        return team2Point;
    }

    public void setTeam2Point(int team2Point) {
        this.team2Point = team2Point;
    }

    public int getIdTeam1() {
        return idTeam1;
    }

    public void setIdTeam1(int idTeam1) {
        this.idTeam1 = idTeam1;
    }

    public int getIdTeam2() {
        return idTeam2;
    }

    public void setIdTeam2(int idTeam2) {
        this.idTeam2 = idTeam2;
    }

    public int getIdDay() {
        return idDay;
    }

    public void setIdDay(int idDay) {
        this.idDay = idDay;
    }
}
