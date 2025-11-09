package com.pluralsight;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        showHomeScreen();
    }

    // Scanner object for reading user input throughout the application
    private static Scanner scanner = new Scanner(System.in);

    // The current order being built
    private static Order currentOrder = null;

    //Display the Home Screen menu
    //Options New Order or Exit
    private static void showHomeScreen() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("       WELCOME TO DELI-SHOP!           ");
            System.out.println("========================================\n");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("\nEnter your choice: ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                // Create a new order and show the order screen
                currentOrder = new Order();
                showHomeScreen();
            } else if (choice.equals("0")) {
                // Exit the application
                System.out.println("\nThank you for visiting DELI-cious! Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}