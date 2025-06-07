package com.example.clinic.service;

import java.util.List;

import com.example.clinic.model.entitati.Programare;
import com.example.clinic.util.HibernateUtil;

public class ProgramareService extends GenericService<Programare, Long> {

    public ProgramareService() {
        super(Programare.class, HibernateUtil.getEntityManager());
    }

    public Programare salveazaProgramare(Programare p) {
        return create(p);
    }

    public List<Programare> listaProgramari() {
        return readAll();
    }

    public void stergeProgramare(Programare p) {
        delete(p);
    }
}
