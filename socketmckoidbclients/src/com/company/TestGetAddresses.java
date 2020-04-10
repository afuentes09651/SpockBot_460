package com.company;

import java.sql.*;

public class TestGetAddresses {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args) {
        

        // ******************************************************
        // ****************  Connecting to datatbase  ***********
        // ******************************************************
        
        // Register Mckoi JDBC driver
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
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, admin, adminPw);
        } catch (SQLException e) {
            System.out.println("cannot connect to database: " + e);
            return;
        }



        // ******************************************************
        // ****************  Querying datatbase  ****************
        // ******************************************************
        
        try {
            // create a Statement object for query execution
            Statement statement = connection.createStatement();
            // create a result object
            ResultSet result;
            
            // query to get addresses of students
            result = statement.executeQuery(
                "SELECT Student.name, Address.street, Address.city, " + 
                        "Address.state, Address.zip " +
                "FROM Student, Address " +
                "WHERE Student.id=Address.id"
            );
            
            // display result of query
            System.out.println("Student Addresses:\n");
            while(result.next()) {
                System.out.println(result.getString(1));
                System.out.println(result.getString(2));
                System.out.println(result.getString(3) + ", " + 
                                   result.getString(4) + "  " +
                                   result.getString(5) + "\n");
            }
            
        } catch (SQLException e) {
            System.out.println("Error querying database: " + e);
            return;
        }
        
    }
    
}
