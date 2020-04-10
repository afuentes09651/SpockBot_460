package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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


        //
    }
}
