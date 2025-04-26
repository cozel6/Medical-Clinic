package com.example.clinic.service;

import java.util.List;

import com.example.clinic.model.entitati.Disponibilitate;
import com.example.clinic.repository.DisponibilitateDAO;
import com.example.clinic.util.HibernateUtil;

import jakarta.persistence.EntityManager;

public class DisponibilitateService {

    private final DisponibilitateDAO disponibilitateDAO;

    public DisponibilitateService() {
        EntityManager em = HibernateUtil.getEntityManager();
        disponibilitateDAO = new DisponibilitateDAO(em);
    }
    public Disponibilitate salveazaDisponibilitate(Disponibilitate d){
        disponibilitateDAO.save(d);
        return d;
    }
    public List<Disponibilitate> listaDisponibilitati(){
        return  disponibilitateDAO.findAll();
    }
}
