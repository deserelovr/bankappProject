import java.util.Scanner;

public class DataEntry {
    private static Scanner scanner = new Scanner(System.in);

    // Entry of string data with no limit
    public static String enterString() {
        return scanner.nextLine();
    }

    // Entry of string data but with a length limit
    public static String enterString(int maxLength) {
        String input = scanner.nextLine();
        if (input.length() > maxLength) {
            System.out.println("String length exceeds the limit. Please enter again:");
            return enterString(maxLength);
        }
        return input;
    }

    // Entry of string data that must only have numeric values
    public static String enterNumericString() {
        String input = scanner.nextLine();
        if (!input.matches("\\d+")) {
            System.out.println("Input must contain only numeric values. Please enter again:");
            return enterNumericString();
        }
        return input;
    }

    // Entry of integer data
    public static int enterInteger() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer:");
            scanner.next();
        }
        return scanner.nextInt();
    }

    // Entry of integer data with a range limit
    public static int enterInteger(int min, int max) {
        int input;
        do {
            System.out.println("Please enter an integer between " + min + " and " + max + ":");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer:");
                scanner.next();
            }
            input = scanner.nextInt();
        } while (input < min || input > max);
        return input;
    }

    // Entry of decimal data
    public static double enterDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a decimal number:");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    // Entry of decimal data with a range limit
    public static double enterDouble(double min, double max) {
        double input;
        do {
            System.out.println("Please enter a decimal number between " + min + " and " + max + ":");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a decimal number:");
                scanner.next();
            }
            input = scanner.nextDouble();
        } while (input < min || input > max);
        return input;
    }

    // Entry of a date (not implemented for Phase 1)
    // You can implement date entry based on your requirements
}
