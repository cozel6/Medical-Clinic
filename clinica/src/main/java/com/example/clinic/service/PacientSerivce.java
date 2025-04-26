package com.example.clinic.service;

import java.util.List;

import com.example.clinic.model.entitati.Pacient;
import com.example.clinic.model.entitati.Programare;
import com.example.clinic.repository.PacientDAO;
import com.example.clinic.repository.ProgramareDAO;
import com.example.clinic.util.HibernateUtil;

import jakarta.persistence.EntityManager;

public class PacientSerivce {
    private final PacientDAO pacientDAO;
    private final ProgramareDAO programareDAO;

    public PacientSerivce() {
        EntityManager em = HibernateUtil.getEntityManager();
        pacientDAO = new PacientDAO(em);
        programareDAO = new ProgramareDAO(em);
    }
    public Pacient inregistreazaPacient(Pacient p){
        pacientDAO.save(p);
        return p;
    }
    public List<Pacient> listaPacienti(){
        return pacientDAO.findAll();
    }
    public Pacient gasestePacient(Long id){
        return pacientDAO.find(id);
    }
    public Programare solicitaProgamare(Long idPacient , Programare pr){
        Pacient p = gasestePacient(idPacient);
        pr.setPacient(p);
        programareDAO.save(pr);
        return pr;
    }
    public boolean anuleazaProgramarea(Long idPacient, Long idProgramare){
        Pacient p = gasestePacient(idPacient);
        Programare pr = programareDAO.find(idProgramare);
        if(p != null && pr  != null && p.getProgramari().remove(pr)){
            programareDAO.delete(pr);
            return true;
        }
        return false;
    }
     
}
