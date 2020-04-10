// Armando Fuentes
// C00296127
// CMPS 360
// Programming Project : 5-1
// Program Description: This code reads and writes to BedrockU database
// Certificate of Authenticity: 
 // certify that the changes and additions to the code of this project are entirely my own work.


package com.company;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    static Scanner reader = new Scanner(System.in);
    static listAll la;
    static StudentInfo sinfo;
    static Add add;
    static Enroll enroll;

    public Main() {

    }
    
    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome to Project 5!\n");

        //initializing all my classes
        la = new listAll();
        sinfo = new StudentInfo();
        add = new Add();
        enroll = new Enroll();




        int choice = 0;

        while(choice != 5){ //loop for logic
            System.out.print("\nYou have the option to:\n" +
                    "1. List all...\n" +
                    "2. Student Info\n" +
                    "3. Add a...\n" +
                    "4. Enroll a student\n" +
                    "5. Exit\n\n" +
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
                sinfo.main();
                break;
            case 3:
                add.main();
                break;
            case 4:
                enroll.main();
                break;
            case 5:
                System.out.println("\n\nThank you for choosing Project 5! Have a Java Day :)");
            default: return;
        }

    }



    
}
