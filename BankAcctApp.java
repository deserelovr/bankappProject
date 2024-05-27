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

            // Ask if the user wants to add another person
            System.out.print("\nDo you want to add another Customer? (yes/no): ");
            String choice = scanner.nextLine().toLowerCase();
            if (!choice.equals("yes")) {
                addAnotherPerson = false;
            }
        }

        scanner.close();
    }
}
