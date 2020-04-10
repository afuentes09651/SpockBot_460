package com.company;

import java.sql.*;
import java.util.Scanner;

public class listAll {

    static ResultSet result = Main.result;
    static Connection connection = Main.connection;
    static Scanner reader = Main.reader;
    static Statement statement = Main.statement;

    public listAll() throws SQLException{


    }

public static void main() throws SQLException {

        int choice = 0;
        System.out.print("\n\nYou chose to list all. What exactly would you like to list?\n" +
            "1. Crew\n" +
            "2. Positions\n" +
            "3. Ranks\n" +
            "4. Crew with a given Rank\n" +
            "5. Crew on a given Assignment\n" +
            "Enter Choice: ");


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


         //Crew
         public static void promptOne() throws SQLException {

            result  = statement.executeQuery(
                    "SELECT * FROM Crew");

             System.out.println("Crew:\n");
             while(result.next()) {
                 System.out.println(result.getString(1));
                 System.out.println(result.getString(2));
             }
         }

         //Positions
        public static void promptTwo() throws SQLException {

            result  = statement.executeQuery(
                "SELECT * FROM Post");


            System.out.println("Positions:\n");
            while(result.next()) {
                System.out.println(result.getString(1));
                System.out.println(result.getString(2));
            }

        }

        //Ranks
        public static void promptThree() throws SQLException {

            //Query for like all of dem course IDs
            result  = statement.executeQuery(
                    "SELECT * FROM Rank");

            System.out.println("Ranks:\n");
            while(result.next()) {
                System.out.println(result.getString(1));
                System.out.println(result.getString(2));
            }

        }

        //EDIT
        //Crew with a given Rank
        public static void promptFour() throws SQLException {

            result = statement.executeQuery("SELECT * FROM Rank");

            System.out.println("\nPlease choose from the following ranks: \n");
            while(result.next()){
                System.out.println(result.getString(1) + "\t" + result.getString(2));
            }
            reader.nextLine();
            System.out.print("\nEnter major ID: ");

            int major = reader.nextInt();

            //Double Check this query please
            result  = statement.executeQuery(
                    "SELECT DISTINCT Crew.name FROM Crew, Rank WHERE Crew.id = Rank.cid AND Crew.id =" + major);



            System.out.println("Crew of Rank ID " + major + ":\n");
            while(result.next()) {
                System.out.println(result.getString(1));
            }

        }

        //Crew on a given Assignment
        public static void promptFive() throws SQLException{


            result  = statement.executeQuery(
                    "SELECT * FROM Post");

            System.out.println("\nPlease choose from the following Assignments: ");
            while(result.next()){
                System.out.println(result.getString(1) + "\t" + result.getString(2));
            }
            reader.nextLine();

            System.out.print("Enter Post ID: ");
            int post = reader.nextInt();

            //double check this query
            result = statement.executeQuery("SELECT DISTINCT Crew.name FROM Crew, Job, Post " +
                    "WHERE Crew.id = Job.cid " +
                    "AND Job.pid = " + post);

            System.out.println("\nCrew on Post ID " + post + ":\n");
            while(result.next()){
                System.out.println(result.getString(1));
            }

        }
}
