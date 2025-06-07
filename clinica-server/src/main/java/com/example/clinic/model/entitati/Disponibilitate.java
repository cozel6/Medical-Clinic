package com.example.clinic.model.entitati;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
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

    /*Removed
    --@Enumerated(EnumType.STRING)
    --private DayOfWeek zi;
     */


    private LocalDate data;

    private LocalTime oraInceput;
    private LocalTime oraSfarsit;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Disponibilitate() {}

    public Disponibilitate(Long id, LocalTime oraInceput, LocalTime oraSfarsit, LocalDate data) {
        this.id = id;
        this.oraInceput = oraInceput;
        this.oraSfarsit = oraSfarsit;
        this.data = data;
    }

    // getteri și setteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    /*
    public DayOfWeek getZi() { return zi; }
    public void setZi(DayOfWeek zi) { this.zi = zi; } 
    */
    
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public LocalTime getOraInceput() { return oraInceput; }
    public void setOraInceput(LocalTime oraInceput) { this.oraInceput = oraInceput; }

    public LocalTime getOraSfarsit() { return oraSfarsit; }
    public void setOraSfarsit(LocalTime oraSfarsit) { this.oraSfarsit = oraSfarsit; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
}