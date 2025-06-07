package com.example.clinic.cli;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ClinicClient client = new ClinicClient("localhost", 5555);

        try {
            client.connect();
        } catch (Exception e) {
            System.err.println("Nu s-a putut conecta la server: " + e.getMessage());
            sc.close();
            return;
        }

        while (true) {
            System.out.println("\n=== Clinica Medicala (Client) ===");

            // ==== PACIENT ====
            System.out.println("----- Pacient -----");
            System.out.println("1. Inregistreaza pacient");
            System.out.println("2. Solicitare programare");
            System.out.println("3. Anulare programare");
            System.out.println("4. Afiseaza toate programarile pacientului");

            // ==== DOCTOR ====
            System.out.println("\n----- Doctor -----");
            System.out.println("5. Inregistreaza doctor");
            System.out.println("6. Adauga disponibilitate doctor");
            System.out.println("7. Listeaza disponibilitati doctor");
            System.out.println("8. Pune diagnostic");
            System.out.println("9. Prescrie tratament");
            System.out.println("10. Filtreaza programari doctor");

            System.out.println("\n0. Iesire");
            System.out.print("Optiune: ");

            int opt;
            try {
                opt = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Te rog introdu un numar valid.");
                continue;
            }

            try {
                switch (opt) {
                    // ---- PACIENT ----
                    case 1 -> { // Inregistreaza pacient
                        System.out.println("----- Inregistrare Pacient -----");
                        System.out.print("Nume: ");
                        String pn = sc.nextLine();
                        System.out.print("Prenume: ");
                        String ppr = sc.nextLine();
                        System.out.print("Email: ");
                        String pe = sc.nextLine();
                        System.out.print("Telefon: ");
                        String tel = sc.nextLine();
                        System.out.print("Data nasterii (YYYY-MM-DD): ");
                        LocalDate dn = LocalDate.parse(sc.nextLine());
                        long idPac = client.registerPacient(pn, ppr, pe, tel, dn);
                        System.out.println("Pacient inregistrat cu ID=" + idPac);
                    }
                    case 2 -> { // Solicitare programare
                        System.out.print("ID pacient: ");
                        long idPac = Long.parseLong(sc.nextLine());
                        System.out.print("ID doctor: ");
                        long idDoc = Long.parseLong(sc.nextLine());
                        System.out.print("Data ora (YYYY-MM-DDTHH:MM): ");
                        LocalDateTime dt = LocalDateTime.parse(sc.nextLine());
                        System.out.print("Recomandare familie? (true/false): ");
                        boolean rf = Boolean.parseBoolean(sc.nextLine());
                        long idPr = client.createAppointment(idPac, idDoc, dt, rf);
                        System.out.println("Programare creata cu ID=" + idPr);
                    }
                    case 3 -> { // Anulare programare
                        System.out.print("ID pacient: ");
                        long ip = Long.parseLong(sc.nextLine());
                        System.out.print("ID programare: ");
                        long ipr = Long.parseLong(sc.nextLine());
                        client.cancelAppointment(ip, ipr);
                        System.out.println("Programare anulata.");
                    }
                    case 4 -> { // Afiseaza toate programarile pacientului
                        System.out.print("ID pacient: ");
                        long idPac = Long.parseLong(sc.nextLine());
                        // folosim listaProgramari generala și filtrăm local
                        List<String> toate = client.listAllAppointments();
                        toate.stream()
                                .filter(line -> line.startsWith(idPac + "|")) // linie: ID|Pacient|Doctor|Data
                                .forEach(line -> {
                                    String[] c = line.split("\\|");
                                    System.out.printf("ID:%s Doctor:%s Data:%s%n",
                                            c[0], c[2], c[3]);
                                });
                    }

                    // ---- DOCTOR ----
                    case 5 -> { // Inregistreaza doctor
                        System.out.println("----- Inregistrare Doctor -----");
                        System.out.print("Nume doctor: ");
                        String dn = sc.nextLine();
                        System.out.print("Prenume doctor: ");
                        String dpr = sc.nextLine();
                        System.out.print("Email doctor: ");
                        String de = sc.nextLine();
                        System.out.print("Specializare: ");
                        String spec = sc.nextLine();
                        System.out.print("Necesita recomandare familie? (true/false): ");
                        boolean rec = Boolean.parseBoolean(sc.nextLine());
                        long idDoc = client.registerDoctor(dn, dpr, de, spec, rec);
                        System.out.println("Doctor inregistrat cu ID=" + idDoc);
                    }
                    case 6 -> { // Listeaza disponibilitati
                        System.out.print("ID doctor: ");
                        long idD = Long.parseLong(sc.nextLine());
                        List<String> av = client.listAvailabilities(idD);
                        if (av.isEmpty()) {
                            System.out.println("Nu există disponibilități.");
                        } else {
                            System.out.println("Disponibilități doctor:");
                            av.forEach(line -> {
                                String[] c = line.split("\\|");
                                // c[1] = data, c[2] = interval
                                System.out.printf("ID:%s Data:%s Interval:%s%n",
                                        c[0], c[1], c[2]);
                            });
                        }
                    }
                    case 7 -> {
                        System.out.print("ID doctor: ");
                        long idD = Long.parseLong(sc.nextLine());
                        List<String> av = client.listAvailabilities(idD);
                        if (av.isEmpty()) {
                            System.out.println("Nu există disponibilități.");
                        } else {
                            System.out.println("Disponibilități doctor:");
                            av.forEach(line -> {
                                String[] c = line.split("\\|");
                                System.out.printf("ID:%s Zi:%s Interval:%s%n",
                                        c[0], c[1], c[2]);
                            });
                        }
                    }
                    case 8 -> { // Pune diagnostic
                        System.out.print("ID programare: ");
                        long ip5 = Long.parseLong(sc.nextLine());
                        System.out.print("Diagnostic: ");
                        String dg = sc.nextLine();
                        client.setDiagnostic(ip5, dg);
                        System.out.println("Diagnostic setat.");
                    }
                    case 9 -> { // Prescrie tratament
                        System.out.print("ID programare: ");
                        long ip6 = Long.parseLong(sc.nextLine());
                        System.out.print("Descriere tratament: ");
                        String desc = sc.nextLine();
                        long idT = client.prescribeTreatment(ip6, desc);
                        System.out.println("Tratament creat cu ID=" + idT);
                    }
                    case 10 -> { // Filtreaza programari doctor
                        System.out.print("ID doctor: ");
                        long idd7 = Long.parseLong(sc.nextLine());
                        System.out.print("Data (YYYY-MM-DD): ");
                        LocalDate d7 = LocalDate.parse(sc.nextLine());
                        List<String> lst = client.filterAppointmentsByDoctor(idd7, d7);
                        if (lst.isEmpty()) {
                            System.out.println("Nu exista programari.");
                        } else {
                            System.out.println("Programări doctor pentru " + d7 + ":");
                            lst.forEach(line -> {
                                String[] c = line.split("\\|");

                                System.out.printf("ID:%s Pacient:%s Data:%s%n",
                                        c[0], c[1], c[2]);
                            });
                        }
                    }
                    case 0 -> {
                        sc.close();
                        client.close();
                        System.out.println("La revedere!");
                        System.exit(0);
                    }
                    default ->
                        System.out.println("Optiune invalida.");
                }
            } catch (Exception ex) {
                System.out.println("Eroare: " + ex.getMessage());
            }
        }
    }
}
