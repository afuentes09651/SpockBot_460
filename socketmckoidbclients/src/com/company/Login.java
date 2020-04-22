package com.company;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Scanner;

public class Login {

    static ResultSet result = Main.result;
    static Statement statement = Main.statement;
    private String id;
    private String pw;

    public Login(String id, String pw){
        this.pw = pw;
        this.id = id;
    }

    public int authenticate() throws SQLException, NoSuchAlgorithmException {

        //pull password info from Users data base and check it


        result = statement.executeQuery("SELECT * FROM Users WHERE Users.userId='" + this.id + "'");

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

    }

}
