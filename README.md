Medical-Clinic

Medical-Clinic is a multi-module Java project implementing a simple client-server clinic management system. It uses sockets for communication and Hibernate/JPA with PostgreSQL for persistence.

📂 Repository Structure

Medical-Clinic
├── pom.xml                   # Parent POM
├── clinica-server            # Server module
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java/com/example/clinic
│       │   │   ├── model           # Entities & interfaces
│       │   │   ├── service         # Business logic services
│       │   │   └── server          # ClinicServer & ClientHandler
│       │   └── resources           # persistence.xml
│       └── test/java              # (unit tests)
└── clinica-client            # Client module
    ├── pom.xml
    └── src
        ├── main
        │   └── java/com/example/clinic/cli
        │       ├── ClinicClient.java
        │       └── Main.java       # Console-based UI
        └── test/java              # (unit tests)

🚀 Features

Patient Operations

Register Patient: Create a new patient record.

Request Appointment: Schedule a visit with a doctor.

Cancel Appointment: Cancel an existing appointment.

View Appointments: List all appointments for a patient.

Doctor Operations

Register Doctor: Create a new doctor record.

Add Availability: Specify date and time ranges a doctor is available.

List Availabilities: View a doctor’s available slots.

Set Diagnostic: Add diagnosis to an appointment.

Prescribe Treatment: Attach a treatment to an appointment.

Filter Appointments: List appointments for a doctor on a given date.

Persistence

Uses Hibernate JPA with PostgreSQL.

Entities: Pacient, Doctor, Programare, Tratament, Disponibilitate.

Configuration in clinica-server/src/main/resources/META-INF/persistence.xml.

⚙️ Prerequisites

Java 17+

Maven 3.6+

PostgreSQL database running (default jdbc:postgresql://localhost:5432/clinicdb)

🛠️ Building & Running

Clone repository:

git clone https://github.com/yourusername/Medical-Clinic.git
cd Medical-Clinic

Configure database in clinica-server/src/main/resources/META-INF/persistence.xml:

<property name="jakarta.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/clinicdb"/>
<property name="jakarta.persistence.jdbc.user" value="postgres"/>
<property name="jakarta.persistence.jdbc.password" value="<your-password>"/>

Build all modules:

mvn clean install

Run Server (in one terminal):

cd clinica-server
mvn exec:java
# Server listens on port 5555

Run Client (in another terminal):

cd clinica-client
mvn exec:java
# Interact via console menu

📋 Usage Example

Add a patient:

1 -> Nume: Popescu
     Prenume: Ion
     Email: ion@mail.com
     Telefon: 0712345678
     Data nasterii: 1980-05-15

Add a doctor:

5 -> Nume doctor: Ionescu
     Prenume: Maria
     Email: maria@mail.com
     Specializare: Cardiologie
     Necesita recomandare: false

Schedule an appointment:

2 -> ID pacient: 1
     ID doctor: 1
     Data (YYYY-MM-DDTHH:MM): 2025-06-10T14:30
     Recomandare familie: false

🤝 Contributing

Contributions welcome! Please fork the repo, create a feature branch, and open a PR.

📄 License

This project is licensed under the MIT License.

