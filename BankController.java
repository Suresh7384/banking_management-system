import java.io.*;
import java.util.HashMap;

public class BankController {

    private static final String FILE_NAME = "accounts.txt";

    public static void main(String[] args) {

        HashMap<Integer, Account> accounts = loadAccounts();
        BankView view = new BankView();

        while (true) {
            int choice = view.showMenu();

            switch (choice) {

                case 1: // Create Account
                    int accNo = view.getAccountNumber();
                    String name = view.getName();

                    if (accounts.containsKey(accNo)) {
                        view.showMessage("Account already exists!");
                    } else {
                        accounts.put(accNo, new Account(accNo, name));
                        saveAccounts(accounts);
                        view.showMessage("Account created!");
                    }
                    break;

                case 2: // Deposit
                    accNo = view.getAccountNumber();
                    if (accounts.containsKey(accNo)) {
                        double amt = view.getAmount();
                        accounts.get(accNo).deposit(amt);
                        saveAccounts(accounts);
                        view.showMessage("Deposit successful!");
                    } else {
                        view.showMessage("Account not found!");
                    }
                    break;

                case 3: // Withdraw
                    accNo = view.getAccountNumber();
                    if (accounts.containsKey(accNo)) {
                        double amt = view.getAmount();
                        boolean success = accounts.get(accNo).withdraw(amt);

                        if (success) {
                            saveAccounts(accounts);
                            view.showMessage("Withdrawal successful!");
                        } else {
                            view.showMessage("Insufficient balance!");
                        }
                    } else {
                        view.showMessage("Account not found!");
                    }
                    break;

                case 4: // Check Balance
                    accNo = view.getAccountNumber();
                    if (accounts.containsKey(accNo)) {
                        view.showBalance(accounts.get(accNo).getBalance());
                    } else {
                        view.showMessage("Account not found!");
                    }
                    break;

                case 5:
                    saveAccounts(accounts);
                    view.showMessage("Data saved. Exiting...");
                    System.exit(0);

                default:
                    view.showMessage("Invalid choice!");
            }
        }
    }

    // 🔽 LOAD FROM TXT FILE
    private static HashMap<Integer, Account> loadAccounts() {
        HashMap<Integer, Account> accounts = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                int accNo = Integer.parseInt(data[0]);
                String name = data[1];
                double balance = Double.parseDouble(data[2]);

                Account acc = new Account(accNo, name);
                acc.setBalance(balance);

                accounts.put(accNo, acc);
            }

        } catch (IOException e) {
            System.out.println("No previous data found.");
        }

        return accounts;
    }

    // 🔽 SAVE TO TXT FILE
    private static void saveAccounts(HashMap<Integer, Account> accounts) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Account acc : accounts.values()) {
                writer.write(acc.getAccountNumber() + "," +
                             acc.getName() + "," +
                             acc.getBalance());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving data!");
        }
    }
}