import java.util.List;
import java.util.ArrayList;

/**
 * Represents a checking account with basic banking operations.
 */
public class CheckingAccount {
    private static final int FEE_AMOUNT = 5; // Use a constant for fees

    private String name;
    private int balance;
    private String id;
    private double interestRate;
    private boolean isClosed = false;
    private Currency currency;
    private List<String> transactionHistory = new ArrayList<>();

    /**
     * Constructs a CheckingAccount.
     * @param inputName the account holder's name
     * @param inputBalance the initial balance
     * @param inputId the account ID
     * @param inputCurrency the account currency
     * @param inputInterestRate the account's interest rate
     */
    public CheckingAccount(String inputName, int inputBalance, String inputId, Currency inputCurrency, double inputInterestRate){
        this.name = inputName;
        this.balance = inputBalance;
        this.id = inputId;
        this.interestRate = inputInterestRate;
        this.currency = inputCurrency;
        transactionHistory.add("Account created with balance: " + balance + " " + currency + " and interest rate: " + interestRate);
    }

    /**
     * Gets the current balance.
     * @return the balance, or -1 if account is closed
     */
    public int getBalance(){
        if (isClosed) {
            System.out.println("Error: account closed");
            return -1;
        }
        return this.balance;
    }

    /**
     * Sets the account balance.
     * @param newBalance the new balance
     */
    public void setBalance(int newBalance){
        if (isClosed) {
            System.out.println("Error: account closed");
            return;
        }
        if (newBalance < 0) {
            System.out.println("Error: balance cannot be negative");
            return;
        }
        transactionHistory.add("Balance set to: " + newBalance + " " + currency);
        this.balance = newBalance;
    }

    /**
     * Calculates the monthly interest.
     * @return the interest amount, or 0.0 if account is closed
     */
    public double getMonthlyInterest(){
        if (isClosed) {
            System.out.println("Error: account closed");
            return 0.0;
        }
        return this.interestRate * this.balance;
    }

    /**
     * Closes the account if balance is zero.
     */
    public void closeAccount(){
        if (this.balance > 0){
            System.out.println("There is still the amount of " + this.balance + " left in this account. Please withdraw the amount before closing the account.");
            return;
        } else if (this.balance == 0) {
            isClosed = true;
            transactionHistory.add("Account closed.");
            System.out.println("Account closed successfully.");
        } else {
            System.out.println("Please settle your debt before closing the account.");
        }
    }

    /**
     * Withdraws money from the account.
     * @param amountToWithdraw the amount to withdraw
     * @return the new balance, or -1 if error
     */
    public int withdrawMoney(int amountToWithdraw){
        if (isClosed) {
            System.out.println("Error: account closed");
            return -1;
        }
        if (amountToWithdraw <= 0) {
            System.out.println("Error: withdrawal amount must be positive");
            return -1;
        }
        if (amountToWithdraw > this.balance) {
            System.out.println("Error: insufficient funds");
            return -1;
        }
        System.out.println("Withdrawing " + amountToWithdraw + " from " + this.balance);
        this.setBalance(this.balance - amountToWithdraw);
        transactionHistory.add("Withdrew: " + amountToWithdraw + " " + currency);
        System.out.println("The new balance after the withdrawal is " + this.balance);
        return this.balance;
    }

        /**
     * Deposits money into the account.
     * @param amount the amount to deposit
     */
    public void deposit(int amount) {
        if (isClosed) {
            System.out.println("Error: account closed");
            return;
        }
        if (amount <= 0) {
            System.out.println("Error: deposit amount must be positive");
            return;
        }
        this.balance += amount;
        transactionHistory.add("Deposited: " + amount + " " + currency);
    }

    /**
     * Applies a fee to the account.
     * @return the new balance, or -1 if account is closed
     */
    public int applyFees(){
        if (isClosed) {
            System.out.println("Error: account closed");
            return -1;
        }
        this.balance = this.balance - FEE_AMOUNT;
        transactionHistory.add("Fee applied: -" + FEE_AMOUNT + " " + currency);
        return this.balance;
    }

    /**
     * Gets the balance with currency as a string.
     * @return balance and currency, or error message if closed
     */
    public String getBalanceWithCurrency() {
        if (isClosed) {
            return "Error: account closed";
        }
        return this.balance + " " + this.currency;
    }

    /**
     * Gets the transaction history.
     * @return a list of transaction descriptions
     */
    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    /**
     * Gets the account holder's name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the account ID.
     * @return the account ID
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the account currency.
     * @return the currency
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Gets the account's interest rate.
     * @return the interest rate
     */
    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public String toString() {
        return "Account[" + name + ", ID: " + id + ", Balance: " + balance + " " + currency + "]";
    }
}