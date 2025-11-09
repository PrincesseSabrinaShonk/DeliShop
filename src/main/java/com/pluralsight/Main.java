package com.pluralsight;


public class Main {
    public static void main(String[] args) {
        HomeScreen();
    }

    //Display the Home Screen menu
    private static void HomeScreen() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("       WELCOME TO DELI-SHOP!           ");
            System.out.println("========================================\n");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("\nEnter your choice: ");

            String choice = ConsoleHelper.promptForString("Enter your choice");

            switch (choice) {
                case "1":
                    OrderScreen(); // Go to order menu
                    break;
                case "0":
                    System.out.println("Thank you for visiting Deli-Shop. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    private static void OrderScreen() {
        Order order = new Order(); // Create a new order
        //Display the Order Screen menu
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
                    System.out.println("\nOrder cancelled. Returning to Home Screen..");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }

    private static OrderItems addDrink() {
        return null;
    }
    private static OrderItems addChips(){
        return null;
    }
    private static OrderItems checkout(){
        return null;
    }

    private static OrderItems addSandwich() {
        System.out.println("\n--- Add Sandwich ---");
        // Step 1: Select bread and size
        String size = ConsoleHelper.promptForString("Enter sandwich size (4, 8, 12)");
        String bread = ConsoleHelper.promptForString("Enter bread type (white, wheat, rye, wrap)");
        boolean toasted = ConsoleHelper.promptForString("Toasted? (yes/no)").equalsIgnoreCase("yes");
        // Create sandwich
        Sandwich sandwich = new Sandwich(size, bread, toasted);

        // Step 2: Add toppings
        System.out.println("\nAdd toppings. Type 'done' when finished.");

        while (true) {
            String name = ConsoleHelper.promptForString("Enter topping name (or 'done')");
            if (name.equalsIgnoreCase("done")) break;
            String type = ConsoleHelper.promptForString("Enter topping type (MEAT, CHEESE, REGULAR, SAUCE)").toUpperCase();
            boolean extra = ConsoleHelper.promptForString("Extra portion? (yes/no)").equalsIgnoreCase("yes");

            sandwich.addTopping(new Topping(name, type, extra));
        }
        return sandwich;
    }
}

