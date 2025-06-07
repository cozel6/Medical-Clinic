package com.example.clinic.service;

import java.util.List;

import com.example.clinic.model.entitati.Tratament;
import com.example.clinic.util.HibernateUtil;

public class TratamentService extends GenericService<Tratament, Long> {

    public TratamentService() {
        super(Tratament.class, HibernateUtil.getEntityManager());
    }

    public Tratament salveazaTratament(Tratament t) {
        return create(t);
    }

    public List<Tratament> listaTratamente() {
        return readAll();
    }

    public void stergeTratament(Tratament t) {
        delete(t);
    }
}
