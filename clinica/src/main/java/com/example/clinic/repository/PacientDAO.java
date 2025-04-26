package com.example.clinic.repository;

import com.example.clinic.model.entitati.Pacient;

import jakarta.persistence.EntityManager;

public class PacientDAO extends GenericDAO<Pacient, Long> {
    public PacientDAO(EntityManager em) { super(Pacient.class, em); }
}