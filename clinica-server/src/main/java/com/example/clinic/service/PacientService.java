package com.example.clinic.service;

import java.util.List;

import com.example.clinic.model.entitati.Pacient;
import com.example.clinic.model.entitati.Programare;
import com.example.clinic.util.HibernateUtil;

public class PacientService extends GenericService<Pacient, Long> {

    private final ProgramareService programareService;

    public PacientService() {
        super(Pacient.class, HibernateUtil.getEntityManager());
        this.programareService = new ProgramareService();
    }

    public Pacient inregistreazaPacient(Pacient p) {
        return create(p);
    }

    public List<Pacient> listaPacienti() {
        return readAll();
    }

    public Pacient gasestePacient(Long id) {
        return read(id);
    }

    public Programare solicitaProgramare(Long idPacient, Programare pr) {
        Pacient p = read(idPacient);
        pr.setPacient(p);
        p.getProgramari().add(pr);
        return programareService.create(pr);
    }

    public boolean anuleazaProgramare(Long idPacient, Long idProgramare) {
        Programare pr = programareService.read(idProgramare);
        if (pr == null) {
            return false;
        }
        if (pr.getPacient() == null || !pr.getPacient().getId().equals(idPacient)) {
            return false;
        }
        programareService.delete(pr);
        return true;
    }

}
