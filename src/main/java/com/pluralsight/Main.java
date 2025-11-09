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
                System.out.println("\nThank you for visiting DELI-SHOP! Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    //Display the Order Screen menu
    private static void OrderScreen(){
        while (true) {
            System.out.println("\n╔════════════════════════════════╗");
            System.out.println("║        Order Screen            ║");
            System.out.println("╚════════════════════════════════╝");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("\nEnter your choice: ");
        }
    }

        //Add a sandwich to the order by walking through customization options
        private static void addSandwich() {
            System.out.println("\n--- Add Sandwich ---");
    }
}