import java.util.Scanner;

public class BankAcctApp {
    public static void main(String[] args) {
        // Welcome message
        System.out.println("Welcome to the Bank Account Application!");

        Scanner scanner = new Scanner(System.in);

        boolean addAnotherPerson = true;
        while (addAnotherPerson) {
            // Test data entry
            System.out.println("\nEnter customer details:");

            // Customer input validation loop
            Customer customer = null;
            while (customer == null) {
                try {
                    System.out.print("Customer ID: ");
                    String customerId = DataEntry.enterString(5);

                    System.out.print("Customer SSN (9 digits): ");
                    String customerSSN = DataEntry.enterNumericString();
                    if (customerSSN.length() != 9) {
                        throw new IllegalArgumentException("SSN must be 9 digits.");
                    }

                    System.out.print("Last Name: ");
                    String lastName = DataEntry.enterString(20);

                    System.out.print("First Name: ");
                    String firstName = DataEntry.enterString(15);

                    System.out.print("Street: ");
                    String street = DataEntry.enterString(20);

                    System.out.print("City: ");
                    String city = DataEntry.enterString(20);

                    System.out.print("State (2 characters): ");
                    String state = DataEntry.enterString(2);

                    System.out.print("Zip (5 digits): ");
                    String zip = DataEntry.enterNumericString();
                    if (zip.length() != 5) {
                        throw new IllegalArgumentException("Zip code must be 5 digits.");
                    }

                    System.out.print("Phone (10 digits): ");
                    String phone = DataEntry.enterNumericString();
                    if (phone.length() != 10) {
                        throw new IllegalArgumentException("Phone number must be 10 digits.");
                    }

                    // Create a customer object
                    customer = new Customer(customerId, customerSSN, lastName, firstName, street, city, state, zip, phone);

                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input: " + e.getMessage());
                }
            }

            // Display customer details
            System.out.println("\nCustomer details:");
            System.out.println(customer);

            // Create accounts for the customer
            CheckingAccount checkingAccount = new CheckingAccount("CHK001");
            SavingsAccount savingsAccount = new SavingsAccount("SAV001");

            boolean customerActive = true;
            while (customerActive) {
                // Display menu for transactions
                System.out.println("\nSelect an account to perform a transaction:");
                System.out.println("1. Checking Account");
                System.out.println("2. Savings Account");
                System.out.println("3. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        processAccount(checkingAccount, scanner);
                        break;
                    case 2:
                        processAccount(savingsAccount, scanner);
                        break;
                    case 3:
                        customerActive = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                        break;
                }
            }

            // Ask if the user wants to add another person
            System.out.print("\nDo you want to add another Customer? (yes/no): ");
            String choice = scanner.nextLine().toLowerCase();
            if (!choice.equals("yes")) {
                addAnotherPerson = false;
            }
        }

        scanner.close();
    }

    private static void processAccount(Account account, Scanner scanner) {
        boolean accountActive = true;
        while (accountActive) {
            // Display account menu
            System.out.println("\nAccount Menu");
            System.out.println("1. Deposit");
            System.out.println("2. Withdrawal");
            System.out.println("3. Check Balance");
            System.out.println("4. Apply Interest");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawalAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    account.withdrawal(withdrawalAmount);
                    break;
                case 3:
                    System.out.println("Current Balance: $" + account.balance());
                    break;
                case 4:
                    if (account instanceof SavingsAccount) {
                        ((SavingsAccount) account).applyInterest();
                    } else if (account instanceof CheckingAccount) {
                        ((CheckingAccount) account).applyInterest();
                    }
                    break;
                case 5:
                    accountActive = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    break;
            }
        }
    }
}
