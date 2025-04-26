package com.example.clinic.service;


import java.util.List;

import com.example.clinic.model.entitati.Programare;
import com.example.clinic.repository.ProgramareDAO;
import com.example.clinic.util.HibernateUtil;

import jakarta.persistence.EntityManager;

public class ProgamareSerivce {
    private final ProgramareDAO programareDAO;

    public ProgamareSerivce() {
        EntityManager em = HibernateUtil.getEntityManager();
        programareDAO = new ProgramareDAO(em);
    }
    public Programare salveazProgramare(Programare p){
        programareDAO.save(p);
        return p;
    }
    public List<Programare> listaProgramari(){
        return programareDAO.findAll();
    }
    public void stergeProgamare(Programare p){
        programareDAO.delete(p);
    }
}
