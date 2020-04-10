package com.company;
import java.sql.*;
import java.util.Scanner;

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
                la.main();
                break;
            case 2:
                cinfo.main();
                break;
            case 3:
                add.main();
                break;
            case 4:
                enlist.main();
                break;
            case 5:
                enlist.main();
            case 6:
                break;
            case 7:
                System.out.println("\n\nThank you for choosing Spockbot! Live Long and Prosper!");
                break;
            default:
                System.out.println("\nInvalid Request. Please choose again.");
                break;
        }

    }



    
}
