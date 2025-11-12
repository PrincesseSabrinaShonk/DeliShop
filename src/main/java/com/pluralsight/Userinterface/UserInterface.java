package com.pluralsight.Userinterface;


import com.pluralsight.data.ReceiptFileManager;
import com.pluralsight.models.*;

import static com.pluralsight.Userinterface.ConsoleHelper.*;

public class UserInterface {
    public void display() {
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
            System.out.println("5) Add Signature Sandwich");
            System.out.println("0) Cancel Order");

            String choice = ConsoleHelper.promptForString("Enter your choice");
            switch (choice) {
                case "1":
                    Sandwich sandwich = (Sandwich) addSandwich();     // Create a custom sandwich
                    order.addSandwich(sandwich);
                    break;
                case "2":
                    Drink drink = (Drink) addDrink();    // Create and add a drink to the order
                    order.addDrink(drink);
                    break;
                case "3":
                    Chips chips = (Chips) addChips();       // Create and add chips to the order
                    order.addChips(chips);
                    break;
                case "4":                                       // Proceed to checkout and return to the home screen afterward
                    checkout(order);
                    return;
                case "5":
                    Sandwich sigSandwich = addSignatureSandwiches();   // Add a pre-made (signature) sandwich to the order
                    order.addSandwich(sigSandwich);
                    break;
                case "0":
                    System.out.println("\nOrder cancelled. Returning to Home Screen..");
                    return;
                default:                                                      // Handle invalid input
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    //Displays all available chip flavors and prompts the user to choose one.
    private static OrderItems addChips() {
        System.out.println("\n--- Add Chips ---");
        for (int i = 0; i < ChipsChoice.choice.length; i++) {   // Display all available chip flavor options
            System.out.println((i + 1) + ") " + ChipsChoice.choice[i]);
        }
        int option;
        while (true) {    // Keep prompting the user until a valid option is selected
            option = ConsoleHelper.promptForInt("Choose your chip flavor (1-" + ChipsChoice.choice.length + ")");
            if (option >= 1 && option <= ChipsChoice.choice.length) {  // Check if the user's choice is within the valid range
                break;
            }
            System.out.println("Invalid choice! Please select a number between 1 and " + ChipsChoice.choice.length);
        }

        String chipFlavor = ChipsChoice.choice[option - 1];   // Retrieve the chosen flavor based on the user's selection
        Chips chips = new Chips(chipFlavor); // Create a new Chips object for the selected flavor

        System.out.println("\n✓ " + chipFlavor + " chips added successfully! ($1.50)\n");
        return chips;
    }

    //  you must have at least one sandwich OR chips/drink
    private static void checkout(Order order) {
        System.out.println("\n--- Checkout ---");

        if (order.getSandwichCount() == 0 && !order.hasChipsOrDrink()) { // Ensure the order contains at least one item
            System.out.println("\n You must order at least one sandwich, chips, or drink to checkout.");
            System.out.println("Returning to Order Screen...\n");
            return;
        }
        order.displayOrderDetails();  // Display the summary of the current order

        String confirm = ConsoleHelper.promptForString("\nWould you like to confirm your order? (yes/no)");

        if (confirm.equalsIgnoreCase("yes")) {

            ReceiptFileManager.saveReceipt(order); // Save the order receipt if confirmed
            System.out.println("\n Order confirmed! Receipt saved successfully.");
            System.out.println("Thank you for your order!\n");
        } else {    // Handle order cancellation
            System.out.println("\nOrder cancelled. Returning to Home Screen...\n");


        }
    }

    // Displays available drink sizes and flavors, prompts the user to select one
    private static OrderItems addDrink() {
        System.out.println("\n--- Add Drink ---");
        System.out.println("Select drink size:");
        System.out.println("1) Small ($2.00)");
        System.out.println("2) Medium ($2.50)");
        System.out.println("3) Large ($3.00)");
        String sizeChoice = ConsoleHelper.promptForString("Enter your drink Size");

        String size;
        switch (sizeChoice) {
            case "1":
                size = "small";
                break;
            case "2":
                size = "medium";
                break;
            case "3":
                size = "large";
                break;
            default:
                System.out.println("Invalid selection. Defaulting to small."); // need to be revised
                size = "small";
                break;
        }
        System.out.println("\nAvailable Flavors:");  // Display all available drink flavors

        for (String flavorOption : DrinkFlavor.flavors) {
            System.out.println("- " + flavorOption);
        }
        String flavor = ConsoleHelper.promptForString("Which flavor would you like"); // Prompt user to select a flavor

        Drink drink = new Drink(size, flavor);  // Create a new Drink object based on user selection

        // Confirm the drink was added
        System.out.println(size.substring(0, 1).toUpperCase() + size.substring(1) + " "
                + flavor + " drink added successfully! ($" + String.format("%.2f", drink.getPrice()) + ")\n");
        return drink;
    }   // need to implement


    private static OrderItems addSandwich() {
        System.out.println("\n---ADD SANDWICH---");
        // Choose bread type
        displayOptions("Choose your bread type:", Bread.types);
        int breadOption = getValidatedOption("Enter bread option (1-" + Bread.types.length + ")", Bread.types.length, Bread::isValid);
        String breadType = Bread.types[breadOption - 1];

        // Choose sandwich size
        displayOptions("Choose your sandwich size:", SandwichSize.size, " Inches");
        int sizeOption = getValidatedOption("Enter size option (1-3)", 3, i -> i >= 1 && i <= 3);
        String breadsize = SandwichSize.size[sizeOption - 1];

        // Choose if toasted
        boolean toasted = getYesNoChoice("Would you like it toasted?");

        // Create the sandwich object
        Sandwich sandwich = new Sandwich(breadsize, breadType, toasted);
        double basePrice = sandwich.getPrice();

        // Display sandwich creation summary
        System.out.println("\n " + breadsize + "\" " + breadType + " sandwich created!");
        System.out.println("  Base price: $" + String.format("%.2f", basePrice));
        if (toasted) System.out.println("  Toasted: Yes");

        // Add toppings
        while (true) {
            System.out.println("\n----------------------------------------");
            System.out.println("Select topping category:");
            System.out.println("1) Meat (Premium)\n2) Cheese (Premium)\n3) Regular Toppings\n4) Sauces\n5) Sides\n6) Done adding toppings");

            int categoryChoice = ConsoleHelper.promptForInt("Enter your choice (1-6)");
            if (categoryChoice == 6) break;

            String[] toppingArray;
            String categoryType;

            // Determine the topping category based on the user's menu choice
            switch (categoryChoice) {
                case 1 -> { toppingArray = AllTopping.Meat; categoryType = "MEAT"; }  //  selected meat toppings
                case 2 -> { toppingArray = AllTopping.Cheese; categoryType = "CHEESE"; }    // User selected cheese
                case 3 -> { toppingArray = AllTopping.Regular; categoryType = "REGULAR"; } // choose regular topping
                case 4 -> { toppingArray = AllTopping.Sauce; categoryType = "SAUCE"; }   // choose sauce
                case 5 -> { toppingArray = AllTopping.Side; categoryType = "SIDE"; }   // choose sides
                default -> { System.out.println("Invalid choice!"); continue; }   // Handles invalid user input
            }

            displayOptions("Available " + categoryType + " toppings:", toppingArray);
            int toppingOption = getValidatedOption("Choose topping (1-" + toppingArray.length + ")", toppingArray.length, i -> i >= 1 && i <= toppingArray.length);
            String toppingName = toppingArray[toppingOption - 1];

            boolean extra = false;
            if (categoryType.equals("MEAT") || categoryType.equals("CHEESE")) {

                if (getYesNoChoice("Would you like EXTRA " + toppingName + "?")) {  // Ask the user if they want an extra portion of the topping
                    extra = true;
                    Topping tempTopping = new Topping(toppingName, categoryType, true); // Create a temporary Topping object to calculate the extra cost
                    double extraCost = sandwich.getExtraPrice(tempTopping); // Get the price for the extra portion
                    //Inform the user that the extra topping has been added and show the cost
                    System.out.println("Added extra " + toppingName + " (" + categoryType + ") $" + String.format("%.2f", extraCost));
                } else {
                    System.out.println("\n " + toppingName + " added successfully! (No additional charge)");
                }
            } else {   // If the user doesn’t want extra, confirm the regular topping addition
                if (getYesNoChoice("Would you like to add " + toppingName + "?")) {
                    System.out.println(toppingName + " added successfully! (Free topping)");
                    sandwich.addTopping(new Topping(toppingName, categoryType, false));
                } else {   // User declines to add the topping
                    System.out.println("No topping added.");
                }
                continue;  // Skip to the next topping since the current one has been handled
            }

            sandwich.addTopping(new Topping(toppingName, categoryType, extra));  // Add the topping to the sandwich
        }

        // Display final summary
        double finalPrice = sandwich.getPrice();
        System.out.println("\n========================================");
        System.out.println("     SANDWICH ADDED SUCCESSFULLY!");
        System.out.println("========================================");
        System.out.println(sandwich.getDescription());
        System.out.println("\nTotal Sandwich Price: $" + String.format("%.2f", finalPrice));
        System.out.println("========================================\n");

        return sandwich;

    }

    private static Sandwich addSignatureSandwiches() {
        int command;

        // Display signature sandwich options
        while (true) {
            System.out.println("\n--- Signature Sandwiches ---");
            System.out.println("1) BLT");
            System.out.println("2) Philly Cheese Steak");
            command = ConsoleHelper.promptForInt("Please choose between 1 - 2");
            if (command == 1 || command == 2) break;
            System.out.println("Invalid choice! Please select 1 or 2.");
        }

        Sandwich sandwich;
        if (command == 1) {
            sandwich = SignatureSandwiches.BLT();
            System.out.println("\n BLT sandwich added!");
        } else {
            sandwich = SignatureSandwiches.PhillyCheeseSteak();
            System.out.println("\n Philly Cheese Steak added!");
        }

        System.out.println("Base price: $" + String.format("%.2f", sandwich.getPrice()));
        return sandwich;
    } //todo

    }


