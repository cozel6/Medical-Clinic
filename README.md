# # Medical-Clinic

**Medical-Clinic** is a multi-module Java project implementing a simple client-server clinic management system. It uses sockets for communication and Hibernate/JPA with PostgreSQL for persistence.

---

## 📂 Repository Structure

```text
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
```

---

## 🚀 Features

### Patient Operations

1. **Register Patient**: Create a new patient record.
2. **Request Appointment**: Schedule a visit with a doctor.
3. **Cancel Appointment**: Cancel an existing appointment.
4. **View Appointments**: List all appointments for a patient.

### Doctor Operations

1. **Register Doctor**: Create a new doctor record.
2. **Add Availability**: Specify date and time ranges a doctor is available.
3. **List Availabilities**: View a doctor’s available slots.
4. **Set Diagnostic**: Add diagnosis to an appointment.
5. **Prescribe Treatment**: Attach a treatment to an appointment.
6. **Filter Appointments**: List appointments for a doctor on a given date.

---

## ⚙️ Prerequisites

* Java 17+
* Maven 3.6+
* PostgreSQL database running (default `jdbc:postgresql://localhost:5432/clinicdb`)

---

## 🛠️ Building & Running

1. **Clone repository**:

   ```bash
   git clone https://github.com/yourusername/Medical-Clinic.git
   cd Medical-Clinic
   ```

2. **Configure database** in `clinica-server/src/main/resources/META-INF/persistence.xml`:

   ```xml
   <property name="jakarta.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/clinicdb"/>
   <property name="jakarta.persistence.jdbc.user" value="postgres"/>
   <property name="jakarta.persistence.jdbc.password" value="<your-password>"/>
   ```

3. **Build all modules**:

   ```bash
   mvn clean install
   ```

4. **Run Server** (in one terminal):

   ```bash
   cd clinica-server
   mvn exec:java
   # Server listens on port 5555
   ```

5. **Run Client** (in another terminal):

   ```bash
   cd clinica-client
   mvn exec:java
   # Interact via console menu
   ```

---

## 📋 Usage Example

**Add a patient**:

```
1 -> Nume: Popescu
     Prenume: Ion
     Email: ion@mail.com
     Telefon: 0712345678
     Data nasterii: 1980-05-15
```

**Add a doctor**:

```
5 -> Nume doctor: Ionescu
     Prenume doctor: Maria
     Email doctor: maria@mail.com
     Specializare: Cardiologie
     Necesita recomandare: false
```

**Schedule an appointment**:

```
2 -> ID pacient: 1
     ID doctor: 1
     Data (YYYY-MM-DDTHH:MM): 2025-06-10T14:30
```

---

## 📄 License

**Medical-Clinic** is a multi-module Java project implementing a simple client-server clinic management system. It uses sockets for communication and Hibernate/JPA with PostgreSQL for persistence.
