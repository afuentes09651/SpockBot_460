package com.company;

import java.sql.*;
import java.util.Scanner;

public class Enroll {

    static ResultSet result;
    static Connection connection;
    static Scanner reader = new Scanner(System.in);

    public Enroll() throws SQLException {

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

    public static void main() throws SQLException {
        Statement statement = connection.createStatement();


        System.out.println("You have chosen to enroll a student! Here are the available Courses: \n");

        result = statement.executeQuery("SELECT courseDesc, courseId FROM Course");

        while(result.next()){
            System.out.println(result.getString(1) + "\t" + result.getString(2));
        }

        System.out.print("\n\nPlease enter Student ID: ");

        int sid = reader.nextInt();

        System.out.print("\nPlease enter Course ID: ");

        int cid = reader.nextInt();

        try{
            result = statement.executeQuery(
                    "INSERT INTO Enrolled(id, courseId) VALUES " +
                            "(" + sid + ", " + cid + ")");
        }
        catch(SQLException e){
            System.out.println("There was an issue enrolling student");
        }

    }
}
