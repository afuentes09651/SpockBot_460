package com.company;

import java.sql.*;
import java.util.Scanner;

public class CrewInfo {

    static ResultSet result = Main.result;
    static Scanner reader = Main.reader;
    static Statement statement = Main.statement;

    public CrewInfo(){}

    //We may require more information than what is provided
    public static void main() throws SQLException{


        result  = statement.executeQuery(
                "SELECT * FROM Crew");

        System.out.println("Crew:\n");
        while(result.next()) {
            System.out.println(result.getString(1) + "\t" + result.getString(2));
        }

        System.out.print("\nEnter Crew ID to view info of an Officer: ");

        int crew = reader.nextInt();


        result = statement.executeQuery("SELECT Crew.name, Rank.title " +
                "FROM Crew, Rank, Ranking " +
                "WHERE Crew.id = " + crew +
                " AND Ranking.cid = " + crew +
                " AND Ranking.rid = Rank.id");

        System.out.println("\nOfficer info: ");
        while(result.next()){
            System.out.println(result.getString(1));
            System.out.println(result.getString(2));
            //System.out.println(result.getString(3));
        }


        result = statement.executeQuery("SELECT DISTINCT Post.title " +
                "FROM Crew, Post, Job " +
                        "WHERE Job.cid = " + crew +
                " AND Job.pid = Post.id");

        System.out.println("\nOfficer Assignments: ");
        while(result.next()){
            System.out.println(result.getString(1));
        }

        System.out.println();
    }

}
