package com.example.clinic.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.clinic.model.entitati.Disponibilitate;
import com.example.clinic.model.entitati.Doctor;
import com.example.clinic.model.entitati.Pacient;
import com.example.clinic.model.entitati.Programare;
import com.example.clinic.model.entitati.Tratament;
import com.example.clinic.service.DisponibilitateService;
import com.example.clinic.service.DoctorService;
import com.example.clinic.service.PacientService;
import com.example.clinic.service.ProgramareService;
import com.example.clinic.service.TratamentService;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final PacientService pacientService = new PacientService();
    private final DoctorService doctorService = new DoctorService();
    private final ProgramareService programareService = new ProgramareService();
    private final TratamentService tratamentService = new TratamentService();
    private final DisponibilitateService disponibilitateService = new DisponibilitateService();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] t = line.split("\\|");
                switch (t[0]) {
                    case "REGISTER_PACIENT" -> {
                        Pacient p = new Pacient();
                        p.setNume(t[1]);
                        p.setPrenume(t[2]);
                        p.setEmail(t[3]);
                        p.setTelefon(t[4]);
                        p.setDataNasterii(LocalDate.parse(t[5]));
                        pacientService.inregistreazaPacient(p);
                        out.println("OK|" + p.getId());
                    }
                    case "REGISTER_DOCTOR" -> {
                        Doctor d = new Doctor();
                        d.setNume(t[1]);
                        d.setPrenume(t[2]);
                        d.setEmail(t[3]);
                        d.setSpecializare(t[4]);
                        d.setNecesitaRecomandare(Boolean.parseBoolean(t[5]));
                        doctorService.inregistreazaDoctor(d);
                        out.println("OK|" + d.getId());
                    }
                    case "CREATE_APPOINTMENT" -> {
                        Long idPac = Long.valueOf(t[1]), idDoc = Long.valueOf(t[2]);
                        Pacient pac = pacientService.gasestePacient(idPac);
                        Doctor doc = doctorService.gasesteDoctor(idDoc);
                        if (pac == null || doc == null) {
                            out.println("ERROR|Pacient sau doctor inexistent");
                            break;
                        }
                        Programare pr = new Programare();
                        pr.setPacient(pac);
                        pr.setDoctor(doc);
                        pr.setData(LocalDateTime.parse(t[3]));
                        pr.setRecomandareFamilie(Boolean.parseBoolean(t[4]));
                        pacientService.solicitaProgramare(pac.getId(), pr);
                        out.println("OK|" + pr.getId());
                    }
                    case "CANCEL_APPOINTMENT" -> {
                        Long idPac = Long.valueOf(t[1]), idPr = Long.valueOf(t[2]);
                        boolean ok = pacientService.anuleazaProgramare(idPac, idPr);
                        out.println(ok ? "OK|Anulat" : "ERROR|Nu s-a putut anula");
                    }
                    case "SET_DIAGNOSTIC" -> {
                        Long idPr = Long.valueOf(t[1]);
                        Programare pr = programareService.read(idPr);
                        if (pr == null) {
                            out.println("ERROR|Inexistent");
                            break;
                        }
                        pr.setDiagnostic(t[2]);
                        programareService.update(pr);
                        out.println("OK|Diagnostic setat");
                    }
                    case "PRESCRIBE_TREATMENT" -> {
                        Long idPr = Long.valueOf(t[1]);
                        Programare pr = programareService.read(idPr);
                        if (pr == null) {
                            out.println("ERROR|Inexistent");
                            break;
                        }
                        Tratament tr = new Tratament();
                        tr.setDescriere(t[2]);
                        tr.setDataPrescriptie(LocalDateTime.now());
                        tr.setProgramare(pr);
                        tratamentService.salveazaTratament(tr);
                        out.println("OK|" + tr.getId());
                    }
                    case "FILTER_APPOINTMENTS_BY_DOCTOR" -> {
                        Long idDoc = Long.valueOf(t[1]);
                        List<Programare> lst = doctorService
                                .filtreazaProgramariByData(idDoc, LocalDate.parse(t[2]));
                        if (lst.isEmpty()) {
                            out.println("OK|EMPTY");
                        } else {
                            out.println("OK|LIST");
                            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            for (Programare x : lst) {
                                String dateTime = x.getData().format(fmt);
                                out.println(
                                        x.getId() + "|"
                                        + x.getPacient().getNume() + " " + x.getPacient().getPrenume() + "|"
                                        + dateTime
                                );
                            }
                            out.println("END");
                        }
                    }
                    case "LIST_ALL_APPOINTMENTS" -> {
                        List<Programare> all = programareService.listaProgramari();
                        if (all.isEmpty()) {
                            out.println("OK|EMPTY");
                        } else {
                            out.println("OK|LIST");
                            for (Programare x : all) {
                                out.println(x.getId() + "|"
                                        + x.getPacient().getNume() + " " + x.getPacient().getPrenume() + "|"
                                        + x.getDoctor().getNume() + " " + x.getDoctor().getPrenume() + "|"
                                        + x.getData().toString());
                            }
                            out.println("END");
                        }
                    }
                    case "ADD_AVAILABILITY" -> {
                        Long idDoc = Long.valueOf(t[1]);
                        Doctor d = doctorService.gasesteDoctor(idDoc);
                        if (d == null) {
                            out.println("ERROR|Doctor inexistent");
                            break;
                        }
                        Disponibilitate disp = new Disponibilitate();
                        disp.setDoctor(d);

                        // parsează data completă:
                        disp.setData(LocalDate.parse(t[2]));

                        disp.setOraInceput(LocalTime.parse(t[3]));
                        disp.setOraSfarsit(LocalTime.parse(t[4]));
                        disponibilitateService.salveazaDisponibilitate(disp);
                        out.println("OK|" + disp.getId());
                    }
                    case "LIST_AVAILABILITIES" -> {
                        Long idDoc = Long.valueOf(t[1]);
                        Doctor d = doctorService.gasesteDoctor(idDoc);
                        if (d == null) {
                            out.println("ERROR|Doctor inexistent");
                            break;
                        }
                        List<Disponibilitate> list = disponibilitateService.listaDisponibilitatiByDoctor(idDoc);
                        if (list.isEmpty()) {
                            out.println("OK|EMPTY");
                        } else {
                            out.println("OK|LIST");
                            for (Disponibilitate dd : list) {
                                out.println(dd.getId() + "|"
                                        + dd.getData().toString() + "|"
                                        + dd.getOraInceput() + "-" + dd.getOraSfarsit());
                            }
                            out.println("END");
                        }
                    }

                    default ->
                        out.println("ERROR|Comanda necunoscuta");
                }
            }
        } catch (Exception e) {
        }
    }
}
