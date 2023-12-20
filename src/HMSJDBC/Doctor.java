package HMSJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    private Connection connection;
    public Doctor(Connection connection){
        this.connection = connection;
    }

    public void viewDoctors(){
        String query = "select * from doctors";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors : ");
            System.out.println("+-------------+-------------------+-----------------+");
            System.out.println("| Doctor Id  | Name              | Specialization   |");
            System.out.println("+-------------+-------------------+-----------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("|%-13s|%-19s|%-18s|",id,name,specialization);
                System.out.println();
                System.out.println("+-------------+-------------------+-----------------+");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public boolean getDoctorById(int id){
        String query = "SELECT * FROM doctors WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
