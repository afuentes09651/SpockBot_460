package com.company;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Scanner;

public class Login {

    static ResultSet result = Main.result;
    static Scanner reader = Main.reader;
    static Statement statement = Main.statement;
    static Connection connection;

    private String [] ids = {"Captain", "Commander", "Ensign"}; //just some users. The names indicate rank for easr
    private String [] pws = {"llap","spock","beans"}; //passwords correspond to the indexes of ids
    private String id;
    private String pw;

    public Login(String id, String pw){
        this.pw = pw;
        this.id = id;
    }

    public int authenticate() throws SQLException, NoSuchAlgorithmException {

        //pull password info from Users data base and check it


        result = statement.executeQuery("SELECT * FROM Users WHERE Users.userId='" + this.id + "'");

        String hashedInputPass;

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashInBytes = md.digest(this.pw.getBytes(StandardCharsets.UTF_8));

        // bytes to hex
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        result.next();

        //sb is now the hashed input password, parse column results of permission as an integer and return


        if (result.getString(2).equals(sb.toString())) {
             //the password is authenticated

             return Integer.parseInt(result.getString(3));
        } else {
            //the password is not authenticated
            return -1;
        }


        /*
        for(int i = 0; i < ids.length; i++){
            if(ids[i].equals(id)){
                if(pws[i].equals(id));{
                    return i; //we are going to use the index as a permission
                }
            }
        }
        return -1; //access denied
    */
    }

}
