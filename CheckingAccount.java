package src;

import java.time.LocalDate;

public class CheckingAccount extends Account implements AccountInterface {
    private LocalDate transactionDate;
    private String transactionType; // "DEP" for deposit, "WTH" for withdrawal
    private double transactionAmount;

    public CheckingAccount(String accountNumber) {
        super(accountNumber, "Checking", 0.50, 0.02, 30.00); // Interest rate is set to 2%
    }

    private void recordTransaction(String type, double amount) {
        this.transactionDate = LocalDate.now();
        this.transactionType = type;
        this.transactionAmount = amount;
    }

    @Override
    public void withdrawal(double amount) {
        if (amount > 0) {
            double totalAmount = amount + getServiceFee();
            if (getBalance() - totalAmount < 0) {
                setBalance(getBalance() - totalAmount - getOverdraftFee());
                System.out.println("Overdraft fee of $" + getOverdraftFee() + " charged.");
            } else {
                setBalance(getBalance() - totalAmount);
            }
            recordTransaction("WTH", amount);
            System.out.println("Withdrawal of $" + amount + " was successful.");
        } else {
            System.out.println("Invalid withdrawal amount.");
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

    public void applyInterest() {
        double interestEarned = getBalance() * getInterestRate();
        setBalance(getBalance() + interestEarned);
        System.out.println("Interest of $" + interestEarned + " was applied to the account.");
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    // Override setInterestRate to set the interest rate to 2%
    @Override
    public void setInterestRate(double interestRate) {
        super.setInterestRate(0.02); // 2%
    }
}