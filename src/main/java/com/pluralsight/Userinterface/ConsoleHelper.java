package com.pluralsight.Userinterface;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class ConsoleHelper {
    private static Scanner scanner = new Scanner(System.in);

    public static String promptForString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

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
}

