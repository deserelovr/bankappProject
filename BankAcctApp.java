package src;

import javax.swing.*;

public class BankAcctApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BankAcctAppGUI();
            }
        });
    }
}
