import java.util.Scanner;

/**
 * Demonstrates usage of the Bank and CheckingAccount classes with user input.
 */
public class Main {
    public static void main(String[] args) {
        Bank bankOfGods = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. View all accounts");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View transaction history");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                for (CheckingAccount acc : bankOfGods.getAccounts()) {
                    System.out.println(acc);
                }
            } else if (choice == 2) {
                System.out.print("Enter account ID: ");
                String id = scanner.nextLine();
                CheckingAccount acc = bankOfGods.getAccountById(id);
                if (acc != null) {
                    System.out.print("Enter deposit amount: ");
                    int amount = scanner.nextInt();
                    scanner.nextLine();
                    acc.deposit(amount);
                } else {
                    System.out.println("Account not found.");
                }
            } else if (choice == 3) {
                System.out.print("Enter account ID: ");
                String id = scanner.nextLine();
                CheckingAccount acc = bankOfGods.getAccountById(id);
                if (acc != null) {
                    System.out.print("Enter withdrawal amount: ");
                    int amount = scanner.nextInt();
                    scanner.nextLine();
                    acc.withdrawMoney(amount);
                } else {
                    System.out.println("Account not found.");
                }
            } else if (choice == 4) {
                System.out.print("Enter sender account ID: ");
                String fromId = scanner.nextLine();
                System.out.print("Enter receiver account ID: ");
                String toId = scanner.nextLine();
                System.out.print("Enter amount to transfer: ");
                int amount = scanner.nextInt();
                scanner.nextLine();
                bankOfGods.transfer(fromId, toId, amount);
            } else if (choice == 5) {
                System.out.print("Enter account ID: ");
                String id = scanner.nextLine();
                CheckingAccount acc = bankOfGods.getAccountById(id);
                if (acc != null) {
                    System.out.println("Transaction history for account " + acc.getId() + ":");
                    for (String entry : acc.getTransactionHistory()) {
                        System.out.println("  " + entry);
                    }
                } else {
                    System.out.println("Account not found.");
                }
            } else if (choice == 6) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }
}