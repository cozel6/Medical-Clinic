package com.example.clinic.model.entitati;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pacienti")
public class Pacient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nume;
    private String prenume;
    private String email;
    private String dataNasterii;
    private String alergii;
    
    @Lob
    private String istoricMedical;
    
    @OneToMany(mappedBy = "pacient", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Programare> programari = new ArrayList<>();

    public Pacient() {
    }

    public Pacient(String nume, String prenume, String email, String dataNasterii, String alergii, String istoricMedical) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.dataNasterii = dataNasterii;
        this.alergii = alergii;
        this.istoricMedical = istoricMedical;
    }
    // Getteri și setteri

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNume() {return nume;}
    public void setNume(String nume) {this.nume = nume;}
    
    public String getPrenume() {return prenume;}
    public void setPrenume(String prenume) {this.prenume = prenume;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getDataNasterii() {return dataNasterii;}
    public void setDataNasterii(String dataNasterii) {this.dataNasterii = dataNasterii;}

    public String getAlergii() {return alergii;}
    public void setAlergii(String alergii) {this.alergii = alergii;}

    public String getIstoricMedical() {return istoricMedical;}
    public void setIstoricMedical(String istoricMedical) {this.istoricMedical = istoricMedical;}

    public List<Programare> getProgramari() {return programari;}
    public void setProgramari(List<Programare> programari) {this.programari = programari;}
}
