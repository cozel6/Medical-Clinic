package com.example.clinic.model.entitati;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "programari")
public class Programare {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime data;
    private boolean recomandareFamilie;
    private String diagnostic;

    @ManyToOne @JoinColumn(name = "pacient_id")
    private Pacient pacient;

    @ManyToOne @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToMany(mappedBy = "programare", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tratament> tratamente = new ArrayList<>();

    public Programare() {}
    public Programare(Long id, LocalDateTime data, boolean recomandareFamilie, String diagnostic) {
        this.id = id; this.data = data;
        this.recomandareFamilie = recomandareFamilie;
        this.diagnostic = diagnostic;
    }

    // getteri si setteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }
    public boolean isRecomandareFamilie() { return recomandareFamilie; }
    public void setRecomandareFamilie(boolean rf) { this.recomandareFamilie = rf; }
    public String getDiagnostic() { return diagnostic; }
    public void setDiagnostic(String diagnostic) { this.diagnostic = diagnostic; }
    public Pacient getPacient() { return pacient; }
    public void setPacient(Pacient pacient) { this.pacient = pacient; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public List<Tratament> getTratamente() { return tratamente; }
    public void setTratamente(List<Tratament> tratamente) { this.tratamente = tratamente; }
}

