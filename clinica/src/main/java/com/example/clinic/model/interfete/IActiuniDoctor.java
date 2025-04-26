package com.example.clinic.model.interfete;

import java.time.LocalDate;
import java.util.List;

import com.example.clinic.model.entitati.Programare;
import com.example.clinic.model.entitati.Tratament;

public interface IActiuniDoctor {
     Programare puneDiagnostic(Programare p, String diagnostic);
     Tratament prescrieTratament(Programare p, String descriere);
     List<Programare> filtreazaProgramariByData(LocalDate data);
}