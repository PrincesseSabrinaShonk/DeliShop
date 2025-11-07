package com.pluralsight;

public class OrderScreen {
    private Order currentOrder = new Order();

    public void startOrder() {
        System.out.println("---- Starting a New Order ----");

        String orderMenu = """
                1) Add Sandwich
                2) Add Drink
                3) Add Chips
                4) Checkout
                0) Cancel Order
                """;

        while (true) {
            System.out.println(orderMenu);
            String input = ConsoleHelper.promptForString("Enter your choice");

            switch (input) {
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
                    return;
                case "0":
                    System.out.println("Order canceled. Returning to Home Screen...");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void addSandwich() {
        System.out.println("--- Add Sandwich ---");
        String size = ConsoleHelper.promptForString("Select size (4, 8, or 12)");
        String bread = ConsoleHelper.promptForString("Select bread type (white, wheat, rye, wrap)");
        boolean toasted = ConsoleHelper.promptForString("Toasted? (yes/no)").equalsIgnoreCase("yes");

        Sandwich sandwich = new Sandwich(size, bread, toasted);
        System.out.println("Sandwich created: " + size + "\" " + bread + (toasted ? " (toasted)" : ""));
        currentOrder.addSandwich(sandwich);
    }

    private void addDrink() {
        System.out.println("--- Add Drink ---");
        String drinkSize = ConsoleHelper.promptForString("Select drink size (Small, Medium, Large)");
        String drinkFlavor = ConsoleHelper.promptForString("Enter drink flavor: Coke,Lemonade, Sweet Tea, Juice");
        String drinkItem = drinkSize + " " + drinkFlavor;
        currentOrder.addDrink(drinkItem);
        System.out.println(drinkItem + " added to your order.");
    }

    private void addChips() {
        System.out.println("--- Add Chips ---");
        String chipType = ConsoleHelper.promptForString("What type of chips would you like? Small, Med, or Large? ");
        currentOrder.addChips(chipType);
        System.out.println(chipType + " chips added to your order.");
    }

    private void checkout() {
        System.out.println("--- Checkout ---");
        System.out.println("Your Order Summary:");
        currentOrder.displayOrderDetails();
        System.out.println("Total (to be implemented in Phase 2): $" + currentOrder.calculateTotal());
        System.out.println("Returning to Home Screen...");
    }



}
