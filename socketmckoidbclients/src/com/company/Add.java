package com.company;

import java.sql.*;
import java.util.Scanner;

public class Add {

    static ResultSet result = Main.result;
    static Connection connection = Main.connection;
    static Scanner reader = Main.reader;
    static Statement statement = Main.statement;

    public Add(){}

    public static void main() throws SQLException {

        System.out.print("\n\nPlease choose what you would like to add to the Database:" +
                "\n1. Officer" +
                "\n2. Assignment" +
                "\n3. Rank" +
                "\nEnter choice: ");

        switch(reader.nextInt()){
            case 1:
                addCrew();
                break;
            case 2:
                addPost();
                break;
            case 3:
                addRank();
                break;
        }

    }


    public static void addCrew() throws SQLException {

        System.out.println("\nYou have chosen to recruit a new officer to the USS Corsar!" +
                " This requires an Crew ID, Name, and Rank ID");

        System.out.print("\nPlease enter a unique Student ID: ");
        int cid = reader.nextInt();
        reader.nextLine();

        System.out.print("\nEnter Student Name: ");
        String name = reader.nextLine();


        System.out.print("\nEnter an existing Major ID: ");
        int rid = reader.nextInt();


        //This does not take care of rank. That must be done separately
        Statement statement = connection.createStatement();
        try {
            result = statement.executeQuery("INSERT INTO Crew(id, name) VALUES " +
                    "(" + cid + ", '" + name  + ")"
            );

            System.out.println("Officer " + name + " has been added!");

            //double check query
            //This is to add the rank
            result = statement.executeQuery("INSERT INTO Ranking(cid, rid) VALUES " +
                    "(" + cid + ", " + rid + ")"
            );

            //get title name to print out
            result = statement.executeQuery("SELECT title FROM Rank WHERE id = " + rid);
        }
        catch(SQLException e){
            System.out.println("There was an issue adding student");
        }

    }

    public static void addPost() throws SQLException {

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

    public static void addRank() throws SQLException {

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
