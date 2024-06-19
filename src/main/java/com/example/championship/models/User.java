package com.example.championship.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Le champ firstName ne doit être null")
    @NotBlank(message = "Le champ firstName ne doit pas être vide")
    private String firstName;

    @NotNull(message = "Le champ lastName ne doit être null")
    @NotBlank(message = "Le champ lastName ne doit pas être vide")
    private String lastName;

    @NotNull(message = "Le champ email ne doit être null")
    @NotBlank(message = "Le champ email ne doit pas être vide")
    @Email(message = "Le champ email doit être valide")
    private String email;

    @NotNull(message = "Le champ password ne doit être null")
    @NotBlank(message = "Le champ password ne doit pas être vide")
    private String password;

    @NotNull(message = "Le champ creationDate ne doit être null")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, Date creationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creationDate = creationDate;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
