package com.example.clinic.service;


import java.util.List;

import com.example.clinic.model.entitati.Programare;
import com.example.clinic.repository.ProgramareDAO;
import com.example.clinic.util.HibernateUtil;

import jakarta.persistence.EntityManager;

public class ProgramareService {
    private final ProgramareDAO programareDAO;

    public ProgramareService() {
        EntityManager em = HibernateUtil.getEntityManager();
        programareDAO = new ProgramareDAO(em);
    }
    public Programare salveazaProgramare(Programare p){
        programareDAO.save(p);
        return p;
    }
    public List<Programare> listaProgramari(){
        return programareDAO.findAll();
    }
    public void stergeProgramare(Programare p){
        programareDAO.delete(p);
    }
}
