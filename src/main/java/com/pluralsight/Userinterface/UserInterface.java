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
            System.out.println("1) New Order");  // Option to start a new order
            System.out.println("0) Exit");       // Option to exit the application

            // Prompt the user to enter their choice (1 to start a new order, 0 to exit)
            String choice = ConsoleHelper.promptForString("Enter your choice");

            // Handle the user's input using a switch statement
            switch (choice) {
                case "1":
                    OrderScreen(); //If the user selects '1' go to the OrderScreen to start a new order
                    break;
                case "0":
                    // If the user selects '0', print a goodbye message and exit the program
                    System.out.println("Thank you for visiting Deli-Shop. Goodbye!");
                    return;
                default:
                    // If the user enters anything other than '1' or '0', display an error message
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to display the Order Screen and allow the user to add items to their order
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

            String choice = ConsoleHelper.promptForString("Enter your choice"); // Prompt the user for their menu choice
            switch (choice) {  // Handle the user input using a switch statement
                case "1":
                    // Add a custom sandwich to the order
                    Sandwich sandwich = (Sandwich) addSandwich();     // Create a custom sandwich
                    order.addSandwich(sandwich);                       // Add the sandwich to the order
                    break;
                case "2":
                    Drink drink = (Drink) addDrink();             // Create and add a drink to the order
                    order.addDrink(drink);
                    break;
                case "3":
                    Chips chips = (Chips) addChips();           // Create and add chips to the order
                    order.addChips(chips);
                    break;
                case "4":
                    // Proceed to checkout
                    checkout(order);                    // Call the checkout method to finalize the order
                    return;                              // Return to exit the loop after checkout
                case "5":
                    // Add a pre-made signature sandwich to the order
                    Sandwich sigSandwich = addSignatureSandwiches();   // Add a signature sandwich
                    modifySignatureSandwich(sigSandwich);                // Allow modifications to the signature sandwich
                    order.addSandwich(sigSandwich);                      // Add the signature sandwich to the order
                    break;
                case "0":
                    System.out.println("\nOrder cancelled. Returning to Home Screen.."); // Cancel the order and return to the home screen
                    return;
                default:
                    // Handle invalid menu selections
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    //Displays all available chip flavors and prompts the user to choose one.
    private static OrderItems addChips() {
        System.out.println("\n--- Add Chips ---");   // Display all available chip flavor options
        // Loop through the `ChipsChoice.choice` array and print each option with its corresponding number
        for (int i = 0; i < ChipsChoice.choice.length; i++) {
            System.out.println((i + 1) + ") " + ChipsChoice.choice[i]);
        }
        int option;  // Variable to store the user's choice
        while (true) {     // Loop until a valid choice is entered
            // Prompt the user to select a chip flavor by entering a number within the valid range
            option = ConsoleHelper.promptForInt("Choose your chip flavor (1-" + ChipsChoice.choice.length + ")");
            // Check if the user's choice is valid
            if (option >= 1 && option <= ChipsChoice.choice.length) {  // Check if the user's choice is within the valid range
                break; // Exit the loop if the choice is valid
            }
            System.out.println("Invalid choice! Please select a number between 1 and " + ChipsChoice.choice.length);
        }

        String chipFlavor = ChipsChoice.choice[option - 1];   // Retrieve the chosen flavor based on the user's selection
        Chips chips = new Chips(chipFlavor); // Create a new Chips object for the selected flavor

        System.out.println("\n✓ " + chipFlavor + " chips added successfully! ($1.50)\n");
        return chips; // Return the Chips object representing the selected chip flavor
    }

    //  you must have at least one sandwich OR chips/drink
    private static void checkout(Order order) {
        System.out.println("\n--- Checkout ---");

        if (order.getSandwichCount() == 0 && !order.hasChipsOrDrink()) {
            // If there are no items in the order, display an error message and return to the order screen
            System.out.println("\n You must order at least one sandwich, chips, or drink to checkout.");
            System.out.println("Returning to Order Screen...\n");
            return;  // Exit the method and prevent further checkout
        }
        order.displayOrderDetails();  // Display the summary of the current order sandwiches, chips, drinks
        // Ask the user to confirm their order
        String confirm = ConsoleHelper.promptForString("\nWould you like to confirm your order? (yes/no)");
        if (confirm.equalsIgnoreCase("yes")) {   // If the user confirms the order

            ReceiptFileManager.saveReceipt(order); // Save the order receipt if confirmed
            System.out.println("\n Order confirmed! Receipt saved successfully.");
            System.out.println("Thank you for your order!\n");
        } else {    // If the user cancels the order
            System.out.println("\nOrder cancelled. Returning to Home Screen...\n");
            // Handle the cancellation by returning to the home screen or another appropriate action


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
                size = "small";  // User selected small size
                break;
            case "2":
                size = "medium";  // User selected medium size
                break;
            case "3":
                size = "large";   // User selected large size
                break;
            default:                 // Handle invalid input with a clear message
                System.out.println("Invalid selection. Defaulting to small."); // need to be revised
                size = "small";  // Default to small if invalid selection
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

    // method to Display how to add sandwich
    private static OrderItems addSandwich() {
         System.out.println("\n---ADD SANDWICH---");
         String breadType = ConsoleHelper.getOption("bread type", Bread.types, Bread::isValid);  // Choose bread  type

        // Choose size
        displayOptions("Choose your sandwich size:", SandwichSize.size, " Inches");// Display a list of available bread types for the user to choose from
        int sizeOption = getValidatedOption("Enter size option (1-3)", 3, i -> i >= 1 && i <= 3);// using Lambda exp
        String breadSize = SandwichSize.size[sizeOption - 1];  // Get the corresponding size from the SandwichSize array (adjusting for 0-based indexing)

        // Ask the user if they want their sandwich toasted
        boolean toasted = getYesNoChoice("Would you like it toasted?");
        Sandwich sandwich = new Sandwich(breadSize, breadType, toasted);// Create a new Sandwich object with the specified bread size, type

        // Display sandwich creation summary
        System.out.println("\n" + breadSize + "\" " + breadType + " sandwich created!");  // Show bread size and type
        System.out.println("Base price: $" + String.format("%.2f", sandwich.getPrice())); // Show the base price of the sandwich
        if (toasted) System.out.println("Toasted: Yes");  // If the sandwich is toasted, display a confirmation

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

    }

    //  Method to display signature sandwiches and add one to the menu based on user input
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

        Sandwich sandwich;   // Create the selected signature sandwich
        // Based on user input, return the corresponding sandwich
        if (command == 1) {
            sandwich = SignatureSandwiches.BLT();
            System.out.println("\nBLT sandwich selected!");
        } else {     // Assuming Philly Cheese Steak is a predefined sandwich object
            sandwich = SignatureSandwiches.PhillyCheeseSteak();
            System.out.println("\nPhilly Cheese Steak selected!");
        }
          // Display base price of the chosen sandwich
        System.out.println("Base price: $" + String.format("%.2f", sandwich.getPrice()));
        return sandwich;
    }
}




