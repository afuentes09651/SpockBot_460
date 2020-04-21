package com.company;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
                    statement.executeQuery("DROP TABLE Users");


                }catch(SQLException e){}


            //Create Crew Table
            statement.executeQuery(
                    "CREATE TABLE Crew " +
                            "(id INTEGER PRIMARY KEY, name VARCHAR(25))"
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

            //Create Ranking Table
            //This table is to link Crew with Rank
            statement.executeQuery(
                    "CREATE TABLE Ranking " +
                            "(cid INTEGER PRIMARY KEY, rid INTEGER)");

            //Create Users table
            statement.executeQuery(
                    "CREATE TABLE Users " +
                            "(id INTEGER PRIMARY KEY, user VARCHAR(25), pass VARCHAR(100), permission INTEGER)");

            //insert default user info and levels, in SHA256 hash

            String[] password = {"llap", "spock", "beans"};
            String[] passwordHash = {"", "", ""};
            for (int i = 0; i <= 2; i++) {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hashInBytes = md.digest(password[i].getBytes(StandardCharsets.UTF_8));

                // bytes to hex
                StringBuilder sb = new StringBuilder();
                for (byte b : hashInBytes) {
                    sb.append(String.format("%02x", b));
                }
                passwordHash[i] = sb.toString();
            }



            /*statement.executeQuery(
                    "INSERT INTO Users (user, pass, permission) VALUES " +
                            "(" + "Captain" + ", " + passwordHash[0] + ", " + 0 + ")");
            statement.executeQuery(
                    "INSERT INTO Users (user, pass, permission) VALUES " +
                            "(" + "Commander" + ", " + passwordHash[1] + ", " + 1 + ")");
            statement.executeQuery(
                    "INSERT INTO Users (user, pass, permission) VALUES " +
                            "(" + "Ensign" + ", " + passwordHash[2] + ", " + 2 + ")");

            System.out.println("WOOOOOOOOOOOO");

            result = statement.executeQuery("SELECT * FROM Users");
            System.out.println(result.getString(1));*/

            System.out.println("Ranking Table has been created");

            System.out.println("\nSchema has been generated");


        }catch(SQLException | NoSuchAlgorithmException e){
            System.out.println(e);
        }
    }
}
