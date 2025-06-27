import java.util.Arrays;

/**
 * Represents a bank that manages checking accounts using an array.
 */
public class Bank {
    private static final int MAX_ACCOUNTS = 100; // or any reasonable limit
    private CheckingAccount[] accounts;
    private int accountCount;

    /**
     * Constructs a Bank with two sample accounts.
     */
    public Bank() {
        accounts = new CheckingAccount[MAX_ACCOUNTS];
        accountCount = 0;
        addAccount(new CheckingAccount("Zeus", 100, "1", Currency.USD, 0.05));
        addAccount(new CheckingAccount("Poseidon", 20, "2", Currency.GBP, 0.15));
        addAccount(new CheckingAccount("Hades", 200, "3", Currency.EUR, 0.03));
    }

    /**
     * Adds a new checking account to the bank.
     * @param account the CheckingAccount to add
     */
    public void addAccount(CheckingAccount account) {
        if (accountCount < accounts.length) {
            accounts[accountCount] = account;
            accountCount++;
        } else {
            System.out.println("Error: Maximum number of accounts reached.");
        }
    }

    /**
     * Retrieves a checking account by its ID.
     * @param id the account ID to search for
     * @return the CheckingAccount with the given ID, or null if not found
     */
    public CheckingAccount getAccountById(String id) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getId().equals(id)) {
                return accounts[i];
            }
        }
        return null;
    }

    /**
     * Gets all accounts as an array (for display or iteration).
     * @return an array of current CheckingAccount objects
     */
    public CheckingAccount[] getAccounts() {
        return Arrays.copyOf(accounts, accountCount);
    }

    /**
     * Transfers money from one account to another.
     * @param fromId the sender's account ID
     * @param toId the receiver's account ID
     * @param amount the amount to transfer
     * @return true if successful, false otherwise
     */
    public boolean transfer(String fromId, String toId, int amount) {
        CheckingAccount from = getAccountById(fromId);
        CheckingAccount to = getAccountById(toId);
        if (from == null || to == null) {
            System.out.println("Error: One or both accounts not found.");
            return false;
        }
        if (from.withdrawMoney(amount) != -1) {
            to.deposit(amount);
            System.out.println("Transferred " + amount + " from " + from.getName() + " to " + to.getName());
            System.out.println("New balance for " + from.getName() + ": " + from.getBalance());
            return true;
        }
        return false;
    }

    /**
     * Removes an account by ID.
     * @param id the account ID to remove
     * @return true if removed, false if not found
     */
    public boolean removeAccountById(String id) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getId().equals(id)) {
                // Shift remaining accounts left
                for (int j = i; j < accountCount - 1; j++) {
                    accounts[j] = accounts[j + 1];
                }
                accounts[accountCount - 1] = null;
                accountCount--;
                System.out.println("Account with ID " + id + " removed.");
                return true;
            }
        }
        System.out.println("Account with ID " + id + " not found.");
        return false;
    }
}