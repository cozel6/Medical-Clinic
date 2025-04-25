package com.example.clinic.model.entitati;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctori")
public class Doctor extends com.example.clinic.model.abstracte.Persoana {

    private String specializare;
    private boolean necesitaRecomandare;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Programare> programari = new ArrayList<>();

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Disponibilitate> disponibilitati = new ArrayList<>();

    public Doctor() {}
    public Doctor(Long id, String nume, String prenume, String email,
                  String specializare, boolean necesitaRecomandare) {
        setId(id); setNume(nume); setPrenume(prenume); setEmail(email);
        this.specializare = specializare;
        this.necesitaRecomandare = necesitaRecomandare;
    }

    // getteri și setteri
    public String getSpecializare() { return specializare; }
    public void setSpecializare(String specializare) { this.specializare = specializare; }

    public boolean isNecesitaRecomandare() { return necesitaRecomandare; }
    public void setNecesitaRecomandare(boolean necesitaRecomandare) { this.necesitaRecomandare = necesitaRecomandare; }

    public List<Programare> getProgramari() { return programari; }
    public void setProgramari(List<Programare> programari) { this.programari = programari; }

    public List<Disponibilitate> getDisponibilitati() { return disponibilitati; }
    public void setDisponibilitati(List<Disponibilitate> disponibilitati) { this.disponibilitati = disponibilitati; }
}