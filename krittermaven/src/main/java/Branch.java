import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Branch extends bank {
    String branchid;
    String bid;
    String address;
    int employee;

    void addbranch() {
        printBank();
        System.out.print("Enter the Bank id to create branch :");
        sc.nextLine();
        bid = sc.nextLine();
        System.out.print("Enter Branch id :");
        branchid = sc.nextLine();
        System.out.print("Enter Branch Address :");
        address = sc.nextLine();
        System.out.print("Enter number of employees  :");
        employee = sc.nextInt();
        try {
            con = DriverManager.getConnection(MySQLURL, databseUserName, databasePassword);
            if (con != null) {
                logger.info("Database connection is successful for adding branch !!!!");
                String query = " insert into branchdetails (branchid, bid, address, noemp)" + " values (?, ?, ?, ?)";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1, branchid);
                preparedStmt.setString(2, bid);
                preparedStmt.setString(3, address);
                preparedStmt.setInt(4, employee);
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

    void printBranch() {
        try {
            con = DriverManager.getConnection(MySQLURL, databseUserName, databasePassword);
            if (con != null) {
                logger.info("Database connection is successful !!!!");
                Statement stmt = con.createStatement();
                System.out.println("---------------------------------------------------------------------------------------------");
                System.out.printf("%5s %30s %10s %15s ", "BRANCH ID", "BANK ID", "PLACE", "EMPLOYEES");
                System.out.println();
                System.out.println("---------------------------------------------------------------------------------------------");
                ResultSet rs = stmt.executeQuery("select * from branchdetails");
                while (rs.next()) {
                    System.out.printf("%5s %30s %10s %15s ", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
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

    void topemployees() {

        try {
            con = DriverManager.getConnection(MySQLURL, databseUserName, databasePassword);
            if (con != null) {
                Statement stmt = con.createStatement();
                PreparedStatement statement = con.prepareStatement("select * from branchdetails order by noemp desc limit 1");
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
        try {
            con = DriverManager.getConnection(MySQLURL, databseUserName, databasePassword);
            if (con != null) {
                System.out.println("----------------------------------------------");
                Statement stmt = con.createStatement();
                PreparedStatement statement = con.prepareStatement("select * from branchdetails order by noemp");
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
