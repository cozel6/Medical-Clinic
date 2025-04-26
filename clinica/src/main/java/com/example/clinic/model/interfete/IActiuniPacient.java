package com.example.clinic.model.interfete;

import java.util.List;

import com.example.clinic.model.entitati.Programare;

public interface  IActiuniPacient {
    Programare solicitaProgramare(Programare p);
    List<Programare> vizualizeazaIstoric();
    }

