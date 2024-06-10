package src;
import java.time.LocalDate;

public class SavingsAccount extends Account {
    // Instance variables for transactions
    private LocalDate transactionDate;
    private String transactionType; // "DEP" for deposit, "WTH" for withdrawal
    private double transactionAmount;

    public SavingsAccount(String accountNumber) {
        super(accountNumber, "Savings", 0.25, 0.05, 0.0);
    }

    // Method to record a transaction
    private void recordTransaction(String type, double amount) {
        this.transactionDate = LocalDate.now();
        this.transactionType = type;
        this.transactionAmount = amount;
    }

    @Override
    public void withdrawal(double amount) {
        if (amount > 0 && amount + getServiceFee() <= getBalance()) {
            setBalance(getBalance() - amount - getServiceFee());
            recordTransaction("WTH", amount);
            System.out.println("Withdrawal of $" + amount + " was successful.");
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            setBalance(getBalance() + amount - getServiceFee());
            recordTransaction("DEP", amount);
            System.out.println("Deposit of $" + amount + " was successful.");
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    @Override
    public double balance() {
        return getBalance();
    }

    // Getters for transaction details
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }
}
