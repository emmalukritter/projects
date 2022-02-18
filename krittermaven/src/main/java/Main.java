import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Main extends Branch {
    Main() {
        logger = Logger.getLogger(Main.class.getName());
    }

    public static void main(String[] args) {
        loadlog4j();
        bank sampbank = new bank();
        Branch sampbranch = new Branch();
        Customer cs = new Customer();
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        Map<Integer, Character> map = new HashMap<Integer, Character>();
        char ch = 'y';
        System.out.println("Welcome");
        System.out.println("------------------------");

        while (ch == 'y' | ch == 'Y') {
            try {
                int y = random.nextInt(1000);
                System.out.println("Your Verification code is " + y);
                System.out.print("Enter your Verification code :");
                int x = sc.nextInt();
                System.out.println("------------------------");
                if (x == y) {
                    int choice;
                    System.out.print("1. BANK OPERATIONS \n2. BRANCH OPERATIONS \n3. Mapping\n4. CSV reader\n5. Customer\nEnter your choice :");
                    choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            int bchoice;
                            System.out.print("1. ADD NEW BANK \n2. PRINT ALL BANKS \n3. EDIT DETAILS\n4. LIST BRANCHES \nEnter your choice :");
                            bchoice = sc.nextInt();
                            switch (bchoice) {
                                case 1:
                                    sampbank.addBank();
                                    break;
                                case 2:
                                    sampbank.printBank();
                                    break;
                                case 3:
                                    sampbank.editdetails();
                                    break;
                                case 4:
                                    sampbank.bankbranch();
                                    break;

                            }
                            break;
                        case 2:
                            int brchoice;
                            System.out.print("1. ADD NEW BRANCH \n2. PRINT ALL BRANCHES \n3. PRINT EMPLOYEE ORDER\nEnter your choice :");
                            brchoice = sc.nextInt();
                            switch (brchoice) {
                                case 1:
                                    sampbranch.addbranch();
                                    break;
                                case 2:
                                    sampbranch.printBranch();
                                    break;
                                case 3:
                                    sampbranch.topemployees();
                                    break;

                            }
                            break;
                        case 3:
                            char c = 'y';
                            int i = 1;
                            while (c == 'y') {
                                System.out.print("Enter new alphabet:");
                                c = sc.next().charAt(0);
                                map.put(i, c);
                                System.out.print("Do you want to continue(y/n) :");
                                c = sc.next().charAt(0);
                                i++;
                            }
                            for (Map.Entry m : map.entrySet()) {
                                System.out.println(m.getKey() + " " + m.getValue());
                            }
                            System.out.println(map.get(3));
                            break;
                        case 4:
                            Scanner sch = new Scanner(new File("result.csv"));
                            sch.useDelimiter(",");   //sets the delimiter pattern
                            while (sc.hasNext())  //returns a boolean value
                            {
                                System.out.print(sch.next());  //find and returns the next complete token from this scanner
                            }
                            sch.close();  //closes the scannbreak;
                            break;
                        case 5:
                            int cschoice;
                            System.out.print("1. ADD CUSTOMER \n2. LOGIN \nEnter your choice :");
                            cschoice = sc.nextInt();
                            switch (cschoice) {
                                case 1:
                                    cs.addCustomer();
                                    break;
                                case 2:
                                    cs.login();
                                    break;
                            }

                            break;

                    }
                    //

                }
            } catch (Exception e) {
                System.out.println("Invailid Format............");
            }

            System.out.print("Do you want to continue Main Menu(y/n) :");
            sc.nextLine();
            ch = sc.next().charAt(0);


        }

    }
}
