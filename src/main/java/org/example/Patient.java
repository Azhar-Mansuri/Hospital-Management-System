package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private final Connection connection;
    private final Scanner sc;

    public Patient(Connection connection, Scanner sc) {
        this.connection = connection;
        this.sc = sc;
    }
    public void addPatient(){
        String query = "Insert into patient(name,age,gender) Values (?,?,?);";
        System.out.print("Enter patient name : ");
        String name = sc.nextLine();
        System.out.print("Enter patient age : ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter patient gender (Male/Female) : ");
        String gender = sc.nextLine();
        try( PreparedStatement preparedStatement = connection.prepareStatement(query);){
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,gender);
            int affectedRow = preparedStatement.executeUpdate();
            if(affectedRow!=0){
                System.out.println("Patient Added Successfully :)");
            }else{
                System.out.println("Patient was not Added :(");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void viewPatients(){
        String query = "Select * from patient;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();){
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.println(
                                    "ID : "+id+"\n"+
                                    "Name : "+name+"\n"+
                                    "Age : "+age+"\n"+
                                    "Gender : "+gender+"\n"+
                "____________________________________________");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean getPatientById(int id) {
        String query = "SELECT * FROM patient WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String gender = resultSet.getString("gender");
                    System.out.println("Name: " + name +
                            "\nAge: " + age +
                            "\nGender: " + gender +
                            "\n____________________________________________");
                    return true;
                } else {
                    System.out.println("No patient found with ID: " + id);
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void deletePatient(int id){
        String query = "DELETE FROM patient WHERE id = ?;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query);){
            preparedStatement.setInt(1,id);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows!=0){
                System.out.println("Deletion Successful :)");
            } else{
                System.out.println("Deletion Failed :(");
            }
        } catch(SQLException e){
            System.out.println(e);
        }
    }
}
