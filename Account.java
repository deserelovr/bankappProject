package src;
public abstract class Account implements AccountInterface {
    private String accountNumber;
    private String accountType;
    private double serviceFee;
    private double interestRate;
    private double overdraftFee;
    private double balance;

    public Account(String accountNumber, String accountType, double serviceFee, double interestRate, double overdraftFee) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.serviceFee = serviceFee;
        this.interestRate = interestRate;
        this.overdraftFee = overdraftFee;
        this.balance = 0.0;
    }

    // Getters and setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getOverdraftFee() {
        return overdraftFee;
    }

    public void setOverdraftFee(double overdraftFee) {
        this.overdraftFee = overdraftFee;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Abstract methods to be implemented by subclasses
    public abstract void withdrawal(double amount);
    public abstract void deposit(double amount);
    public abstract double balance();

    // Method to apply interest
    public void applyInterest() {
        balance += balance * interestRate;
    }
}
