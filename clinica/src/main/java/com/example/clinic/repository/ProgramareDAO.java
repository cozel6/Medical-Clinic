package com.example.clinic.repository;

import com.example.clinic.model.entitati.Programare;

import jakarta.persistence.EntityManager;

public class ProgramareDAO extends GenericDAO<Programare, Long> {
    public ProgramareDAO(EntityManager em) { super(Programare.class, em); }
}