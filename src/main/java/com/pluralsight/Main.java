package com.pluralsight;


public class Main {
    public static void main(String[] args) {
        HomeScreen();
    }

    //Display the Home Screen menu
    private static void HomeScreen() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("       WELCOME TO OUR  DELI-SHOP!           ");
            System.out.println("========================================\n");
            System.out.println("1) New Order");
            System.out.println("0) Exit");

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
            System.out.println("\n========================================");
            System.out.println("            ORDER SCREEN         ");
            System.out.println("========================================\n");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");

            String choice = ConsoleHelper.promptForString("Enter your choice");
            switch (choice) {
                case "1":
                    Sandwich sandwich = (Sandwich) addSandwich();
                    order.addSandwich(sandwich);
                    break;
                case "2":
                    Drink drink = (Drink) addDrink();
                    order.addDrink(drink);
                    break;
                case "3":
                    Chips chips = (Chips) addChips();
                    order.addChips(chips);
                    break;
                case "4":
                    checkout(order);
                    return; // Return to Home Screen after checkout
                case "0":
                    System.out.println("\nOrder cancelled. Returning to Home Screen..");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void checkout(Order order) {
        System.out.println("\n--- Checkout ---");
        order.displayOrderDetails(); //show order summary
        String confirm = ConsoleHelper.promptForString("Would you like to confirm your order? (yes/no)");

        if (confirm.equalsIgnoreCase("yes")) {
            ReceiptFileManager.saveReceipt(order); // save order
            System.out.println("Order confirmed! Receipt saved successfully.");
        } else {
            System.out.println("Order not confirmed. Returning to Home Screen...");
        }
    }

    private static OrderItems addDrink() {
        System.out.println("\n--- Add Drink ---");
        System.out.println("Select drink size:");
        System.out.println("1) Small ($2.00)");
        System.out.println("2) Medium ($2.50)");
        System.out.println("3) Large ($3.00)");
        String sizeChoice = ConsoleHelper.promptForString("Enter your choice");

        String size;
        switch (sizeChoice) {
            case "1":
                size = "Small";
                break;
            case "2":
                size = "Medium";
                break;
            case "3":
                size = "Large";
                break;
            default:
                System.out.println("Invalid choice. Please choose size between 1 - 3");
                return null;
        }
        // Ask for drink flavor
        System.out.println("\nSelect drink flavor:");
        System.out.println("1) Coke");
        System.out.println("2) Sprite");
        System.out.println("3) Lemonade");
        System.out.println("4) Root Beer");
        System.out.println("5) Iced Tea");
        String flavorChoice = ConsoleHelper.promptForString("Enter your choice");

        String flavor;
        switch (flavorChoice) {
            case "1":
                flavor = "Coke";
                break;
            case "2":
                flavor = "Sprite";
                break;
            case "3":
                flavor = "Lemonade";
                break;
            case "4":
                flavor = "Root Beer";
                break;
            case "5":
                flavor = "Iced Tea";
                break;
            default:
                System.out.println("Invalid choice. Please choose a flavor between 1 and 5.");
                return null; // Exit the method without adding a drink

        }
        Drink drink = new Drink(size, flavor);
        System.out.println(size + " " + flavor + " added successfully!");
        return drink;
    }

    private static OrderItems addChips() {
        System.out.println("\n--- Add Chips ---");
        System.out.println("Select chip type:");
        System.out.println("1) Classic");
        System.out.println("2) BBQ");
        System.out.println("3) Sour Cream & Onion");
        System.out.println("4) Salt & Vinegar");
        System.out.println("5) Jalapeño");
        String chipChoice = ConsoleHelper.promptForString("Enter your choice");
        String type = "";
        switch (chipChoice) {
            case "1":
                type = "Classic";
                break;
            case "2":
                type = "BBQ";
                break;
            case "3":
                type = "Sour Cream & Onion";
                break;
            case "4":
                type = "Salt & Vinegar";
                break;
            case "5":
                type = "Jalapeño";
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Classic.");
                type = "Classic";
        }

        Chips chips = new Chips(type);
        System.out.println(type + " Chips added successfully!");
        return chips;
    }

}


// line 354