package com.example.championship.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le champ prénom ne peut pas être null")
    @NotBlank(message = "Le champ prénom ne peut pas être vide")
    private String firstName;

    @NotNull(message = "Le champ nom ne peut pas être null")
    @NotBlank(message = "Le champ nom ne peut pas être vide")
    private String lastName;

    @NotNull(message = "Le champ email ne peut pas être null")
    @NotBlank(message = "Le champ email ne peut pas être vide")
    @Email(message = "L'email doit être valide")
    private String email;

    @NotNull(message = "Le champ mot de passe ne peut pas être null")
    @NotBlank(message = "Le champ mot de passe ne peut pas être vide")
    private String password;

    @NotNull(message = "Le champ ID de l'équipe ne peut pas être null")
    private String teamId;

    @NotNull(message = "Le champ date de création ne peut pas être null")
    @Temporal(value = TemporalType.DATE)
    private LocalDate creationDate;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, String teamId,
            LocalDate creationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.teamId = teamId;
        this.creationDate = creationDate;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
