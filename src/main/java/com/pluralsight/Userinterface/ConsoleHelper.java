package com.pluralsight.Userinterface;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class ConsoleHelper {
    private static Scanner scanner = new Scanner(System.in);

    //  Prompts the user for a string input
    public static String promptForString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    //Prompts the user for an integer input and validates it.
    public static int promptForInt(String prompt) {
        int result;

        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim(); // read inside the loop

            try {
                result = Integer.parseInt(input);
                return result; // valid integer entered
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    public static double promptForDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static LocalDate promptForDate(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String dateAsString = scanner.nextLine();
                return LocalDate.parse(dateAsString);
            } catch (Exception e) {
                System.out.println("Invalid date. Use YYYY-MM-DD.");
            }
        }
    }

    public static LocalTime promptForTime(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String timeAsString = scanner.nextLine();
                return LocalTime.parse(timeAsString);
            } catch (Exception e) {
                System.out.println("Invalid time. Use HH:MM:SS.");
            }
        }
    }

    public static void displayOptions(String header, String[] options) {
        System.out.println("\n" + header);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ") " + options[i]);
        }
    }

    // Displays simple list of options to the console.
    public static void displayOptions(String header, String[] options, String suffix) {
        System.out.println("\n" + header);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ") " + options[i] + suffix);
        }
    }

    // Displays a numbered list of options with a valid (like price, size, or unit).
    public static int getValidatedOption(String prompt, int max, java.util.function.IntPredicate validator) {
        int option;
        while (true) {
            option = ConsoleHelper.promptForInt(prompt);
            if (validator.test(option)) break;
            System.out.println("Invalid choice! Please select a number between 1 and " + max);
        }
        return option;
    }

   //Prompts the user for a numeric choice and validates it using a condition.
    public static boolean getYesNoChoice(String question) {
        System.out.println("\n" + question);
        System.out.println("1) Yes\n2) No");
        while (true) {
            int choice = ConsoleHelper.promptForInt("Enter your choice (1-2)");
            if (choice == 1) return true;  // true if user said yes
            if (choice == 2) return false;   // false if the user said no
            System.out.println("Invalid choice! Please enter 1 or 2.");
        }
    }
}

