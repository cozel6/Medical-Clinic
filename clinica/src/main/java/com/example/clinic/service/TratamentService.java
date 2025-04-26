package com.example.clinic.service;

import java.util.List;

import com.example.clinic.model.entitati.Tratament;
import com.example.clinic.repository.TratamentDAO;
import com.example.clinic.util.HibernateUtil;

import jakarta.persistence.EntityManager;

public class TratamentService {
    private final TratamentDAO tratamentDAO;

    public TratamentService() {
        EntityManager em = HibernateUtil.getEntityManager();
        tratamentDAO = new TratamentDAO(em);
    }
    public Tratament salveazaTratament(Tratament t){
        tratamentDAO.save(t);
        return t;
    }
    public List<Tratament> listaTratamente(){
        return  tratamentDAO.findAll();
    }
    public void stergeTratament(Tratament t){
        tratamentDAO.delete(t);
    }
}
