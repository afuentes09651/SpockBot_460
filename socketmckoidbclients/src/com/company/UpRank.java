package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpRank {

    static ResultSet result = Main.result;
    static Scanner reader = Main.reader;
    static Statement statement = Main.statement;

    public static void main() throws SQLException {


        System.out.println("You have chosen to update a Crew Member's rank! Here are the available ranks: \n");

        result = statement.executeQuery("SELECT * FROM Rank");

        while(result.next()){
            System.out.println(result.getString(1) + "\t" + result.getString(2));
        }

        System.out.print("\n\nPlease enter Crew Member ID: ");

        int cid = reader.nextInt();

        System.out.print("\nPlease enter Rank ID: ");

        int rid = reader.nextInt();

        try{
            result = statement.executeQuery(
                    "UPDATE Ranking SET rid='" + rid + "' WHERE cid=" + cid + ";");

            System.out.println("Crew Member has been assigned!");
        }
        catch(SQLException e){
            System.out.println("There was an issue enlisting Crew Member");
        }

    }
}
