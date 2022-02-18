package com.example.first.dao;

import com.example.first.model.Calculator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class calculatordao {
    public Calculator getcalc(int f,int s,int sum){
        Calculator c=new Calculator();
        c.setFirstnum(f);
        c.setSecondnum(s);
        c.setSum(sum);

        System.out.println("Database connection  starting");
        String MySQLURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
        String databseUserName = "root";
        String databasePassword = "emmalujsp";
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(MySQLURL, databseUserName, databasePassword);
            if (con != null) {
                System.out.println("Database connection is successful !!!!");
                String query = " insert into calculator (first, second , sum)" + " values (?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, f);
                ps.setInt(2, s);
                ps.setInt(3, sum);
                ps.execute();
            }


        } catch (Exception e2) {
            System.out.println(e2);
        }


        return c;
    }
}
