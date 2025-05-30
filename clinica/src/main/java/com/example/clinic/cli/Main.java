package com.example.clinic.cli;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.example.clinic.model.entitati.Doctor;
import com.example.clinic.model.entitati.Pacient;
import com.example.clinic.model.entitati.Programare;
import com.example.clinic.model.entitati.Tratament;
import com.example.clinic.service.DoctorService;
import com.example.clinic.service.PacientService;
import com.example.clinic.service.ProgramareService;
import com.example.clinic.util.HibernateUtil;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PacientService ps = new PacientService();
        DoctorService ds = new DoctorService();
        ProgramareService prs = new ProgramareService();

        while (true) {
            System.out.println("=== Clinica Medicala ===");
            System.out.println("1. Inregistreaza pacient");
            System.out.println("2. Inregistreaza doctor");
            System.out.println("3. Solicitare programare");
            System.out.println("4. Anulare programare");
            System.out.println("5. Pune diagnostic");
            System.out.println("6. Prescrie tratament");
            System.out.println("7. Filtreaza programari doctor");
            System.out.println("8. Afiseaza toate programarile");
            System.out.println("0. Iesire");
            System.out.print("Optiune: ");
            int opt = Integer.parseInt(sc.nextLine());

            switch (opt) {
                case 1:
                    System.out.print("Nume: ");
                    String pn = sc.nextLine();
                    System.out.print("Prenume: ");
                    String ppr = sc.nextLine();
                    System.out.print("Email: ");
                    String pe = sc.nextLine();
                    Pacient p = new Pacient();
                    System.out.print("Numar de telefon: ");
                    String telefon = sc.nextLine();
                    System.out.print("Data nasterii (YYYY-MM-DD): ");
                    LocalDate dataNasterii = LocalDate.parse(sc.nextLine());
                    p.setNume(pn);
                    p.setPrenume(ppr);
                    p.setEmail(pe);
                    p.setTelefon(telefon);
                    p.setDataNasterii(dataNasterii);
                    ps.inregistreazaPacient(p);
                    System.out.println("Pacient înregistrat cu ID=" + p.getId());
                    break;

                case 2:
                    System.out.print("Nume doctor: ");
                    String dn = sc.nextLine();
                    System.out.print("Prenume doctor: ");
                    String dpr = sc.nextLine();
                    System.out.print("Email doctor: ");
                    String de = sc.nextLine();
                    Doctor d = new Doctor();
                    d.setNume(dn);
                    d.setPrenume(dpr);
                    d.setEmail(de);
                    ds.inregistreazaDoctor(d);
                    System.out.println("Doctor înregistrat cu ID=" + d.getId());
                    break;

                case 3:
                    System.out.print("ID pacient: ");
                    Long idPac = Long.valueOf(sc.nextLine());
                    System.out.print("ID doctor: ");
                    Long idDoc = Long.valueOf(sc.nextLine());
                    System.out.print("Data ora (YYYY-MM-DDTHH:MM): ");
                    LocalDateTime dt = LocalDateTime.parse(sc.nextLine());
                    Programare prg = new Programare();
                    prg.setData(dt);
                    prg.setPacient(ps.gasestePacient(idPac));
                    prg.setDoctor(ds.gasesteDoctor(idDoc));
                    prs.salveazaProgramare(prg);
                    System.out.println("Programare creată cu ID=" + prg.getId());
                    break;

                case 4:
                    System.out.print("ID pacient: ");
                    Long ip = Long.valueOf(sc.nextLine());
                    System.out.print("ID programare: ");
                    Long ipr = Long.valueOf(sc.nextLine());
                    boolean ok = ps.anuleazaProgramare(ip, ipr);
                    System.out.println(ok ? "Anulare reusita" : "Anulare esuata");
                    break;

                case 5:
                    System.out.print("ID programare: ");
                    Long idProg = Long.valueOf(sc.nextLine());
                    System.out.print("Diagnostic: ");
                    String diag = sc.nextLine();
                    Programare updated = ds.puneDiagnostic(idProg, diag);
                    System.out.println("Diagnostic setat pe programarea ID=" + updated.getId());
                    break;

                case 6:
                    System.out.print("ID programare: ");
                    idProg = Long.valueOf(sc.nextLine());
                    System.out.print("Descriere tratament: ");
                    String desc = sc.nextLine();
                    Tratament t = ds.prescrieTratament(idProg, desc);
                    System.out.println("Tratament creat cu ID=" + t.getId());
                    break;

                case 7:
                    System.out.print("ID doctor: ");
                    Long idD = Long.valueOf(sc.nextLine());
                    System.out.print("Data (YYYY-MM-DD): ");
                    LocalDate ddate = LocalDate.parse(sc.nextLine());
                    ds
                      .filtreazaProgramariByData(idD, ddate)
                      .forEach(x ->
                          System.out.println("ID:" + x.getId() +
                                             " Pacient:" + x.getPacient().getNume() +
                                             " Ora:" + x.getData())
                      );
                    break;

                case 8:
                    prs
                      .listaProgramari()
                      .forEach(x ->
                          System.out.println("ID:" + x.getId() +
                                             " Pacient:" + x.getPacient().getNume() +
                                             " Doctor:" + x.getDoctor().getNume() +
                                             " Data:" + x.getData())
                      );
                    break;

                case 0:
                    HibernateUtil.shutdown();
                    System.exit(0);

                default:
                    System.out.println("Optiune invalida.");
            }
        }
    }
}
