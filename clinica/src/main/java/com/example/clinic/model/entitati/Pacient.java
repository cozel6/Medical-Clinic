package com.example.clinic.model.entitati;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pacienti")
public class Pacient extends com.example.clinic.model.abstracte.Persoana {

    private LocalDate dataNasterii;
    private String alergii;
    @Lob
    private String istoricMedical;

    @OneToMany(mappedBy = "pacient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Programare> programari = new ArrayList<>();

    // Constructor gol și complete
    public Pacient() {}
    public Pacient(Long id, String nume, String prenume, String email,
                   LocalDate dataNasterii, String alergii, String istoricMedical) {
        setId(id); setNume(nume); setPrenume(prenume); setEmail(email);
        this.dataNasterii = dataNasterii;
        this.alergii = alergii;
        this.istoricMedical = istoricMedical;
    }

    // Getteri/Setteri
    public LocalDate getDataNasterii() { return dataNasterii; }
    public void setDataNasterii(LocalDate dataNasterii) { this.dataNasterii = dataNasterii; }
    public String getAlergii() { return alergii; }
    public void setAlergii(String alergii) { this.alergii = alergii; }
    public String getIstoricMedical() { return istoricMedical; }
    public void setIstoricMedical(String istoricMedical) { this.istoricMedical = istoricMedical; }
    public List<Programare> getProgramari() { return programari; }
    public void setProgramari(List<Programare> programari) { this.programari = programari; }
}