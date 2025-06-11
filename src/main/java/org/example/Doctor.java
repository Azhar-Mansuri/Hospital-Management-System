package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
    private final Connection connection;

    public Doctor(Connection connection) {
        this.connection = connection;
    }

    public void viewDoctors(){
        String query = "Select * from doctor;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();){
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.println(
                        "id : "+id+"\n"+
                        "Name : "+name+"\n"+
                        "specialization : "+specialization+"\n"+
                        "____________________________________________");
            }
        } catch (SQLException e){
            System.out.println(e);
        }
    }
    public boolean getDoctorsById(int id) {
        String query = "SELECT * FROM doctor WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String specialization = resultSet.getString("specialization");
                    System.out.println("Name: " + name +
                            "\nspecialization: " + specialization +
                            "\n____________________________________________");
                    return true;
                } else {
                    System.out.println("No Doctor found with ID: " + id);
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void deleteDoctor(int id){
        String query = "DELETE FROM doctor WHERE id = ?;";
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
