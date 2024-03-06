import java.util.Map;

public class Employee extends Client{
    Customer customer;
    boolean alsoCustomer = false;
    Employee(String name, String email, String phoneNo, int accNo, String password, boolean isCustomer, boolean isSinglePerson, boolean isSavings, boolean isInterest, int designation, Map clientMapAccno) {
        super(name, email, phoneNo, accNo, password, isCustomer, isSinglePerson, isSavings, isInterest, designation, clientMapAccno);
    }
    class Manager {

        String Designation = "Manager";
    }

    class Officer {
        String Designation = "Officer";
    }

    class Trainee {
        String Designation = "Trainee";
    }

}