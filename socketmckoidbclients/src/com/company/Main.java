package com.company;
import com.mckoi.util.Log;

import java.sql.*;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Main {


    static ResultSet result;
    static Connection connection;
    static Scanner reader = new Scanner(System.in);
    static Statement statement;
    static listAll la;
    static CrewInfo cinfo;
    static Add add;
    static Enlist enlist;
    static UpRank upRank;
    static Delete delete;
    private static boolean access = false;
    private static int accLevel = -1; //We will use this to keep track of the permissions of the user


    public Main() {

    }
    
    public static void main(String[] args) throws SQLException {

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
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("cannot connect to database: " + e);
            return;
        }

        System.out.println("Welcome to Spockbot: USS Corsair DBMS!\n");

        System.out.println("\nBefore we begin, you must sign in.");

        while(!access){ //User must have permission to access the DBMS
            System.out.print("\nEnter ID: "); //username
            String id = reader.nextLine(); //stores input for username
            System.out.print("\nEnter Password: ");
            String pw = reader.nextLine(); //stores input for password

            Login login = new Login(id, pw);
            try {
                int auth = login.authenticate();
                switch(auth){
                    case 0: {
                        System.out.println("Access Granted! Welcome Captain!");
                        accLevel = 3;
                        access = true;
                        break;
                    }
                    case 1: {
                        System.out.println("Access Granted! Welcome Commander!");
                        accLevel = 2;
                        access = true;
                        break;
                    }
                    case 2: {
                        System.out.println("Access Granted! Welcome Ensign!");
                        accLevel = 1;
                        access = true;
                        break;
                    }
                    default: {
                        System.out.println("Access Denied!");
                    }
                }
            } catch (Exception e){
                System.out.println("An error occured in authentication, please check your credentials and try again.");
                e.printStackTrace();
            }



        }




        //initializing all my classes
        la = new listAll();
        cinfo = new CrewInfo();
        add = new Add();
        enlist = new Enlist();




        int choice = 0;

        while(choice != 7){ //loop for logic
            System.out.print("\nYou have the option to:\n" +
                    "1. List all...\n" +
                    "2. Crew Info\n" +
                    "3. Add\n" +
                    "4. Drop\n" +
                    "5. Enlist\n" +
                    "6. Update Rank\n" +
                    "7. Exit\n\n" +
                    "Select an option: ");
            choice = reader.nextInt();

            choiceHandler(choice);
        }
    }

    //so we can properly direct the user's choice
    public static void choiceHandler(int choice) throws SQLException{

        switch(choice){
            case 1:
                if (accLevel <= 3) {
                    la.main(); //Ensign Level Access
                } else {
                    System.out.println("Access denied, not proper security clearance.");
                }
                break;
            case 2:
                if (accLevel <= 3) {
                    cinfo.main(); //Ensign Level Access
                } else {
                        System.out.println("Access denied, not proper security clearance.");
                    }
                break;
            case 3:
                if (accLevel <= 2) {
                add.main(); //Commander Level Access
                } else {
                    System.out.println("Access denied, not proper security clearance.");
                }
                break;
            case 4:
                if (accLevel <= 1) {
                delete.main(); //Captain Level Access
                } else {
                    System.out.println("Access denied, not proper security clearance.");
                }
                break;
            case 5:
                if (accLevel <= 2) {
                enlist.main(); //Commander Level Access
                } else {
                    System.out.println("Access denied, not proper security clearance.");
                }
                break;
            case 6:
                if (accLevel <= 1) {
                upRank.main(); //Captain Level Access
                } else {
                    System.out.println("Access denied, not proper security clearance.");
                }
                break;
            case 7:
                connection.close();
                System.out.println("\n\nThank you for choosing Spockbot! Live Long and Prosper!");
                break;
            default:
                System.out.println("\nInvalid Request. Please choose again.");
                break;
        }

    }
}
