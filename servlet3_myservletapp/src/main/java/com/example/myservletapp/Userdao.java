package com.example.myservletapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Userdao {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String MySQLURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
            String databseUserName = "root";
            String databasePassword = "emmalujsp";
            con = DriverManager.getConnection(MySQLURL, databseUserName, databasePassword);
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static int save(User e) {
        int status = 0;
        try {
            Connection con = Userdao.getConnection();
            if (con != null) {
                System.out.println("Database connection is successful !!!!");
                String query = " insert into userdetails(name,password,email)" + " values (?,?,?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, e.getName());
                ps.setString(2, e.getPassword());
                ps.setString(3, e.getEmail());
                boolean bool = ps.execute();
                status = (bool) ? 0 : 1;
            }

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }

    public static int login(String uname, String password) {
        int status = 0;
        String psw;
        try {
            Connection con = Userdao.getConnection();
            if (con != null) {
                System.out.println("Database connection is successful !!!!");
                PreparedStatement ps = con.prepareStatement("select * from userdetails where email=?");
                ps.setString(1, uname);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    psw = rs.getString(2);
                    System.out.print(psw + password);
                    if (psw.equals(password)) {
                        status = 1;
                    }
                }
            }

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }


}
