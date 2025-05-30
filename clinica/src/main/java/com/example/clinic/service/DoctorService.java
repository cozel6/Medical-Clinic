package com.example.clinic.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.clinic.model.entitati.Doctor;
import com.example.clinic.model.entitati.Programare;
import com.example.clinic.model.entitati.Tratament;
import com.example.clinic.util.HibernateUtil;

public class DoctorService extends GenericService<Doctor, Long> {
    private final ProgramareService programareService;
    private final TratamentService tratamentService;

    public DoctorService() {
        super(Doctor.class, HibernateUtil.getEntityManager());
        this.programareService = new ProgramareService();
        this.tratamentService   = new TratamentService();
    }

    public Doctor inregistreazaDoctor(Doctor d) {
        return create(d);
    }

    public List<Doctor> listaDoctori() {
        return readAll();
    }

    public Doctor gasesteDoctor(Long id) {
        return read(id);
    }

    public Programare puneDiagnostic(Long idProgramare, String diagnostic) {
        Programare pr = programareService.read(idProgramare);
        if (pr != null) {
            pr.setDiagnostic(diagnostic);
            return programareService.update(pr);
        }
        return null;
    }

    public Tratament prescrieTratament(Long idProgramare, String descriere) {
        Programare pr = programareService.read(idProgramare);
        if (pr == null) return null;
        Tratament t = new Tratament();
        t.setDescriere(descriere);
        t.setDataPrescriptie(LocalDateTime.now());
        t.setProgramare(pr);
        return tratamentService.create(t);
    }

    public List<Programare> filtreazaProgramariByData(Long idDoctor, LocalDate data) {
        Doctor d = read(idDoctor);
        return d.getProgramari()
                .stream()
                .filter(pr -> pr.getData().toLocalDate().equals(data))
                .toList();
    }
}
