package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        HomeScreen();
    }

    //Display the HomeScreen
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

    private static OrderItems addChips() {
        System.out.println("\n--- Add Chips ---");
        System.out.println("Jalapeño");
        System.out.println("Salt & Vinegar");
        System.out.println("Sour Cream & Onion");
        System.out.println("BBQ");
        System.out.println("Classic");

        String choice = ConsoleHelper.promptForString("Choose a Flavor");
        return new Chips(choice);
    }

    private static void checkout(Order order) {
        System.out.println("\n--- Checkout ---");
        // must have at least one sandwich OR chips/drink
        if (order.getSandwichCount() == 0 && !order.hasChipsOrDrink()) {
            System.out.println("You must order at least one sandwich or chips or drink.");
            return;
        }
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
        String size = ConsoleHelper.promptForString("Enter your Size");
        String flavor = ConsoleHelper.promptForString("Which flavor would you like");

        return new Drink(size, flavor);
    }

    private static OrderItems addSandwich() {
        System.out.println("\n--- Add Sandwich ---");
        int breadOption;
        while (true) {
            System.out.println("Choose your bread Type");
            for (int i = 0; i < Bread.types.length; i++) {
                System.out.println((i + 1) + ") " + Bread.types[i]);
            }
            breadOption = ConsoleHelper.promptForInt("Enter bread Option");
            if (breadOption >= 1 && breadOption <= Bread.types.length) {
                break;
            }
            System.out.println("Invalid Input, Choose from Menu");
        }
        String breadType = Bread.types[breadOption - 1];
        String breadsize;

        while (true) {
            breadsize = ConsoleHelper.promptForString("Choose your size (4,8,12 Inches)");
            if (breadsize.equals("4") || breadsize.equals("8") || breadsize.equals("12")) {
                break;
            }
            System.out.println("Invalid size! Please enter only 4, 8,OR 12.");
        }
        boolean toasted;
        while (true) {
            String t = ConsoleHelper.promptForString("Would you like it toasted? (Yes/No)");
            if (t.equalsIgnoreCase("Yes")) {
                toasted = true;
                break;
            } else if (t.equalsIgnoreCase("No")) {
                toasted = false;
                break;
            }
            System.out.println("Invalid input! Please enter Yes or No.");
        }
        Sandwich sandwich = new Sandwich(breadsize, breadType, toasted);
        String[] choice = null;

        String ty;
        while (true) {
            ty = ConsoleHelper.promptForString("Topping type: MEAT, CHEESE, REG, SAUCE , SIDE , DONE ").toUpperCase();
            if (ty.equals("DONE"))
                break;
            switch (ty) {
                case "MEAT":
                    choice = AllTopping.Meat;
                    break;
                case "CHEESE":
                    choice = AllTopping.Cheese;
                    break;
                case "REG":
                    choice = AllTopping.Regular;
                    break;
                case "SAUCE":
                    choice = AllTopping.Sauce;
                    break;
                case "SIDE":
                    choice = AllTopping.Side;
                    break;
                default:
                    System.out.println("Invalid type! Please choose Your Meat,Cheese,Regular,Sauce or Side.");
                    continue;
            }

            System.out.println("Available " + ty + " toppings");
            for (int i = 0; i < choice.length; i++) {
                System.out.println((i + 1) + ") " + choice[i]); //List toppings
            }
            int Option;
            while (true) {
                Option = ConsoleHelper.promptForInt("Topping?");
                if (Option >= 1 && Option <= choice.length) break;
                System.out.println("Invalid Input, try again.");
            }
            boolean more = false;
            if (ty.equals("Meat") || ty.equals("Cheese")) {
                while (true) { // Keep asking until valid input
                    String moreInput = ConsoleHelper.promptForString(" More Toppings (Yes /No): ");
                    if (moreInput.equalsIgnoreCase("Yes")) {
                        more = true;
                        break;
                    } else if (moreInput.equalsIgnoreCase("No")) {
                        more = false;
                        break; // exit The loop
                    } else {
                        System.out.println("Invalid input! Please enter only Yes or No."); // invalid
                    }
                }
            }
            sandwich.addTopping(new Topping(choice[Option - 1], ty, more));
        }
        return sandwich;
    }


     }


