package com.example.clinic.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ClinicClient {

    private final String serverHost;
    private final int serverPort;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClinicClient(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    public void connect() throws Exception {
        socket = new Socket(serverHost, serverPort);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void close() {
        try {
            out.println("EXIT");
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        } finally {
            socket = null;
            in = null;
            out = null;
        }
    }

    private String sendRaw(String request) throws Exception {
        out.println(request);
        return in.readLine();
    }

    public long registerPacient(String nume, String prenume, String email,
            String telefon, LocalDate dataNasterii) throws Exception {
        String cmd = String.join("|",
                "REGISTER_PACIENT",
                nume, prenume, email, telefon, dataNasterii.toString()
        );
        String response = sendRaw(cmd);
        String[] parts = response.split("\\|", 2);
        if ("OK".equals(parts[0])) {
            return Long.parseLong(parts[1]);
        } else {
            throw new RuntimeException(parts[1]);
        }
    }

    public long registerDoctor(String nume, String prenume, String email,
            String specializare, boolean necesitaRecomandare) throws Exception {
        String cmd = String.join("|",
                "REGISTER_DOCTOR",
                nume, prenume, email, specializare, String.valueOf(necesitaRecomandare)
        );
        String response = sendRaw(cmd);
        String[] parts = response.split("\\|", 2);
        if ("OK".equals(parts[0])) {
            return Long.parseLong(parts[1]);
        } else {
            throw new RuntimeException(parts[1]);
        }
    }

    public long createAppointment(long idPacient, long idDoctor, LocalDateTime dataOra,
            boolean recomandareFamilie) throws Exception {
        String cmd = String.join("|",
                "CREATE_APPOINTMENT",
                String.valueOf(idPacient),
                String.valueOf(idDoctor),
                dataOra.toString(),
                String.valueOf(recomandareFamilie)
        );
        String response = sendRaw(cmd);
        String[] parts = response.split("\\|", 2);
        if ("OK".equals(parts[0])) {
            return Long.parseLong(parts[1]);
        } else {
            throw new RuntimeException(parts[1]);
        }
    }

    public void cancelAppointment(long idPacient, long idProgramare) throws Exception {
        String cmd = String.join("|",
                "CANCEL_APPOINTMENT",
                String.valueOf(idPacient),
                String.valueOf(idProgramare)
        );
        String response = sendRaw(cmd);
        String[] parts = response.split("\\|", 2);
        if (!"OK".equals(parts[0])) {
            throw new RuntimeException(parts[1]);
        }
    }

    public void setDiagnostic(long idProgramare, String diagnostic) throws Exception {
        String cmd = String.join("|",
                "SET_DIAGNOSTIC",
                String.valueOf(idProgramare),
                diagnostic
        );
        String response = sendRaw(cmd);
        String[] parts = response.split("\\|", 2);
        if (!"OK".equals(parts[0])) {
            throw new RuntimeException(parts[1]);
        }
    }

    public long prescribeTreatment(long idProgramare, String descriere) throws Exception {
        String cmd = String.join("|",
                "PRESCRIBE_TREATMENT",
                String.valueOf(idProgramare),
                descriere
        );
        String response = sendRaw(cmd);
        String[] parts = response.split("\\|", 2);
        if ("OK".equals(parts[0])) {
            return Long.parseLong(parts[1]);
        } else {
            throw new RuntimeException(parts[1]);
        }
    }

    public List<String> filterAppointmentsByDoctor(long idDoctor, LocalDate date) throws Exception {
        String cmd = String.join("|",
                "FILTER_APPOINTMENTS_BY_DOCTOR",
                String.valueOf(idDoctor),
                date.toString()
        );
        out.println(cmd);
        String first = in.readLine();
        String[] header = first.split("\\|", 2);
        if ("OK".equals(header[0]) && "EMPTY".equals(header[1])) {
            return new ArrayList<>();
        }
        if ("ERROR".equals(header[0])) {
            throw new RuntimeException(header[1]);
        }
        List<String> list = new ArrayList<>();
        String line;
        while ((line = in.readLine()) != null) {
            if ("END".equals(line)) {
                break;
            }
            list.add(line);
        }
        return list;
    }

    public List<String> listAllAppointments() throws Exception {
        String cmd = "LIST_ALL_APPOINTMENTS";
        out.println(cmd);
        String first = in.readLine();
        String[] header = first.split("\\|", 2);
        if ("OK".equals(header[0]) && "EMPTY".equals(header[1])) {
            return new ArrayList<>();
        }
        if ("ERROR".equals(header[0])) {
            throw new RuntimeException(header[1]);
        }
        List<String> list = new ArrayList<>();
        String line;
        while ((line = in.readLine()) != null) {
            if ("END".equals(line)) {
                break;
            }
            list.add(line);
        }
        return list;
    }

    public long addAvailability(long idDoctor,
            LocalDate data,
            LocalTime start,
            LocalTime end) throws Exception {
        String cmd = String.join("|",
                "ADD_AVAILABILITY",
                String.valueOf(idDoctor),
                data.toString(), // ← data completă
                start.toString(),
                end.toString()
        );
        String resp = sendRaw(cmd);
        String[] p = resp.split("\\|", 2);
        if ("OK".equals(p[0])) {
            return Long.parseLong(p[1]);
        }
        throw new RuntimeException(p[1]);
    }

    public List<String> listAvailabilities(long idDoctor) throws Exception {
        out.println("LIST_AVAILABILITIES|" + idDoctor);
        String header = in.readLine();
        String[] h = header.split("\\|", 2);
        if ("OK".equals(h[0]) && "EMPTY".equals(h[1])) {
            return new ArrayList<>();
        }
        if ("ERROR".equals(h[0])) {
            throw new RuntimeException(h[1]);
        }
        List<String> res = new ArrayList<>();
        String line;
        while ((line = in.readLine()) != null && !line.equals("END")) {
            res.add(line);
        }
        return res;
    }
}
