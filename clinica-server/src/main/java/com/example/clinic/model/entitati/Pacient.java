package com.example.clinic.model.entitati;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.clinic.model.abstracte.Persoana;
import com.example.clinic.model.interfete.IActiuniPacient;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pacienti")
public class Pacient extends Persoana implements IActiuniPacient {

    private LocalDate dataNasterii;
    private String alergii;
    @Lob
    private String istoricMedical;

    @OneToMany(mappedBy = "pacient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Programare> programari = new ArrayList<>();

    public Pacient() {}
    public Pacient(Long id, String nume, String prenume, String email,
                   LocalDate dataNasterii, String alergii, String istoricMedical) {
        setId(id); setNume(nume); setPrenume(prenume); setEmail(email);
        this.dataNasterii = dataNasterii;
        this.alergii = alergii;
        this.istoricMedical = istoricMedical;
    }

    // getteri și setteri

    public LocalDate getDataNasterii() { return dataNasterii; }
    public void setDataNasterii(LocalDate dataNasterii) { this.dataNasterii = dataNasterii; }
    public String getAlergii() { return alergii; }
    public void setAlergii(String alergii) { this.alergii = alergii; }
    public String getIstoricMedical() { return istoricMedical; }
    public void setIstoricMedical(String istoricMedical) { this.istoricMedical = istoricMedical; }
    public List<Programare> getProgramari() { return programari; }
    public void setProgramari(List<Programare> programari) { this.programari = programari; }

    @Override
    public Programare solicitaProgramare(Programare p) {
      p.setPacient(this);
      programari.add(p);
      return p;
    }
  
    @Override public List<Programare> vizualizeazaIstoric() { return programari; }
  
    @Override
    public boolean anuleazaProgramare(Programare p) {
      return programari.remove(p);
    }
}