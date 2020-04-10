package com.company;

import java.sql.*;

public class TestChangeSched {
    
    // create a Statement object for query execution
    Statement statement;
    // create result object
    ResultSet result;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestChangeSched gs = new TestChangeSched();
    }
    
    
    /** 
     * creates an instance of GetSched
     */
    public TestChangeSched() {

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
            statement = connection.createStatement();
            // create result object

            // display Fred's schedule
            String student = "9";
            GetSchedule(student);
            ShowSchedule("Fred");

            // add Fred to class on tracking
            String cid = "6";
            AddClass(student, cid);
            
            // delete Fred from class on Gravel
            cid = "3";
            DeleteClass(student, cid);

            // display Fred's updated schedule
            GetSchedule(student);
            ShowSchedule("Fred");
            
        } catch (SQLException e) {
            System.out.println("Error querying database: " + e);
            return;
        }
        
    }
    
    public void AddClass(String student, String cid) {
        try {
            result = statement.executeQuery(
                "INSERT INTO Enrolled (id, courseId) " +
                "VALUES (" + student + ", " + cid + ")"
            );
        } catch (SQLException e) {
            System.out.println("Insertion failed: " + e);
        }
    }

    public void GetSchedule (String student) {
        try {
            result = statement.executeQuery(
                "SELECT Course.courseDesc " + 
                "FROM Enrolled, Course  " +
                "WHERE " + student + "=Enrolled.id " +
                  "AND Enrolled.courseId=Course.courseId" 
            );
        } catch (SQLException e) {
            System.out.println("retrive schedule failed: " + e);
        }
    }
        
    public void ShowSchedule(String name) {
        System.out.println("\nSchedule of " + name + ":");
        try {
            while(result.next()) {
                System.out.println("    " + result.getString(1));
            }
            System.out.println();
        } catch (Exception e) {
                System.out.println("display failed: " + e);
        }
    }

    public void DeleteClass(String student, String cid) {
        try {
            result = statement.executeQuery(
                "DELETE FROM Enrolled " + 
                "WHERE Enrolled.id=" + student + 
                  "AND Enrolled.courseId=" + cid
            );
        } catch (Exception e) {
            System.out.println("drop failed: " + e);
        }
    }

}
