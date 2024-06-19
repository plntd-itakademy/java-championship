package com.example.championship.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Le champ name ne doit être null")
    @NotBlank(message = "Le champ name ne doit pas être vide")
    private String name;

    @NotNull(message = "Le champ creationDate ne doit être null")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @ManyToMany(mappedBy = "teams")
    private List<Championship> championships;

    @OneToMany(mappedBy = "team1", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Game> gamesAsTeam1;

    @OneToMany(mappedBy = "team2", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Game> gamesAsTeam2;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Championship> getChampionships() {
        return championships;
    }

    public void setChampionships(List<Championship> championships) {
        this.championships = championships;
    }

    public List<Game> getGamesAsTeam1() {
        return gamesAsTeam1;
    }

    public void setGamesAsTeam1(List<Game> gamesAsTeam1) {
        this.gamesAsTeam1 = gamesAsTeam1;
    }

    public List<Game> getGamesAsTeam2() {
        return gamesAsTeam2;
    }

    public void setGamesAsTeam2(List<Game> gamesAsTeam2) {
        this.gamesAsTeam2 = gamesAsTeam2;
    }
}
