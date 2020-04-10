package com.company;

import java.sql.*;
import java.util.Scanner;

public class Add {
    
    static ResultSet result = Main.result;
    static Connection connection = Main.connection;
    static Scanner reader = Main.reader;
    static Statement statement = Main.statement;

    public Add() throws SQLException {}

    public static void main() throws SQLException {

        System.out.print("\n\nPlease choose what you would like to add to the Database:" +
                "\n1. Student" +
                "\n2. Course" +
                "\n3. Major" +
                "\nEnter choice: ");

        switch(reader.nextInt()){
            case 1:
                addStudent();
                break;
            case 2:
                addCourse();
                break;
            case 3:
                addMajor();
                break;
        }

    }


    public static void addStudent() throws SQLException {

        System.out.println("\nYou have chosen to add a new student! This requires an ID, Name, and Major ID");

        System.out.print("\nPlease enter a unique Student ID: ");
        int id = reader.nextInt();
        reader.nextLine();

        System.out.print("\nEnter Student Name: ");
        String name = reader.nextLine();


        System.out.print("\nEnter an exisiting Major ID: ");
        int mid = reader.nextInt();


        Statement statement = connection.createStatement();
        try {
            result = statement.executeQuery("INSERT INTO Student(id, name, majorId) VALUES " +
                    "(" + id + ", '" + name + "', " + mid + ")"
            );

            System.out.println("Student " + name + " has been added!");
        }
        catch(SQLException e){
            System.out.println("There was an issue adding student");
        }

    }

    public static void addCourse() throws SQLException {

        System.out.println("\nYou have chosen to add a new course! This requires a Course ID and Course Description");

        System.out.print("\nPlease enter a unique Course ID: ");
        int id = reader.nextInt();
        reader.nextLine();

        System.out.print("\nEnter Course Description: ");
        String desc = reader.nextLine();


        Statement statement = connection.createStatement();
        try {
            result = statement.executeQuery(
                    "INSERT INTO Course(courseId, courseDesc) VALUES " +
                            "(" + id + ", '" + desc + "')"
            );

            System.out.println(desc + " has been added!");
        }
        catch(SQLException e){
            System.out.println("There was an issue adding course");
        }

    }

    public static void addMajor() throws SQLException {

        System.out.println("\nYou have chosen to add a new major! This requires a Major ID and Major Description");

        System.out.print("\nPlease enter a unique Major ID: ");
        int id = reader.nextInt();
        reader.nextLine();

        System.out.print("\nEnter Major Description: ");
        String desc = reader.nextLine();


        Statement statement = connection.createStatement();
        try {
            result = statement.executeQuery(
                    "INSERT INTO Major(majorId, majorDesc) VALUES " +
                            "(" + id + ", '" + desc + "')"
            );

            System.out.println(desc + " has been added!");
        }
        catch(SQLException e){
            System.out.println("There was an issue adding major");
        }

    }


}
