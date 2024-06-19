package com.example.championship.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Championship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le champ nom ne peut pas être null")
    @NotBlank(message = "Le champ nom ne peut pas être vide")
    private String name;

    @NotNull(message = "Le champ date de début ne peut pas être null")
    @Temporal(value = TemporalType.DATE)
    private LocalDate startDate;

    @NotNull(message = "Le champ date de fin ne peut pas être null")
    @Temporal(value = TemporalType.DATE)
    private LocalDate endDate;

    private int wonPoint;
    private int lostPoint;
    private int drawPoint;

    @OneToMany(mappedBy = "championship", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Day> days;

    public Championship() {
    }

    public Championship(String name, LocalDate startDate, LocalDate endDate, int wonPoint, int lostPoint,
            int drawPoint) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.wonPoint = wonPoint;
        this.lostPoint = lostPoint;
        this.drawPoint = drawPoint;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getWonPoint() {
        return wonPoint;
    }

    public void setWonPoint(int wonPoint) {
        this.wonPoint = wonPoint;
    }

    public int getLostPoint() {
        return lostPoint;
    }

    public void setLostPoint(int lostPoint) {
        this.lostPoint = lostPoint;
    }

    public int getDrawPoint() {
        return drawPoint;
    }

    public void setDrawPoint(int drawPoint) {
        this.drawPoint = drawPoint;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
