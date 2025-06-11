# Hospital Management System with JDBC

A console-based Hospital Management System built in Java using JDBC for database connectivity. This application allows hospital staff to manage patients, doctors, and appointments efficiently.

## 🏥 Features

- **Patient Management**

  - Add new patients to the system
  - View all registered patients
  - Search patients by ID

- **Doctor Management**

  - View all available doctors
  - Search doctors by ID
  - Display doctor specializations

- **Appointment System**
  - Book appointments between patients and doctors
  - Check doctor availability for specific dates
  - Prevent double booking for the same doctor on the same date

## 🛠️ Technology Stack

- **Language**: Java
- **Database**: MySQL
- **Database Connectivity**: JDBC (Java Database Connectivity)
- **IDE**: IntelliJ IDEA (based on project structure)

## 📊 Database Schema

The system uses a MySQL database named `hospital` with the following tables:

### Tables Structure

```sql
-- Patients Table
CREATE TABLE patients (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL
);

-- Doctors Table
CREATE TABLE doctors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    specialization VARCHAR(255) NOT NULL
);

-- Appointments Table
CREATE TABLE appointments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    doctor_id INT,
    appointment_date DATE,
    FOREIGN KEY (patient_id) REFERENCES patients(id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);
```

## 🏗️ System Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    Hospital Management System               │
├─────────────────────────────────────────────────────────────┤
│                     HospitalMS.java                        │
│                   (Main Application)                       │
│  ┌─────────────────┐ ┌─────────────────┐ ┌────────────────┐ │
│  │   Patient.java  │ │   Doctor.java   │ │   MySQL DB     │ │
│  │   Management    │ │   Management    │ │   (hospital)   │ │
│  └─────────────────┘ └─────────────────┘ └────────────────┘ │
└─────────────────────────────────────────────────────────────┘

Flow Diagram:
┌─────────────┐    ┌──────────────┐    ┌─────────────────┐
│   User      │───▶│ HospitalMS   │───▶│    Database     │
│ Interface   │    │ (Main Menu)  │    │   Operations    │
└─────────────┘    └──────────────┘    └─────────────────┘
      │                     │                    │
      │            ┌────────┴────────┐          │
      │            │                 │          │
      ▼            ▼                 ▼          ▼
┌──────────┐ ┌──────────┐ ┌─────────────┐ ┌────────────┐
│ Patient  │ │ Doctor   │ │ Appointment │ │   MySQL    │
│ Module   │ │ Module   │ │   Booking   │ │  Database  │
└──────────┘ └──────────┘ └─────────────┘ └────────────┘
```

## ⚙️ Prerequisites

Before running this application, ensure you have:

1. **Java Development Kit (JDK) 8 or higher**
2. **MySQL Server** installed and running
3. **MySQL JDBC Driver** (mysql-connector-java)
4. **IntelliJ IDEA** or any Java IDE (optional)

## 🚀 Setup Instructions

### 1. Database Setup

1. Install and start MySQL server
2. Create a database named `hospital`:

   ```sql
   CREATE DATABASE hospital;
   USE hospital;
   ```

3. Create the required tables using the SQL schema provided above

4. Insert sample doctor data (optional):
   ```sql
   INSERT INTO doctors (name, specialization) VALUES
   ('Dr. John Smith', 'Cardiologist'),
   ('Dr. Sarah Johnson', 'Neurologist'),
   ('Dr. Michael Brown', 'Orthopedic');
   ```

### 2. Application Configuration

1. Clone or download the project
2. Update database credentials in `HospitalMS.java`:
   ```java
   private static final String url = "jdbc:mysql://localhost:3306/hospital";
   private static final String username = "your_username";
   private static final String password = "your_password";
   ```

### 3. JDBC Driver Setup

1. Download MySQL JDBC Driver (mysql-connector-java.jar)
2. Add it to your project classpath

### 4. Running the Application

1. Compile the Java files:

   ```bash
   javac -cp ".:mysql-connector-java.jar" src/HMSJDBC/*.java
   ```

2. Run the main class:
   ```bash
   java -cp ".:mysql-connector-java.jar:src" HMSJDBC.HospitalMS
   ```

## 🖥️ Usage

When you run the application, you'll see a menu with the following options:

```
Hospital Management System
1. Add Patient
2. View Patient
3. View Doctors
4. Book Appointment
5. Exit
Enter Your Choice :
```

### Menu Options:

1. **Add Patient**: Register a new patient with name, age, and gender
2. **View Patient**: Display all registered patients in a formatted table
3. **View Doctors**: Show all available doctors with their specializations
4. **Book Appointment**: Schedule an appointment between a patient and doctor
5. **Exit**: Close the application

### Sample Output:

```
Patients :
+-------------+-------------------+----------+----------+
| Patient Id  | Name              | Age      | Gender   |
+-------------+-------------------+----------+----------+
|1            |John Doe           |25        |Male      |
+-------------+-------------------+----------+----------+

Doctors :
+-------------+-------------------+-----------------+
| Doctor Id  | Name              | Specialization   |
+-------------+-------------------+-----------------+
|1            |Dr. John Smith     |Cardiologist     |
+-------------+-------------------+-----------------+
```

## 📁 Project Structure

```
Hospital-Management-System-With-JDBC/
│
├── src/
│   └── HMSJDBC/
│       ├── HospitalMS.java     # Main application class
│       ├── Patient.java        # Patient management operations
│       └── Doctor.java         # Doctor management operations
│
├── .gitignore                  # Git ignore file
├── Hospital Management System With JDBC.iml
└── README.md                   # This file
```

## 🔧 Key Features Explained

### Appointment Booking Logic

- Prevents double booking by checking if a doctor already has an appointment on the requested date
- Validates that both patient and doctor exist before booking
- Provides clear feedback for successful or failed operations

### Database Connection Management

- Uses JDBC PreparedStatements to prevent SQL injection
- Implements proper exception handling for database operations
- Maintains a single connection throughout the application lifecycle

## 🚨 Error Handling

The application includes comprehensive error handling for:

- Database connectivity issues
- Invalid user inputs
- SQL exceptions
- Missing patient or doctor records

## 🔮 Future Enhancements

Potential improvements for the system:

- [ ] Add patient discharge functionality
- [ ] Implement appointment cancellation
- [ ] Add doctor schedule management
- [ ] Include billing and payment tracking
- [ ] Add user authentication and roles
- [ ] Implement appointment time slots (not just dates)
- [ ] Add email notifications for appointments
- [ ] Create a GUI interface using JavaFX or Swing

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## 📝 License

This project is open source and available under the [MIT License](LICENSE).

## 👨‍💻 Author

Masudur Rahman Sourav

Developed as a learning project to demonstrate JDBC connectivity and basic hospital management operations.

---

**Note**: This is a console-based application designed for educational purposes. For production use, consider implementing additional security measures, input validation, and a proper user interface.
