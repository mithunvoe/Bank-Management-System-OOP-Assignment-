//import java.util.Scanner;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Client {
    private final String name;
    private final String email;
    private final String phoneNo;
    private final int accNo;
    private final String password;
    private int monee = 0;
    private boolean isCustomer = false;
    private boolean isSinglePerson;
    private boolean isSavings;
    private Customer customer;
    private Employee employee;
    private double ROI;
    private int designation;

    static public Scanner scanner = new Scanner(System.in);
    Map<Integer, Client> clientMapAccno;
    // Client(){}

    Client(String name, String email, String phoneNo, int accNo, String password, boolean isCustomer,
            boolean isSinglePerson, boolean isSavings, boolean isInterest, int designation, Map clientMapAccno) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.accNo = accNo;
        this.password = password;
        this.isCustomer = isCustomer;
        this.isSinglePerson = isSinglePerson;
        this.isCustomer = isCustomer;
        this.isSavings = isSavings;
        this.ROI = (isInterest) ? (2.0 / 100.0) : (2.5 / 100.0);
        this.designation = designation;
        this.clientMapAccno = clientMapAccno;
    }
    // Client(){}

    public void inClient() throws InterruptedException {
        if (!isCustomer) {
            displayInfo();
            // TimeUnit.SECONDS.sleep(2);
            int query = scanner.nextInt();
            return;
        }
        while (true) {

            displayInfo();

            int query = scanner.nextInt();
            int amount;
            Main.clear();
            if (!isCustomer) {
                Main.clear();
                break;
            }

            if (isCustomer) {
                if (query == 1) {
                    System.out.println("Which method do you prefer?");
                    System.out.println("1. Bkash\n2. EFT\n3. Bank Receipt");
                    query = scanner.nextInt();
                    Main.clear();
                    System.out.println("Enter amount to add: ");
                    amount = scanner.nextInt();
                    Main.clear();

                    if (query == 2) {
                        System.out.println("Enter Account No. from which you want to transfer money: ");
                        int acc = scanner.nextInt();
                        Main.clear();

                        if (!clientMapAccno.containsKey(acc)) {
                            System.out.println("Account not found");
                            TimeUnit.SECONDS.sleep(2);
                            Main.clear();

                            continue;
                        }

                        if (!clientMapAccno.get(acc).withdrawMonee(amount)) {
                            System.out.println("Insufficient balance in senders account");
                            TimeUnit.SECONDS.sleep(2);
                            Main.clear();

                            continue;
                        }
                    }
                    addMonee(amount);
                    Main.clear();
                    System.out.println("Current Balance " + getMonee());
                    TimeUnit.SECONDS.sleep(2);
                } else if (query == 2) {
                    System.out.println("Which method do you prefer?");
                    System.out.println("1. Direct Cheque\n2. Bkash Wallet\n3. Credit Card");
                    query = scanner.nextInt();
                    Main.clear();
                    System.out.println("Enter amount to withdraw: ");
                    amount = scanner.nextInt();

                    boolean flag = withdrawMonee(amount);
                    Main.clear();
                    if (!flag) {
                        System.out.println("Insufficient Balance");
                        TimeUnit.SECONDS.sleep(2);
                        Main.clear();
                        continue;
                    }
                    System.out.println("Current balance " + getMonee());
                    TimeUnit.SECONDS.sleep(2);
                } else if (query == 3) {
                    System.out.println("After how many years?");
                    amount = scanner.nextInt();
                    Main.clear();
                    System.out.println(
                            "Total amount of money after " + amount + " years: " + totalMoneyAfterYears(amount));
                    TimeUnit.SECONDS.sleep(2);
                } else
                    break;
            } else {
                // System.out.println(33);
                TimeUnit.SECONDS.sleep(2);

                break;
            }
        }
    }

    public boolean withdrawMonee(int amount) {
        if (monee < amount)
            return false;
        monee -= amount;
        // System.out.println(getName());
        return true;
    }

    public double totalMoneyAfterYears(int year) {
        return getMonee() * Math.pow(1.0 + ROI, year);
    }

    public Boolean passwordMatch(String password) {
        return Objects.equals(this.password, password);
    }

    public void displayInfo() {
        Main.clear();
        System.out.println("Name: " + getName());
        System.out.println("Account No: " + getAccNo());
        System.out.println("Account Type: " + ((!isCustomer)
                ? ("Employee " + ((designation == 1) ? "Manager" : (designation == 2) ? "Officer" : "Trainee"))
                : ("Customer " + ((designation != 0) ? "and Employee " : "") + "("
                        + ((isSinglePerson) ? "Single Person " : "Organization ")
                        + ((isSavings) ? "Savings account" : "Salary Account") + ")")));
        System.out.println("Email: " + getEmail());
        System.out.println("Phone: " + getPhoneNo());
        System.out.println("Balance: " + getMonee());
        System.out.println("\n");
        System.out.println();
        if (isCustomer) {
            System.out.println("1. Add Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Total Amount of Money After Some Years");
        }
        System.out.println(((isCustomer) ? "4. Logout" : "1. Logout"));

        System.out.println(" \n \n");
    }

    public void addMonee(int amount) {
        monee += amount;
    }

    public int getMonee() {
        return monee;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public int getAccNo() {
        return accNo;
    }

    public boolean isCustomer() {
        return isCustomer;
    }

}
