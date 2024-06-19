package com.example.championship.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class TeamChampionship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le champ ID de championnat ne peut pas être null")
    private int idChampionship;

    @NotNull(message = "Le champ ID de l'équipe ne peut pas être null")
    private int idTeam;

    public TeamChampionship() {
    }

    public TeamChampionship(int idChampionship, int idTeam) {
        this.idChampionship = idChampionship;
        this.idTeam = idTeam;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdChampionship() {
        return idChampionship;
    }

    public void setIdChampionship(int idChampionship) {
        this.idChampionship = idChampionship;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }
}
