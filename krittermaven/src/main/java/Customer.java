import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.sql.*;
import java.util.Scanner;

public class Customer {
    int customerId;
    String name;
    String place;
    String username;
    int pin;String MySQLURL = "jdbc:mysql://localhost:3306/bank?useSSL=false";
    String databseUserName = "root";
    String databasePassword = "emmalujsp";

    Scanner sc = new Scanner(System.in);
    public static void loadlog4j(){
        String logpath=System.getProperty("user.dir")+"/log4j.properties";
        PropertyConfigurator.configure(logpath);
    }
    public static Logger logger=null;
    public Customer(){
        logger = Logger.getLogger(Customer.class.getName());
    }
    Connection con = null;
    void addCustomer() {
        System.out.print("Enter Customer Name :");
        name = sc.nextLine();
        System.out.print("Enter customer place :");
        place = sc.nextLine();
        System.out.print("Enter username :");
        username = sc.nextLine();
        System.out.print("Enter pin :");
        pin = sc.nextInt();
        try {
            con = DriverManager.getConnection(MySQLURL, databseUserName, databasePassword);
            if (con != null) {
                logger.info("Database connection is successful  for insert user!!!!");
                String query = " insert into customer (name, place, username, pin)" + " values (?, ?, ?, ?)";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1, name);
                preparedStmt.setString(2, place);
                preparedStmt.setString(3, username);
                preparedStmt.setInt(4, pin);
                preparedStmt.execute();


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    void login()
    {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();

        Template t = velocityEngine.getTemplate("index.vm");


        String usname;
        int pin;
        System.out.print("Enter Your username :");
        usname = sc.nextLine();
        System.out.print("Enter Your PIN :");
        pin = sc.nextInt();
        try {
            con = DriverManager.getConnection(MySQLURL, databseUserName, databasePassword);
            if (con != null) {
                logger.info("Database connection is successful for login");
                Statement stmt = con.createStatement();
                PreparedStatement statement = con.prepareStatement("select * from customer where username=? and pin=?");
                statement.setString(1, usname);
                statement.setInt(2, pin);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    name = rs.getString(2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        VelocityContext context = new VelocityContext();
        context.put("user", name);

        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        System.out.println(writer);
    }

}
