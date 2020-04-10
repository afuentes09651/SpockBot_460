package com.company;

import java.sql.*;
import java.util.Scanner;

public class StudentInfo{

    static ResultSet result;
    static Connection connection;
    static Scanner reader = new Scanner(System.in);

    public StudentInfo() throws SQLException{

        try {
            Class.forName("com.mckoi.JDBCDriver"); //.newInstance();
        } catch (Exception e) {
            System.out.println("Cannot register driver: " + e);
            return;
        }

        // URL for local database with configuration file
        String url = "jdbc:mckoi://localhost:9157/BedrockU.conf?create=false";

        // create root user info
        String admin = "admin";
        String adminPw = "9999";

        // make a connection to the database
        try {
            connection = DriverManager.getConnection(url, admin, adminPw);
        } catch (SQLException e) {
            System.out.println("cannot connect to database: " + e);
            return;
        }

    }

    public static void main() throws SQLException{
        Statement statement = connection.createStatement();

        //Query for like all of dem names and IDs
        result  = statement.executeQuery(
                "SELECT Student.name, Student.id FROM Student");

        System.out.println("Student Names and IDs:\n");
        while(result.next()) {
            System.out.println(result.getString(1) + "\t" + result.getString(2));
        }

        System.out.print("\nEnter Student ID to view info: ");

        int student = reader.nextInt();

        result = statement.executeQuery("SELECT Student.name, Address.street, Major.majorDesc " +
                "FROM Student, Address, Major " +
                "WHERE Student.id = " + student +
                " AND Address.id = " + student +
                " AND Major.majorId = Student.majorId");

        System.out.println("\nStudent info: ");
        while(result.next()){
            System.out.println(result.getString(1));
            System.out.println(result.getString(2));
            System.out.println(result.getString(3));
        }


        result = statement.executeQuery("SELECT DISTINCT Course.courseDesc " +
                "FROM Course, Enrolled " +
                        "WHERE Enrolled.id = " + student);

        System.out.println("\nStudent schedule: ");
        while(result.next()){
            System.out.println(result.getString(1));
        }

        System.out.println();
    }

}
