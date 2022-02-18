import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.*;
import java.util.Scanner;

public class bank {
    public static void loadlog4j(){
        String logpath=System.getProperty("user.dir")+"/log4j.properties";
        PropertyConfigurator.configure(logpath);
    }
    public static Logger logger=null;
    public bank(){
        logger = Logger.getLogger(bank.class.getName());
    }
    Scanner sc = new Scanner(System.in);
    String bankid = "";
    String bname = "";
    String hoffice = "";
    int totalemp;
    String MySQLURL = "jdbc:mysql://localhost:3306/bank?useSSL=false";
    String databseUserName = "root";
    String databasePassword = "emmalujsp";
    Connection con = null;

    void addBank() {
        printBank();
        System.out.print("Enter Bank id :");
        bankid = sc.nextLine();
        System.out.print("Enter Bank Name :");
        bname = sc.nextLine();
        System.out.print("Enter Bank head office :");
        hoffice = sc.nextLine();
        System.out.print("Enter Total number of employees :");
        totalemp = sc.nextInt();
        try {
            con = DriverManager.getConnection(MySQLURL, databseUserName, databasePassword);
            if (con != null) {
                logger.info("Database connection is successful  for addd bank!!!!");
                String query = " insert into bankdetails (bankid, bname, hoffice, totalemp)" + " values (?, ?, ?, ?)";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1, bankid);
                preparedStmt.setString(2, bname);
                preparedStmt.setString(3, hoffice);
                preparedStmt.setInt(4, totalemp);
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

    void printBank() {
        try {
            con = DriverManager.getConnection(MySQLURL, databseUserName, databasePassword);
            if (con != null) {
                logger.info("Database connection is successful fro print bank!!!!");
                Statement stmt = con.createStatement();
                System.out.println("---------------------------------------------------------------------------------------------");
                System.out.printf("%5s %30s %10s %15s ", "BANK ID", "NAME", "PLACE", "EMPLOYEES");
                System.out.println();
                System.out.println("---------------------------------------------------------------------------------------------");
                ResultSet rs = stmt.executeQuery("select * from bankdetails");
                while (rs.next()) {
                    System.out.printf("%5s %30s %10s %15s ", rs.getString(1),rs.getString(2), rs.getString(3),  rs.getString(4));
                    System.out.println();
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
    }

    void editdetails() {
        printBank();
        System.out.print("Enter the Bank id to edit :");
        bankid = sc.nextLine();
        try {
            con = DriverManager.getConnection(MySQLURL, databseUserName, databasePassword);
            if (con != null) {
                logger.info("Database connection is successful for edit details !!!!");
                Statement stmt = con.createStatement();
                PreparedStatement statement = con.prepareStatement("select * from bankdetails where bankid=?");
                statement.setString(1, bankid);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4));
                    totalemp = rs.getInt(4);
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

        System.out.print("Enter Total number of new employees :");
        int newmeb = sc.nextInt();
        totalemp = totalemp + newmeb;
        try {
            con = DriverManager.getConnection(MySQLURL, databseUserName, databasePassword);
            if (con != null) {
                logger.info("Database connection is successful for editing!!!!");
                String sql = "UPDATE  bankdetails set totalemp=? where bankid=?";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1, totalemp);
                statement.setString(2, bankid);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("UPDATED THE DETAILS");
                }
                else
                {
                    logger.error("Wrong Bank id ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    void bankbranch() {
        printBank();
        System.out.print("Enter the Bank for branch :");
        bankid = sc.nextLine();
        try {
            con = DriverManager.getConnection(MySQLURL, databseUserName, databasePassword);
            if (con != null) {
                Statement stmt = con.createStatement();
                PreparedStatement statement = con.prepareStatement("select count(*) as cnt from branchdetails where bid=?  group by bid");
                statement.setString(1, bankid);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    System.out.print("Number of Branches for this bank :");
                    System.out.println(rs.getInt("cnt"));
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
        try {
            con = DriverManager.getConnection(MySQLURL, databseUserName, databasePassword);
            if (con != null) {
                Statement stmt = con.createStatement();
                PreparedStatement statement = con.prepareStatement("select * from branchdetails where bid=?");
                statement.setString(1, bankid);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4));
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
    }
}
