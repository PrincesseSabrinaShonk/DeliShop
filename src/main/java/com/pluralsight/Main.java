package com.pluralsight;


public class Main {
    public static void main(String[] args) {
        HomeScreen();
    }
        //Display the Home Screen menu
        private static void HomeScreen () {
            while (true) {
                System.out.println("\n========================================");
                System.out.println("       WELCOME TO DELI-SHOP!           ");
                System.out.println("========================================\n");
                System.out.println("1) New Order");
                System.out.println("0) Exit");
                System.out.print("\nEnter your choice: ");

                String choice = ConsoleHelper.promptForString("Enter your choice");

                // Use switch for menu selection
                switch (choice) {
                    case "1":
                        Order O = new Order(); // Create a new order object
                        HomeScreen();          // Show the order menu
                        break;
                    case "0":
                        System.out.println("\nThank you for visiting our DELI-SHOP! Goodbye!");
                        return; // Exit the method and end the program
                    default:
                        System.out.println("Invalid choice. Please try again."); // Handle invalid input
                }
            }
        }
        //Display the Order Screen menu
        private static void OrderScreen () {
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

                String choice = ConsoleHelper.promptForString("Enter your choice");
                switch (choice) {
                    case "1":
                        addSandwich();
                        break;
                    case "2":
                        addDrink();
                        break;
                    case "3":
                        addChips();
                        break;
                    case "4":
                        checkout();
                        return; // Return to Home Screen after checkout
                    case "0":
                        System.out.println("\nOrder cancelled.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                
            }
        }
    private static void addSandwich () {
    }
    private static void addDrink() {
    }
    private static void addChips(){
    }
    private static void checkout(){

    }
}
