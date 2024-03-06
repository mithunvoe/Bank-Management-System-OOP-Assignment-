import java.util.Map;

public class Customer extends Client{
    boolean isSinglePerson;
    SinglePerson sp;
    Organization oz;
    private String password;

    Customer(String name, String email, String phoneNo, int accNo, String password, boolean isCustomer, boolean isSinglePerson, boolean isSavings, boolean isInterest, int designation, Map clientMapAccno) {

        super(name, email, phoneNo, accNo, password, isCustomer, isSinglePerson,  isSavings, isInterest, designation, clientMapAccno);
        if (isSinglePerson)
            sp = new SinglePerson();
        else
            oz = new Organization();
        this.password = password;
    }

    class SinglePerson {
        private String BIN;

        SinglePerson() {
            this.BIN = password;
        }

    }

    class Organization {
        private String TIN;

        Organization() {
            this.TIN = password;
        }
    }
}