package com.example.clinic.repository;

import com.example.clinic.model.entitati.Doctor;

import jakarta.persistence.EntityManager;

public class DoctorDAO extends GenericDAO<Doctor, Long> {

    public DoctorDAO(EntityManager em) {
        super(Doctor.class, em);
    }
}
