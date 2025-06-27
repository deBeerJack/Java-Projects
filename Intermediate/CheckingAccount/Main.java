import java.util.Scanner;

/**
 * Demonstrates usage of the Bank and CheckingAccount classes with user input.
 */
public class Main {
    public static void main(String[] args) {
        Bank bankOfGods = new Bank();
        Scanner scanner = new Scanner(System.in);
        CheckingAccount currentAccount = null;

        // Overarching menu: select account
        while (currentAccount == null) {
            System.out.println("🏦 Welcome to Bank of the Gods!");
            System.out.println("Available accounts:");
            for (CheckingAccount acc : bankOfGods.getAccounts()) {
                System.out.println("ID: " + acc.getId() + " | Name: " + acc.getName());
            }
            System.out.print("Enter the account ID you would like to use: ");
            String id = scanner.nextLine();
            currentAccount = bankOfGods.getAccountById(id);
            if (currentAccount == null) {
                System.out.println("Account not found. Please try again.");
            }
        }

        // Main menu loop
        while (true) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. View account balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View transaction history");
            System.out.println("6. Close account");
            System.out.println("7. Switch account");
            System.out.println("8. Administrative features");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number from the menu.");
                continue; // Skip to next loop iteration
            }

            if (choice == 1) {
                System.out.println("Account balance: " + currentAccount.getBalanceWithCurrency());
            } else if (choice == 2) {
                System.out.print("Enter deposit amount: ");
                try {
                    int amount = Integer.parseInt(scanner.nextLine());
                    currentAccount.deposit(amount);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount. Please enter a valid integer.");
                }
            } else if (choice == 3) {
                System.out.print("Enter withdrawal amount: ");
                try {
                    int amount = Integer.parseInt(scanner.nextLine());
                    currentAccount.withdrawMoney(amount);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount. Please enter a valid integer.");
                }
            } else if (choice == 4) {
                System.out.print("Enter receiver account ID: ");
                String toId = scanner.nextLine();
                CheckingAccount toAccount = bankOfGods.getAccountById(toId);
                if (toAccount != null) {
                    System.out.print("Enter amount to transfer: ");
                    try {
                        int amount = Integer.parseInt(scanner.nextLine());
                        bankOfGods.transfer(currentAccount.getId(), toId, amount);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount. Please enter a valid integer.");
                    }
                } else {
                    System.out.println("Receiver account not found.");
                }
            } else if (choice == 5) {
                System.out.println("Transaction history for account " + currentAccount.getId() + ":");
                for (String entry : currentAccount.getTransactionHistory()) {
                    System.out.println("  " + entry);
                }
            } else if (choice == 6) {
                currentAccount.closeAccount();
            } else if (choice == 7) {
                // Switch account logic
                currentAccount = null;
                while (currentAccount == null) {
                    System.out.println("Available accounts:");
                    for (CheckingAccount acc : bankOfGods.getAccounts()) {
                        System.out.println("ID: " + acc.getId() + " | Name: " + acc.getName());
                    }
                    System.out.print("Enter the account ID you would like to use: ");
                    String id = scanner.nextLine();
                    currentAccount = bankOfGods.getAccountById(id);
                    if (currentAccount == null) {
                        System.out.println("Account not found. Please try again.");
                    }
                }
            } else if (choice == 8) {
                // Administrative Features Submenu
                while (true) {
                    System.out.println("\n--- Administrative Features ---");
                    System.out.println("1. View all accounts");
                    System.out.println("2. Apply fees to this account");
                    System.out.println("3. Apply monthly interest to this account");
                    System.out.println("4. Back to main menu");
                    System.out.print("Choose an option: ");

                    int adminChoice = -1;
                    try {
                        adminChoice = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number from the menu.");
                        continue;
                    }

                    if (adminChoice == 1) {
                        for (CheckingAccount acc : bankOfGods.getAccounts()) {
                            System.out.println(acc);
                        }
                    } else if (adminChoice == 2) {
                        currentAccount.applyFees();
                        System.out.println("New balance after fees: " + currentAccount.getBalanceWithCurrency());
                    } else if (adminChoice == 3) {
                        currentAccount.applyMonthlyInterest();
                        System.out.println("New balance after interest: " + currentAccount.getBalanceWithCurrency());
                    } else if (adminChoice == 4) {
                        break; // Back to main menu
                    } else {
                        System.out.println("Invalid option.");
                    }
                }
            } else if (choice == 9) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }
}