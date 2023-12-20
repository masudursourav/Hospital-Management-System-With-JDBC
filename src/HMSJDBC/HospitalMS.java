package HMSJDBC;

import java.sql.*;
import java.util.Scanner;

public class HospitalMS {
    private static final  String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private  static final String password = "12346";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            Patient patient = new Patient(connection,scanner);
            Doctor doctor = new Doctor(connection);
            while (true){
                System.out.println("Hospital Management System");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patient");
                System.out.println("3. View Doctors");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");
                System.out.println("Enter Your Choice : ");
                int choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        patient.addPatient();
                        break;
                    case 2:
                        patient.viewPatients();
                        break;
                    case 3:
                        doctor.viewDoctors();
                        break;
                    case 4:
                        bookAppointment(patient,doctor,connection,scanner);
                        break;
                    case 5:
                        return;
                    default:
                        throw new IllegalStateException("Unexpected value: " + choice);
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner){
        System.out.print("Enter Patient ID : ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Doctor ID : ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter Appointment date (YYYY-MM-DD): ");
        String appointmentDate = scanner.next();
        if(patient.getPatientById(patientId) && doctor.getDoctorById(doctorId)){
            if(checkDoctorAvailability(doctorId, appointmentDate,connection)){
                String appointmentQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) Values(?, ?, ?)";
                try{
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1,patientId);
                    preparedStatement.setInt(2,doctorId);
                    preparedStatement.setString(3,appointmentDate);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if(rowsAffected > 0){
                        System.out.println(rowsAffected + " Appointment Added");
                    }
                    else{
                        System.out.println("Appointment failed to add");
                    }

                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
            else{
                System.out.println("Sorry !!!! Doctor not available on this date");
            }

        }else{
            System.out.println("Sorry! The Patient or Doctor Doesn't Exist :'( ");
        }
    }

    public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection){
        String query = "SELECT COUNT (*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,doctorId);
            preparedStatement.setString(2,appointmentDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt(1);
                if(count == 1) return false;
            }
            else{
                return true;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

}
