package com.example.championship.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int team1Point;
    private int team2Point;

    @ManyToOne
    @JoinColumn(name = "id_team1", nullable = false)
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "id_team2", nullable = false)
    private Team team2;

    @ManyToOne
    @JoinColumn(name = "id_day", nullable = false)
    private Day day;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
