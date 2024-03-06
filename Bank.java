
import java.sql.SQLOutput;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Bank {

    static public Scanner scanner = new Scanner(System.in);
    static public String menu[] = { "1. Login", "2. Create Account", "3. Go Back" };
    public Client newCustomer;
    private String bankName;
    int accNo = 1;
    int designation;

    public Bank(String name) {
        bankName = name;
    }

    Map<String, Client> clientMap = new HashMap<String, Client>();
    Map<Integer, Client> clientMapAccno = new HashMap<Integer, Client>();

    void createAccount(String name, String email, String phoneNo, String password, boolean isCustomer,
            boolean isSinglePerson, boolean isSavings, boolean isInterest, int designation, Map clientMapAccno)
            throws InterruptedException {
        if (isCustomer)
            newCustomer = new Customer(name, email, phoneNo, accNo++, password, isCustomer, isSinglePerson, isSavings,
                    isInterest, 0, clientMapAccno);
        else
            newCustomer = new Employee(name, email, phoneNo, accNo++, password, isCustomer, false, false, false,
                    designation, clientMapAccno);
        clientMap.put(email, newCustomer);
        clientMapAccno.put(accNo - 1, newCustomer);

    }

    int loginPossible(String email, String password) {
        if (!clientMap.containsKey(email))
            return -1;
        if (!clientMap.get(email).passwordMatch(password))
            return -2;
        return 1;
    }

    public void inBank() throws InterruptedException {
        while (true) {
            Main.clear();
            System.out.println("Welcome to " + getBankName());
            for (String s : menu)
                System.out.println(s);
            int query;
            query = scanner.nextInt();
            Main.clear();
            if (query == 1) {
                Main.clear();
                System.out.println("Enter Email: ");
                String email = scanner.nextLine();
                email = scanner.nextLine();
                Main.clear();

                System.out.println("Enter Pin: ");
                String password = scanner.nextLine();
                Main.clear();
                int loginResponse = loginPossible(email, password);

                Main.clear();
                if (loginResponse == -1) {
                    System.out.println("Invalid Email");
                    TimeUnit.SECONDS.sleep(1);

                } else if (loginResponse == -2) {
                    System.out.println("Invalid PIN");
                    TimeUnit.SECONDS.sleep(1);

                } else {
                    var customer = clientMap.get(email);
                    customer.inClient();
                }

            } else if (query == 2) {
                String name;
                String email;
                String phoneNo;
                String password;
                boolean isCustomer = false;
                boolean isSinglePerson = false;
                boolean isSavings = false;
                boolean isInterest = false;
                System.out.println("Enter Name: ");
                name = scanner.nextLine();
                name = scanner.nextLine();
                Main.clear();

                System.out.println("Enter Email: ");
                email = scanner.nextLine();

                Main.clear();

                if (clientMap.containsKey(email) && clientMap.get(email).isCustomer()) {
                    System.out.println("Already an account available with this email.");

                    continue;
                }

                System.out.println("Enter Phone No.: ");
                phoneNo = scanner.nextLine();

                Main.clear();

                System.out.println("What type of account do you want to create?");
                System.out.println("1. Customer\n2. Employee");
                query = scanner.nextInt();
                if (query == 1)
                    isCustomer = true;
                else if (query == 2)
                    isCustomer = false;
                else {
                    System.out.println("Invalid Input");
                    continue;
                }
                if (isCustomer) {
                    Main.clear();

                    System.out.println("1. Single Person.\n2. Organization");
                    query = scanner.nextInt();
                    Main.clear();

                    if (query == 1)
                        isSinglePerson = true;
                    else if (query == 2)
                        isSinglePerson = false;
                    else {
                        System.out.println("Invalid Input");

                        Main.clear();
                        continue;
                    }
                    Main.clear();

                    System.out.println("1. Savings Account.\n2. Salary Account");
                    query = scanner.nextInt();
                    Main.clear();

                    if (query == 1)
                        isSavings = true;
                    else if (query == 2)
                        isSavings = false;
                    else {

                        System.out.println("Invalid Input");
                        Main.clear();

                        continue;
                    }
                    Main.clear();

                    System.out.println("1. Interest Scheme\n2. Tax-Cut Scheme");
                    query = scanner.nextInt();
                    Main.clear();

                    if (query == 1)
                        isInterest = true;
                    else if (query == 2)
                        isInterest = false;
                    else {
                        System.out.println("Invalid Input");

                        continue;
                    }
                    Main.clear();
                } else {
                    Main.clear();
                    System.out.println("What type of account do you want to create?");
                    System.out.println("1. Manager\n2. Officer\n3. Trainee");
                    designation = scanner.nextInt();
                    Main.clear();

                    if (designation < 1 || designation > 3) {
                        System.out.println("Invalid Input");
                        Main.clear();

                        continue;
                    }
                }
                System.out.println("Set Password: ");
                password = scanner.nextLine();
                password = scanner.nextLine();
                Main.clear();
                try {
                    createAccount(name, email, phoneNo, password, isCustomer, isSinglePerson, isSavings, isInterest,
                            designation, clientMapAccno);
                    System.out.println("Account Created Successfully\nPlease login to access your account");
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    System.out.println("Couldn't create account");
                }
            }

            else {
                break;
            }

        }
    }

    public String getBankName() {
        return bankName;
    }
}