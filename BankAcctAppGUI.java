package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class BankAcctAppGUI extends JFrame {
    private JTextField customerIdField, ssnField, lastNameField, firstNameField, streetField, cityField, zipField, phoneField, transactionAmountField, transactionDateField;
    private JComboBox<String> stateComboBox;
    private JRadioButton checkingRadioButton, savingsRadioButton, depositRadioButton, withdrawalRadioButton;
    private JButton addCustomerButton, displayButton, performTransactionButton, clearButton, applyInterestButton;
    private JTextArea displayArea;
    private JLabel statusLabel;

    private Customer customer;
    private Account account;

    public BankAcctAppGUI() {
        setTitle("Bank Account Application");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(17, 2));

        inputPanel.add(new JLabel("Customer ID:"));
        customerIdField = new JTextField();
        inputPanel.add(customerIdField);

        inputPanel.add(new JLabel("SSN (9 digits):"));
        ssnField = new JTextField();
        inputPanel.add(ssnField);

        inputPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        inputPanel.add(lastNameField);

        inputPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        inputPanel.add(firstNameField);

        inputPanel.add(new JLabel("Street:"));
        streetField = new JTextField();
        inputPanel.add(streetField);

        inputPanel.add(new JLabel("City:"));
        cityField = new JTextField();
        inputPanel.add(cityField);

        inputPanel.add(new JLabel("State:"));
        stateComboBox = new JComboBox<>(new String[]{
                "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD",
                "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
                "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"
        });
        inputPanel.add(stateComboBox);

        inputPanel.add(new JLabel("Zip (5 digits):"));
        zipField = new JTextField();
        inputPanel.add(zipField);

        inputPanel.add(new JLabel("Phone (10 digits):"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);

        // Account type radio buttons
        inputPanel.add(new JLabel("Account Type:"));
        JPanel accountTypePanel = new JPanel();
        ButtonGroup accountTypeGroup = new ButtonGroup();
        checkingRadioButton = new JRadioButton("Checking");
        savingsRadioButton = new JRadioButton("Savings");
        accountTypeGroup.add(checkingRadioButton);
        accountTypeGroup.add(savingsRadioButton);
        accountTypePanel.add(checkingRadioButton);
        accountTypePanel.add(savingsRadioButton);
        inputPanel.add(accountTypePanel);

        // Transaction type radio buttons
        inputPanel.add(new JLabel("Transaction Type:"));
        JPanel transactionTypePanel = new JPanel();
        ButtonGroup transactionTypeGroup = new ButtonGroup();
        depositRadioButton = new JRadioButton("Deposit");
        withdrawalRadioButton = new JRadioButton("Withdrawal");
        transactionTypeGroup.add(depositRadioButton);
        transactionTypeGroup.add(withdrawalRadioButton);
        transactionTypePanel.add(depositRadioButton);
        transactionTypePanel.add(withdrawalRadioButton);
        inputPanel.add(transactionTypePanel);

        // Transaction amount and date fields
        inputPanel.add(new JLabel("Transaction Amount:"));
        transactionAmountField = new JTextField();
        inputPanel.add(transactionAmountField);

        inputPanel.add(new JLabel("Transaction Date (YYYY-MM-DD):"));
        transactionDateField = new JTextField();
        inputPanel.add(transactionDateField);

        // Buttons
        addCustomerButton = new JButton("Add New Customer and Account");
        addCustomerButton.addActionListener(new AddCustomerListener());
        inputPanel.add(addCustomerButton);

        displayButton = new JButton("Display Customer and Account Data");
        displayButton.addActionListener(new DisplayListener());
        inputPanel.add(displayButton);

        performTransactionButton = new JButton("Perform Transaction");
        performTransactionButton.addActionListener(new PerformTransactionListener());
        inputPanel.add(performTransactionButton);

        applyInterestButton = new JButton("Apply Interest");
        applyInterestButton.addActionListener(new ApplyInterestListener());
        inputPanel.add(applyInterestButton);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ClearListener());
        inputPanel.add(clearButton);

        add(inputPanel, BorderLayout.NORTH);

        // Display area
        displayArea = new JTextArea(20, 50);
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Status label
        statusLabel = new JLabel("Status: ");
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class AddCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String customerId = customerIdField.getText();
                String ssn = ssnField.getText();
                if (ssn.length() != 9) throw new IllegalArgumentException("SSN must be 9 digits.");
                String lastName = lastNameField.getText();
                String firstName = firstNameField.getText();
                String street = streetField.getText();
                String city = cityField.getText();
                String state = (String) stateComboBox.getSelectedItem();
                String zip = zipField.getText();
                if (zip.length() != 5) throw new IllegalArgumentException("Zip code must be 5 digits.");
                String phone = phoneField.getText();
                if (phone.length() != 10) throw new IllegalArgumentException("Phone number must be 10 digits.");

                customer = new Customer(customerId, ssn, lastName, firstName, street, city, state, zip, phone);
                if (checkingRadioButton.isSelected()) {
                    account = new CheckingAccount("CHK" + customerId);
                } else if (savingsRadioButton.isSelected()) {
                    account = new SavingsAccount("SAV" + customerId);
                } else {
                    throw new IllegalArgumentException("Please select an account type.");
                }

                displayArea.setText("Customer and account added successfully.");
                statusLabel.setText("Success: Customer and account added successfully.");

            } catch (IllegalArgumentException ex) {
                displayArea.setText("Error: " + ex.getMessage());
                statusLabel.setText("Error: " + ex.getMessage());
            }
        }
    }

    private class DisplayListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (customer != null && account != null) {
                displayArea.setText("Customer ID: " + customer.getCustomerId() +
                        "\nSSN: " + customer.getCustomerSSN() +
                        "\nName: " + customer.getFirstName() + " " + customer.getLastName() +
                        "\nAddress: " + customer.getStreet() + ", " + customer.getCity() + ", " + customer.getState() + " " + customer.getZip() +
                        "\nPhone: " + customer.getPhone() +
                        "\n\nAccount Number: " + account.getAccountNumber() +
                        "\nAccount Type: " + account.getAccountType() +
                        "\nBalance: $" + account.getBalance());
                statusLabel.setText("Success: Customer and account data displayed.");
            } else {
                displayArea.setText("No customer or account data to display.");
                statusLabel.setText("Error: No customer or account data to display.");
            }
        }
    }

    private class PerformTransactionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (account == null) {
                displayArea.setText("Please add a customer and account first.");
                statusLabel.setText("Error: Please add a customer and account first.");
                return;
            }
            try {
                double amount = Double.parseDouble(transactionAmountField.getText());
                LocalDate date = LocalDate.parse(transactionDateField.getText());

                if (depositRadioButton.isSelected()) {
                    account.deposit(amount);
                } else if (withdrawalRadioButton.isSelected()) {
                    account.withdrawal(amount);
                } else {
                    throw new IllegalArgumentException("Please select a transaction type.");
                }

                displayArea.setText("Transaction successful.\n" +
                        "Customer ID: " + customer.getCustomerId() +
                        "\nAccount Number: " + account.getAccountNumber() +
                        "\nAccount Type: " + account.getAccountType() +
                        "\nTransaction Date: " + date +
                        "\nTransaction Type: " + (depositRadioButton.isSelected() ? "Deposit" : "Withdrawal") +
                        "\nTransaction Amount: $" + amount +
                        "\nBalance: $" + account.getBalance());
                statusLabel.setText("Success: Transaction completed.");

            } catch (Exception ex) {
                displayArea.setText("Error: " + ex.getMessage());
                statusLabel.setText("Error: " + ex.getMessage());
            }
        }
    }

    private class ApplyInterestListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (account == null) {
                displayArea.setText("Please add a customer and account first.");
                statusLabel.setText("Error: Please add a customer and account first.");
                return;
            }
            try {
                account.applyInterest();
                displayArea.setText("Interest applied successfully.\n" +
                        "Customer ID: " + customer.getCustomerId() +
                        "\nAccount Number: " + account.getAccountNumber() +
                        "\nAccount Type: " + account.getAccountType() +
                        "\nBalance: $" + account.getBalance());
                statusLabel.setText("Success: Interest applied.");
            } catch (Exception ex) {
                displayArea.setText("Error: " + ex.getMessage());
                statusLabel.setText("Error: " + ex.getMessage());
            }
        }
    }

    private class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            customerIdField.setText("");
            ssnField.setText("");
            lastNameField.setText("");
            firstNameField.setText("");
            streetField.setText("");
            cityField.setText("");
            zipField.setText("");
            phoneField.setText("");
            transactionAmountField.setText("");
            transactionDateField.setText("");
            stateComboBox.setSelectedIndex(0);
            checkingRadioButton.setSelected(false);
            savingsRadioButton.setSelected(false);
            depositRadioButton.setSelected(false);
            withdrawalRadioButton.setSelected(false);
            displayArea.setText("");
            statusLabel.setText("");
        }
    }

    public static void main(String[] args) {
        new BankAcctAppGUI();
    }
}
