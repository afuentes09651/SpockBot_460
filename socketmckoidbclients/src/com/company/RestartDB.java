package com.company;

import java.sql.*;
import java.util.Scanner;

public class RestartDB {

    static ResultSet result;
    static Connection connection;
    static Scanner reader = new Scanner(System.in);


    public static void main(String [] args){

        //standard connection boilerplate
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


        //Drop all of the tables as needed
        try{
            Statement statement = connection.createStatement();
            ResultSet result;

            // Drop All Tables
                try {
                    statement.executeQuery("DROP TABLE Crew");
                    statement.executeQuery("DROP TABLE Post");
                    statement.executeQuery("DROP TABLE Rank");
                    statement.executeQuery("DROP TABLE Job"); //Job is giving issues
                    statement.executeQuery("DROP TABLE Ranking");

                }catch(SQLException e){}


            //Create Crew Table
            statement.executeQuery(
                    "CREATE TABLE Crew " +
                            "(id INTEGER PRIMARY KEY, name VARCHAR(25), rid INTEGER)"
            );

            System.out.println("Crew Table has been created");


            //Create Post Table
            statement.executeQuery(
                    "CREATE TABLE Post " +
                            "(id INTEGER PRIMARY KEY, title VARCHAR(50))"
            );

            System.out.println("Post Table has been created");


            //Create Job Table
            //This table is to link Crew with Post
            statement.executeQuery(
                    "CREATE TABLE Job " +
                            "(cid INTEGER, pid INTEGER)");

            System.out.println("Job Table has been created");

            //Create Rank Table
            statement.executeQuery(
                    "CREATE TABLE Rank " +
                            "(id INTEGER PRIMARY KEY, title VARCHAR(25))"
            );

            System.out.println(("Rank Table has been created"));

            //Create Job Table
            //This table is to link Crew with Post
            statement.executeQuery(
                    "CREATE TABLE Ranking " +
                            "(cid INTEGER PRIMARY KEY, rid INTEGER)");

            System.out.println("Ranking Table has been created");

            System.out.println("\nSchema has been generated");


        }catch(SQLException e){
            System.out.println(e);
        }
    }
}
