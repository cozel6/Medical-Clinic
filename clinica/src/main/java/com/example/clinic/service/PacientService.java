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
        return programareService.create(pr);
    }

    public boolean anuleazaProgramare(Long idPacient, Long idProgramare) {
        Pacient p = read(idPacient);
        Programare pr = programareService.read(idProgramare);
        if (p != null && pr != null && p.getProgramari().remove(pr)) {
            programareService.delete(pr);
            return true;
        }
        return false;
    }
}
