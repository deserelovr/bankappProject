package src;
import java.time.LocalDate;

public class CheckingAccount extends Account {
    public CheckingAccount(String accountNumber) {
        super(accountNumber, "Checking", 0.50, 0.02, 30.00);
    }

    @Override
    public void withdrawal(double amount) {
        if (amount > 0) {
            setBalance(getBalance() - amount - getServiceFee());
            if (getBalance() < 0) {
                setBalance(getBalance() - getOverdraftFee());
                System.out.println("Overdraft fee charged.");
            }
            System.out.println("Withdrawal of $" + amount + " was successful.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            setBalance(getBalance() + amount - getServiceFee());
            System.out.println("Deposit of $" + amount + " was successful.");
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    @Override
    public double balance() {
        return getBalance();
    }
}
