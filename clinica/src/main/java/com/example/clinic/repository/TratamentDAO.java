package com.example.clinic.repository;

import com.example.clinic.model.entitati.Tratament;

import jakarta.persistence.EntityManager;

public class TratamentDAO extends GenericDAO<Tratament, Long> {

    public TratamentDAO(EntityManager em) {
        super(Tratament.class, em);
    }
}
