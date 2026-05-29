import java.util.Scanner;

public class BankView {
    private Scanner sc = new Scanner(System.in);

    public int showMenu() {
        System.out.println("\n--- Banking System ---");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Check Balance");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
        return sc.nextInt();
    }    // Get user input for account number, name, and amount

    public int getAccountNumber() {
        System.out.print("Enter Account Number: ");
        return sc.nextInt();
    }

    public String getName() {
        System.out.print("Enter Name: ");
        return sc.next();
    }

    public double getAmount() {
        System.out.print("Enter Amount: ");
        return sc.nextDouble();
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showBalance(double balance) {
        System.out.println("Current Balance: " + balance);
    }
}