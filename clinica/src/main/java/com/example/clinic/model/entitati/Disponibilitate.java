package com.example.clinic.model.entitati;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "disponibilitati")
public class Disponibilitate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek zi;

    private LocalTime oraInceput;
    private LocalTime oraSfarsit;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Disponibilitate() {}

    public Disponibilitate(Long id, DayOfWeek zi, LocalTime oraInceput, LocalTime oraSfarsit) {
        this.id = id;
        this.zi = zi;
        this.oraInceput = oraInceput;
        this.oraSfarsit = oraSfarsit;
    }

    // getteri și setteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public DayOfWeek getZi() { return zi; }
    public void setZi(DayOfWeek zi) { this.zi = zi; }

    public LocalTime getOraInceput() { return oraInceput; }
    public void setOraInceput(LocalTime oraInceput) { this.oraInceput = oraInceput; }

    public LocalTime getOraSfarsit() { return oraSfarsit; }
    public void setOraSfarsit(LocalTime oraSfarsit) { this.oraSfarsit = oraSfarsit; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
}