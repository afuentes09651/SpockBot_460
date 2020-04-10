package com.company;

import java.sql.*;
import java.util.Scanner;

public class listAll {

    static ResultSet result;
    static Connection connection;
    static Scanner reader = new Scanner(System.in);

    public listAll() throws SQLException{

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

        int choice = 0;
        System.out.print("\n\nYou chose to list all. What exactly would you like to list?\n" +
            "1. IDs and Student Names\n" +
            "2. Courses in database with Course IDs\n" +
            "3. Majors in database with Major IDs\n" +
            "4. Students with a given major\n" +
            "5. Students in a Course in the database\n" +
            "Enter Choice: ");

        // choice = reader.nextInt();



         switch (reader.nextInt()){
             case 1:
                 promptOne();
                 break;

             case 2:
                 promptTwo();
                 break;
             case 3:
                 promptThree();
                 break;
             case 4:
                 promptFour();
                 break;
             case 5:
                 promptFive();
                 break;
         }

            return;
         }


         //IDs and Student Names
         public static void promptOne() throws SQLException {
           Statement  statement = connection.createStatement();

            //Query for like all of dem names and IDs
            result  = statement.executeQuery(
                    "SELECT Student.name, Student.id FROM Student");

             System.out.println("Student Names and IDs:\n");
             while(result.next()) {
                 System.out.println(result.getString(1));
                 System.out.println(result.getString(2));
             }
         }

         //Courses with their IDs
        public static void promptTwo() throws SQLException {

            Statement statement = connection.createStatement();

             //Query for like all of dem course IDs
            result  = statement.executeQuery(
                "SELECT courseDesc, Course.courseId FROM Course");


            System.out.println("Courses:\n");
            while(result.next()) {
                System.out.println(result.getString(1));
                System.out.println(result.getString(2));
            }

        }

        //Majors with their IDs
        public static void promptThree() throws SQLException {
            Statement statement = connection.createStatement();


            //Query for like all of dem course IDs
            result  = statement.executeQuery(
                    "SELECT majorDesc, majorId FROM Major");

            System.out.println("Majors:\n");
            while(result.next()) {
                System.out.println(result.getString(1));
                System.out.println(result.getString(2));
            }

        }

        //Students with a given Major
        public static void promptFour() throws SQLException {
            Statement statement = connection.createStatement();

            result = statement.executeQuery("SELECT majorDesc, majorId FROM Major");

            System.out.println("\nPlease choose from the following majors: \n");
            while(result.next()){
                System.out.println(result.getString(1) + "\t" + result.getString(2));
            }
            reader.nextLine();
            System.out.print("\nEnter major ID: ");

            int major = reader.nextInt();

            result  = statement.executeQuery(
                    "SELECT DISTINCT Student.name FROM Student WHERE Student.majorId =" + major);



            System.out.println("Students taking Major ID " + major + ":\n");
            while(result.next()) {
                System.out.println(result.getString(1));
            }

        }

        //Students in a Given Course
        public static void promptFive() throws SQLException{
            Statement statement = connection.createStatement();

            //Query for like all of dem course IDs
            result  = statement.executeQuery(
                    "SELECT courseDesc, courseId FROM Course");

            System.out.println("\nPlease choose from the following Courses: ");
            while(result.next()){
                System.out.println(result.getString(1) + "\t" + result.getString(2));
            }
            reader.nextLine();

            System.out.print("Enter Course ID: ");
            int course = reader.nextInt();

            result = statement.executeQuery("SELECT DISTINCT Student.name FROM Student, Enrolled, Course " +
                    "WHERE Student.id = Enrolled.id " +
                    "AND Enrolled.courseId =" + course);

            System.out.println("\nStudents Enrolled in Course ID " + course + ":\n");
            while(result.next()){
                System.out.println(result.getString(1));
            }

        }
}
