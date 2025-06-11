# 🏥 Hospital Management Stand Alone App



---

## 🌍 Project Overview

Hospital Management Console App is a **Java CLI-based application** that helps **small clinics or solo practitioners** manage:

- 👨‍⚕️ Doctor Records  
- 🧑‍🦽 Patient Records  
- 📅 Appointment Scheduling  

All data is stored and retrieved using **MySQL + JDBC**, reducing paperwork by **~70%** and streamlining admin tasks.

---

## 🛠️ Technologies Used

- Java (Core + JDBC)  
- MySQL  
- Command Line Interface (CLI)  
- IntelliJ/Eclipse  
- SQL Workbench (optional)

---

## 📁 Project Structure

```
Hospital_Management/
├── App.java             # Main class with menu & DB connection
├── Doctor.java          # Doctor-related operations
├── Patient.java         # Patient-related operations
└── hospital.sql         # SQL file to create tables
```

---

## 🔄 Application Flow

1. 👨‍💻 Run `App.java`  
2. 📋 Select from menu:
   - ➕ Add new patient  
   - 📂 View/search/delete patients  
   - 📂 View/search/delete doctors  
   - 📅 Book an appointment  
3. 🔌 JDBC connects to MySQL in real-time  
4. 🖨️ Outputs success/error messages in console

---

## 📄 Key Files

### ✅ `App.java`
- Displays main menu  
- Connects to database  
- Delegates actions to `Doctor.java` and `Patient.java`

### ✅ `Doctor.java`
- View all doctors  
- Search doctor by ID  
- Delete doctor by ID  

### ✅ `Patient.java`
- Add new patient  
- View all patients  
- Search patient by ID  
- Delete patient by ID  

---

## 🗃️ MySQL Setup

Use the following SQL to create the database and tables:

```sql
CREATE DATABASE hospital;
USE hospital;

CREATE TABLE doctor (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  specialization VARCHAR(100)
);

CREATE TABLE patient (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  age INT,
  gender VARCHAR(10)
);

CREATE TABLE appointement (
  id INT AUTO_INCREMENT PRIMARY KEY,
  patient_id INT,
  doctor_id INT,
  appointementDate DATE,
  FOREIGN KEY (patient_id) REFERENCES patient(id),
  FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);
```

---

## 🚀 How to Run

1. 🛠️ Open the project in your IDE (IntelliJ or Eclipse)  
2. ✅ Setup MySQL DB using above SQL  
3. 🔧 Configure DB credentials in `App.java`  
4. ▶️ Compile & run using terminal or IDE  
5. 👨‍⚕️ Start managing doctors, patients, appointments

---

## 🙋‍♂️ Author

📍 **Azhar Mansuri**  
🎓 B.Tech – Information Technology  
🏫 Swami Vivekanand Group of Institutes, Indore  
🔗 [LinkedIn – Azhar Mansuri](https://www.linkedin.com/in/azhar-mansuri/)
