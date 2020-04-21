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
                "\n3. Rank (Role)" +
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

        System.out.println("\nYou have chosen to recruit a new officer to the USS Corsair!" +
                " This requires an Crew ID, Name, and Rank ID");

        System.out.print("\nPlease enter a unique Crew ID: ");
        int cid = reader.nextInt();
        reader.nextLine();

        System.out.print("\nEnter Crew Name: ");
        String name = reader.nextLine();

        //item for crew rankid is going to be used in a separate query to the rank database
        System.out.print("\nEnter new Crew's Rank ID: ");
        String rid = reader.nextLine();

        //item for crew postid is going to be used in a separate query to the job database
        System.out.print("\nEnter new Crew's Post ID: ");
        String pid = reader.nextLine();


        //This does not take care of rank. That must be done separately
        try {
            result = statement.executeQuery("INSERT INTO Crew (id, name) VALUES " +
                    "('" + cid + "', '" + name  + "')"
            );

            System.out.println("Officer " + name + " has been added!");


        }
        catch(SQLException e){
            e.printStackTrace();

            System.out.println("There was an issue adding officer");
        }

        //this query takes care of rank
        try {
            result = statement.executeQuery("INSERT INTO Ranking (cid, rid) VALUES " +
                    "('" + cid + "', '" + rid  + "')"
            );


        }
        catch(SQLException e){
            e.printStackTrace();

            System.out.println("There was an issue adding the officer's rank");
        }

    }

    public static void addPost() throws SQLException {

        System.out.println("\nYou have chosen to add a new Assignment! This requires a Post ID and Title");

        System.out.print("\nPlease enter a unique Post ID: ");
        int pid = reader.nextInt();
        reader.nextLine();

        System.out.print("\nEnter Assignment Title: ");
        String title = reader.nextLine();


        Statement statement = connection.createStatement();
        try {
            result = statement.executeQuery(
                    "INSERT INTO Post(id, title) VALUES " +
                            "(" + pid + ", '" + title + "')"
            );

            System.out.println(title + " has been added!");
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("There was an issue adding Assignment");
        }

    }

    public static void addRank() throws SQLException {

        System.out.println("\nYou have chosen to add a new Rank! This requires a Rank ID and Title");

        System.out.print("\nPlease enter a unique Rank ID: ");
        int rid = reader.nextInt();
        reader.nextLine();

        System.out.print("\nEnter rank Description: ");
        String title = reader.nextLine();


        Statement statement = connection.createStatement();
        try {
            result = statement.executeQuery(
                    "INSERT INTO Rank(id, title) VALUES " +
                            "(" + rid + ", '" + title + "')"
            );

            System.out.println(title + " has been added!");
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("There was an issue adding rank");
        }

    }


}
