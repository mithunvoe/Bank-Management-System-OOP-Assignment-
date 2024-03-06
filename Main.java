import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

public class Main {
    static public Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        Vector<Bank> allBanks = new Vector<Bank>();
        Bank bank1 = new Bank("BRAC");
        Bank bank2 = new Bank("Al-Arafah");
        allBanks.add(bank1);
        allBanks.add(bank2);
        while (true) {
            clear();
            System.out.println("All Banks: ");
            for (int i = 0; i < allBanks.size(); i++) {
                System.out.println(i + 1 + ". " + allBanks.get(i).getBankName());
            }  
            System.out.println(allBanks.size() + 1 + ". Exit");
            int query;
            query = scanner.nextInt();

            if (query == (allBanks.size() + 1))
                break;

            try {
                allBanks.get(query-1).inBank();
            }
            catch(Exception e) {
                System.out.println("Invalid Input");
            }
        }

    }
    static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}