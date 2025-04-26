package com.example.clinic.service;

import java.time.LocalDate;
import java.util.List;

import com.example.clinic.model.entitati.Doctor;
import com.example.clinic.model.entitati.Programare;
import com.example.clinic.model.entitati.Tratament;
import com.example.clinic.repository.DoctorDAO;
import com.example.clinic.repository.ProgramareDAO;
import com.example.clinic.repository.TratamentDAO;
import com.example.clinic.util.HibernateUtil;

import jakarta.persistence.EntityManager;

public class DoctorService {

    private final DoctorDAO doctorDAO;
    private final ProgramareDAO programareDAO;
    private final TratamentDAO tratamentDAO;

    public DoctorService() {
        EntityManager em = HibernateUtil.getEntityManager();
        doctorDAO = new DoctorDAO(em);
        programareDAO = new ProgramareDAO(em);
        tratamentDAO = new TratamentDAO(em);

    }

    public Doctor inregistreazaDoctor(Doctor d) {
        doctorDAO.save(d);
        return d;
    }

    public List<Doctor> listaDoctori() {
        return doctorDAO.findAll();
    }

    public Doctor gasesteDoctor(Long id) {
        return doctorDAO.find(id);
    }

    public Programare puneDiagnostic(Long idProgamare, String diagnostic) {
        Programare pr = programareDAO.find(idProgamare);
        if (pr != null) {
            pr.setDiagnostic(diagnostic);
            programareDAO.update(pr);
            return pr;
        }
        return null;
    }

    public Tratament prescrieTratament(Long idProgramare, String descriere) {
        Programare pr = programareDAO.find(idProgramare);
        if (pr == null) {
            return null;
        }
        Tratament t = new Tratament();
        t.setDescriere(descriere);
        t.setDataPrescriptie(java.time.LocalDateTime.now());
        t.setProgramare(pr);
        tratamentDAO.save(t);
        return t;

    }

    public List<Programare> filtrazaProgramareByDate(Long idDoctor, LocalDate data) {
        Doctor d = gasesteDoctor(idDoctor);
        return d.getProgramari().stream().filter(pr -> pr.getData().toLocalDate().equals(data)).toList();
    }
}
