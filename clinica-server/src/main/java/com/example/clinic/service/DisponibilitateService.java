package com.example.clinic.service;

import java.util.List;

import com.example.clinic.model.entitati.Disponibilitate;
import com.example.clinic.util.HibernateUtil;

public class DisponibilitateService extends GenericService<Disponibilitate, Long> {

    public DisponibilitateService() {
        super(Disponibilitate.class, HibernateUtil.getEntityManager());
    }

    public Disponibilitate salveazaDisponibilitate(Disponibilitate d) {
        return create(d);
    }

    public List<Disponibilitate> listaDisponibilitati() {
        return readAll();
    }
    public List<Disponibilitate> listaDisponibilitatiByDoctor(Long idDoctor) {
        return listaDisponibilitati().stream()
            .filter(d -> d.getDoctor() != null && d.getDoctor().getId().equals(idDoctor))
            .toList();
    }
}
