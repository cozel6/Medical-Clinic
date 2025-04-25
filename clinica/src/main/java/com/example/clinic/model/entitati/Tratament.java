package com.example.clinic.model.entitati;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tratamente")
public class Tratament {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descriere;
    private LocalDateTime dataPrescriptie;

    @ManyToOne @JoinColumn(name = "programare_id")
    private Programare programare;

    public Tratament() {}
    public Tratament(Long id, String descriere, LocalDateTime dataPrescriptie) {
        this.id = id; this.descriere = descriere; this.dataPrescriptie = dataPrescriptie;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescriere() { return descriere; }
    public void setDescriere(String descriere) { this.descriere = descriere; }
    public LocalDateTime getDataPrescriptie() { return dataPrescriptie; }
    public void setDataPrescriptie(LocalDateTime d) { this.dataPrescriptie = d; }
    public Programare getProgramare() { return programare; }
    public void setProgramare(Programare p) { this.programare = p; }
}