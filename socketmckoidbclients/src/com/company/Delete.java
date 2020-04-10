package com.company;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Delete {

    static Scanner reader = Main.reader;
    static Statement statement = Main.statement;

    public Delete(){}

    public static void main(){

        System.out.print("\n\nPlease choose what you would like to add to the Database:" +
                "\n1. Crew Member" +
                "\n2. Assignment" +
                "\n3. Rank (Role)" +
                "\nEnter choice: ");

        switch(reader.nextInt()){
            case 1:
                delCrew();
                break;
            case 2:
                delPost();
                break;
            case 3:
                delRank();
                break;
        }

    }


    public static void delCrew(){

        System.out.println("\nYou have chosen to relieve a new Crew Member from duty.");

        System.out.print("\nPlease enter Crew Member's ID: ");
        int cid = reader.nextInt();
        reader.nextLine();


        try {

            //delete from Crew
            statement.executeQuery("DELETE FROM Crew WHERE id = " + cid);

            //Delete from Ranking
            statement.executeQuery("DELETE FROM Ranking WHERE cid = " + cid);

            //Delete from Job
            statement.executeQuery("DELETE FROM Job WHERE cid = " + cid);

            System.out.println("Crew Member has been relieved from duty!");

        }
        catch(SQLException e){
            System.out.println("There was an issue relieving Crew Member from duty");
        }
    }

    public static void delPost(){

        System.out.println("\nYou have chosen to cancel an Assignment");

        System.out.print("\nPlease enter Assignment's ID: ");
        int pid = reader.nextInt();
        reader.nextLine();


        try {

            //delete from Post
            statement.executeQuery("DELETE FROM Post WHERE id = " + pid);

            //Delete from Job
            statement.executeQuery("DELETE FROM Job WHERE pid = " + pid);

            System.out.println("Assignment has been cancelled!");

        }
        catch(SQLException e){
            System.out.println("There was an issue cancelling assignment");
        }
    }

    public static void delRank(){

        System.out.println("\nYou have chosen to remove a Role from the USS Corsair");

        System.out.print("\nPlease enter Rank's ID: ");
        int rid = reader.nextInt();
        reader.nextLine();


        try {

            //delete from Post
            statement.executeQuery("DELETE FROM Post WHERE id = " + rid);

            //Delete from Ranking
            statement.executeQuery("DELETE FROM Ranking WHERE rid = " + rid);

            System.out.println("Role has been removed");

        }
        catch(SQLException e){
            System.out.println("There was an issue removing Role");
        }
    }


}
