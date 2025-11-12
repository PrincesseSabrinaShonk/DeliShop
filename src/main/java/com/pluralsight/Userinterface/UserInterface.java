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
                    modifySignatureSandwich(sigSandwich);
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

        // Loop through all available drink flavors and display them to the user
        for (String flavorOption : DrinkFlavor.flavors) {
            System.out.println("- " + flavorOption);
        }
        String flavor = ConsoleHelper.promptForString("Which flavor would you like"); // Prompt user to select a flavor
        Drink drink = new Drink(size, flavor);  // Create a new Drink object based on user selection

        // Confirm the drink was added
        System.out.println(size.substring(0, 1).toUpperCase() + size.substring(1) + " "
                + flavor + " drink added successfully! ($" + String.format("%.2f", drink.getPrice()) + ")\n");
        return drink;
    }

    //Display how to add sandwich

    private static OrderItems addSandwich() {
        System.out.println("\n---ADD SANDWICH---");
        // Choose bread  type for the sandwich
        displayOptions("Choose your bread type:", Bread.types);
        int breadOption = getValidatedOption("Enter bread option (1-" + Bread.types.length + ")", Bread.types.length, Bread::isValid);
        String breadType = Bread.types[breadOption - 1]; // Prompt user and validate input

        // Choose size
        displayOptions("Choose your sandwich size:", SandwichSize.size, " Inches");// Display a list of available bread types for the user to choose from
        int sizeOption = getValidatedOption("Enter size option (1-3)", 3, i -> i >= 1 && i <= 3);// using Lambda exp
        String breadSize = SandwichSize.size[sizeOption - 1];  // Get the corresponding size from the SandwichSize array (adjusting for 0-based indexing)

        // // Choose whether the sandwich should be toasted
        boolean toasted = getYesNoChoice("Would you like it toasted?");
        Sandwich sandwich = new Sandwich(breadSize, breadType, toasted);

        System.out.println("\n" + breadSize + "\" " + breadType + " sandwich created!");  // Display sandwich creation summary
        System.out.println("Base price: $" + String.format("%.2f", sandwich.getPrice()));
        if (toasted) System.out.println("Toasted: Yes");

        // Add toppings to the sandwich
        addToppingsToSandwich(sandwich);

        //  Display final sandwich summary
        double finalPrice = sandwich.getPrice();
        System.out.println("\n========================================");
        System.out.println("     SANDWICH ADDED SUCCESSFULLY!");
        System.out.println("========================================");
        System.out.println(sandwich.getDescription());
        System.out.println("\nTotal Sandwich Price: $" + String.format("%.2f", finalPrice));
        System.out.println("========================================\n");
        return sandwich; // Return the created Sandwich object

    // Allows the user to select a pre-made signature sandwich
    }
    // Display signature Sandwich
    private static Sandwich addSignatureSandwiches() {
        int command;
        while (true) {  // Loop until the user enters a valid choice (1 or 2)
            System.out.println("\n--- Signature Sandwiches ---");
            System.out.println("1) BLT");
            System.out.println("2) Philly Cheese Steak");
            command = promptForInt("Please choose between 1 - 2");  //Prompt user for input
            if (command == 1 || command == 2) break;                // Exit loop if valid choice
            System.out.println("Invalid choice! Please select 1 or 2.");
        }

        Sandwich sandwich;
        // Create the selected signature sandwich
        if (command == 1) {
            sandwich = SignatureSandwiches.BLT();
            System.out.println("\nBLT sandwich selected!");
        } else {
            sandwich = SignatureSandwiches.PhillyCheeseSteak();
            System.out.println("\nPhilly Cheese Steak selected!");
        }

        System.out.println("Base price: $" + String.format("%.2f", sandwich.getPrice())); // Display base price of the chosen sandwich
        return sandwich;
    }
}




