package com.example.clinic.repository;

import com.example.clinic.model.entitati.Disponibilitate;

import jakarta.persistence.EntityManager;

public class DisponibilitateDAO extends GenericDAO<Disponibilitate, Long> {

    public DisponibilitateDAO(EntityManager em) {
        super(Disponibilitate.class, em);
    }
}
