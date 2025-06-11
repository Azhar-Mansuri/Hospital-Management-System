package org.example;

import java.sql.*;
import java.util.Scanner;

public class App
{
    private static  final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "Azhar@123";

    public static  Connection getConnection(){
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Patient patient = new Patient(getConnection(),sc);
        Doctor doctor = new Doctor(getConnection());
        while(true){
            System.out.println("****** Welcome to Hospital ****** \n");
            System.out.println("Enter 1 for Add Patient");
            System.out.println("Enter 2 for View All Patients");
            System.out.println("Enter 3 for Get Patient by ID ");
            System.out.println("Enter 4 for Delete Patient");
            System.out.println("Enter 5 for View All Doctors");
            System.out.println("Enter 6 for Get Doctor by ID");
            System.out.println("Enter 7 for Delete Doctor");
            System.out.println("Enter 8 for Book an Appointment");
            System.out.println();
            System.out.print("enter your choice : ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    patient.addPatient();
                    break;
                case 2:
                    patient.viewPatients();
                    break;
                case 3:
                    System.out.print("Enter patient id for details : ");
                    int id1 = sc.nextInt();
                    sc.nextLine();
                    patient.getPatientById(id1);
                    break;
                case 4:
                    System.out.print("Enter patient id to delete : ");
                    int id2 = sc.nextInt();
                    sc.nextLine();
                    patient.deletePatient(id2);
                    break;
                case 5:
                    doctor.viewDoctors();
                    break;
                case 6:
                    System.out.print("Enter doctor id for details : ");
                    int id3 = sc.nextInt();
                    sc.nextLine();
                    doctor.getDoctorsById(id3);
                    break;
                case 7:
                    System.out.print("Enter doctor id to delete : ");
                    int id4 = sc.nextInt();
                    sc.nextLine();
                    doctor.deleteDoctor(id4);
                    break;
                case 8:
                    bookAppointment(patient,doctor,getConnection(),sc);
                    break;
                default:
                    System.out.println("Enter a valid choice");
                    break;
            }
        }
    }
    static void bookAppointment(Patient patient,Doctor doctor,Connection connection,Scanner sc){
        System.out.print("Enter patient id : ");
        int patientId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter doctor id : ");
        int doctorId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the Appointment Date (YYYY-MM-DD) : ");
        String date = sc.nextLine();
        if(patient.getPatientById(patientId) && doctor.getDoctorsById(doctorId)){
            if(checkDoctorAvailable(connection,date,doctorId)){
                String appointmentQuery = "Insert into appointement(patient_id,doctor_id,appointementDate) Values (?,?,?);";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1,patientId);
                    preparedStatement.setInt(2,doctorId);
                    preparedStatement.setString(3,date);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if(rowsAffected != 0){
                        System.out.println("Appointment Booked Successfully !");
                    } else{
                        System.out.println("Appointment Booking Failed !!!");
                    }
                } catch (SQLException e){
                    e.printStackTrace();
                }
            } else{
                System.out.println("Doctor is not available on this date !!!");
            }
        } else{
            System.out.println("Either patient or doctor does not exists !!!");
        }
    }
    static boolean checkDoctorAvailable(Connection connection,String date,int doctorID){
        String doctorAvailableQuery = "Select count(*) from appointement where doctor_id = ? and appointementDate = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(doctorAvailableQuery);
            preparedStatement.setInt(1,doctorID);
            preparedStatement.setString(2,date);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next() && resultSet.getInt(1)>0){
                return false;
            } else{
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
