package com.company;

import java.sql.*;
import java.util.Scanner;

public class Enlist {

    static ResultSet result = Main.result;
    static Scanner reader = Main.reader;
    static Statement statement = Main.statement;

    public Enlist() throws SQLException {}

    public static void main() throws SQLException {


        System.out.println("You have chosen to enlist a Crew Member! Here are the available Assignments: \n");

        result = statement.executeQuery("SELECT * FROM Post");

        while(result.next()){
            System.out.println(result.getString(1) + "\t" + result.getString(2));
        }

        System.out.print("\n\nPlease enter Crew Member ID: ");

        int cid = reader.nextInt();

        System.out.print("\nPlease enter Post ID: ");

        int pid = reader.nextInt();

        try{
            result = statement.executeQuery(
                    "INSERT INTO Job(cid, pid) VALUES " +
                            "(" + cid + ", " + pid + ")");

            System.out.println("Crew Member has been assigned!");
        }
        catch(SQLException e){
            System.out.println("There was an issue enlisting Crew Member");
        }

    }
}
