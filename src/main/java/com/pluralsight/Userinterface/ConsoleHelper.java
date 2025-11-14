package com.pluralsight.Userinterface;

import com.pluralsight.models.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.IntPredicate;

public class ConsoleHelper {
    private static  final Scanner scanner = new Scanner(System.in);

    // Method to prompt the user for a string input
    public static String promptForString(String prompt) {
        System.out.print(prompt + ": ");   // Display the prompt message to the user
        return scanner.nextLine().trim();  // Read the user's input, trim any leading/trailing whitespace, and return the result
    }

    // Method to prompt the user for an integer input
    public static int promptForInt(String prompt) {
        while (true) { // Infinite loop to repeatedly ask for input until a valid integer is provided
            System.out.print(prompt + ": ");   // Print the prompt message to the console
            String input = scanner.nextLine().trim();
            try {      // Try to parse the input as an integer
                return Integer.parseInt(input);  // If successful, return the integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    // Method to prompt the user with a yes/no question and return their choice as a boolean
    public static boolean getYesNoChoice(String question) {
        System.out.println("\n" + question);
        System.out.println("1) Yes\n2) No");   // Print the question and the possible choices (1 for Yes, 2 for No)
        while (true) {   // Infinite loop to keep asking the user until a valid choice is made
            int choice = promptForInt("Enter your choice (1-2)");
            if (choice == 1) return true;      // If the user selects 1, return true (Yes)
            if (choice == 2) return false;       // If the user selects 2, return false (No)
            System.out.println("Invalid choice! Please enter 1 or 2.");  // If the input is neither 1 nor 2, print an error message and ask again
        }
    }

    // Method to display a list of options to the user with a header
    public static void displayOptions(String header, String[] options) {
        System.out.println("\n" + header); // Print the header or title for the options
        for (int i = 0; i < options.length; i++) { // Loop through the array of options and print each one with a corresponding number
            System.out.println((i + 1) + ") " + options[i]); // Print each option in the format "1) Option 1", "2) Option 2", etc.
        }
    }

    // Method to display a list of options to the user with a header and a suffix for each option
    public static void displayOptions(String header, String[] options, String suffix) {
        System.out.println("\n" + header);
        for (int i = 0; i < options.length; i++) { // Loop through the array of options and print each one with a corresponding number and suffix
            System.out.println((i + 1) + ") " + options[i] + suffix);  // Print each option with a number and the provided suffix
        }
    }

    // Method to get a validated option from the user based on a prompt, maximum value, and a custom validation predicate
    public static int getValidatedOption(String prompt, int max, IntPredicate validator) {
        int option;  // Declare a variable to store the user input
        while (true) {   // Loop until the user provides a valid input
            option = promptForInt(prompt); // Prompt the user for input using the promptForInt method
            if (validator.test(option)) break;  // If the input is valid according to the validator, break out of the loop
            // If the input is not valid, display an error message and prompt the user again
            System.out.println("Invalid choice! Please select a number between 1 - " + max);
        }
        return option;   // Return the validated option after exiting the loop
    }
    // ======= TOPPING METHODS =======
    public static void addToppingsToSandwich(Sandwich sandwich) {
        while (true) { // Start an infinite loop to allow the user to repeatedly select toppings
            System.out.println("\nSelect topping category:");
            System.out.println("1) Meat (Premium)\n2) Cheese (Premium)\n3) Regular Toppings\n4) Sauces\n5) Sides\n6) Done adding toppings");

            int categoryChoice = promptForInt("Enter your choice (1-6)");   // Get the user's choice of topping category (1-6)
            if (categoryChoice == 6) break;  // Exit the loop if the user selects 'Done adding toppings'
            String[] toppingArray;
            String categoryType;
            switch (categoryChoice) { // Determine which topping array and category type to use based on user choice
                case 1 -> {
                    toppingArray = AllTopping.Meat; // Use the Meat array from AllTopping
                    categoryType = "MEAT";   // Set the category to "MEAT"
                }
                case 2 -> {
                    toppingArray = AllTopping.Cheese; // Use the Cheese array from AllTopping
                    categoryType = "CHEESE";        // Set the category to "CHEESE"
                }
                case 3 -> {
                    toppingArray = AllTopping.Regular;    // Use the Regular array from AllTopping
                    categoryType = "REGULAR";              // Set the category to "REGULAR"
                }
                case 4 -> {
                    toppingArray = AllTopping.Sauce;
                    categoryType = "SAUCE";
                }
                case 5 -> {
                    toppingArray = AllTopping.Side;
                    categoryType = "SIDE";
                }
                default -> {   // If the user selects an invalid option, print an error and continue
                    System.out.println("Invalid choice! please choose from 1 - 6");
                    continue;  // Continue the loop to allow the user to select again
                }
            }
            // Display the selected topping category
            displayOptions("Available " + categoryType + " toppings:", toppingArray);
            // Get the user's topping choice
            int toppingOption = getValidatedOption("Choose topping (1-" + toppingArray.length + ")", toppingArray.length, i -> i >= 1 && i <= toppingArray.length);
            String toppingName = toppingArray[toppingOption - 1];   // Get the selected topping

            boolean extra = false; // Flag to track whether the topping is extra
            if (categoryType.equals("MEAT") || categoryType.equals("CHEESE")) {
                extra = getYesNoChoice("Would you like EXTRA " + toppingName + "?"); // Prompt the user if they want the topping as extra (additional cost)
                if (extra) { // If the user selects "Yes" for extra, calculate the extra cost and display the information
                    double extraCost = sandwich.getExtraPrice(new Topping(toppingName, categoryType, true));
                    // Display the extra cost for the topping
                    System.out.println("Added extra " + toppingName + " ($" + String.format("%.2f", extraCost) + ")");
                } else {
                    System.out.println(toppingName + " added successfully! (No extra charge)");
                }
            } else {
                if (getYesNoChoice("Would you like to add " + toppingName + "?")) {
                    // If the user selects "No" for extra, confirm the topping is added with no extra charge
                    System.out.println(toppingName + " added successfully! (Free topping)");
                } else {
                    System.out.println("No topping added.");
                    continue; // Skip to the next iteration of the loop, asking for the next topping
                }
            }

            sandwich.addTopping(new Topping(toppingName, categoryType, extra));
        }
    }

    // Method to modify the toppings of a sandwich
    public static void modifyToppings(Sandwich sandwich){
            while (true) {
                // Get the list of current toppings from the sandwich
                ArrayList<Topping> toppings = sandwich.getToppings();
                System.out.println("\nCurrent Toppings:");  // Display the current toppings in the sandwich
                if (toppings.isEmpty()) System.out.println("No toppings yet."); // If no toppings are present, print a message
                else for (int i = 0; i < toppings.size(); i++)    // If there are toppings, display each topping with a number
                    System.out.println((i + 1) + ") " + toppings.get(i).getName());
                // Display options for the user to either add, remove, or finish modifying
                System.out.println("\n1) Add Topping\n2) Remove Topping\n3) Done");
                int choice = promptForInt("Enter your choice (1-3)");

                switch (choice) {   // Switch statement to handle different choices
                    case 1 -> addToppingsToSandwich(sandwich);   // If the user selects 1, they can add a topping to the sandwich
                    case 2 -> {   // If the user selects 2, they can remove an existing topping
                        if (toppings.isEmpty()) {
                            System.out.println("No toppings to remove.");
                            break; // Exit this case and return to the top of the loop
                        }
                        int removeIndex = promptForInt("Enter the number of the topping to remove:");
                        if (removeIndex >= 1 && removeIndex <= toppings.size()) { // Validate selected index
                            Topping removed = toppings.get(removeIndex - 1);   // If the selected index is valid, remove the topping

                            System.out.println(removed.getName() + " removed.");
                        } else System.out.println("Invalid selection.");    //   // If the index is invalid, display an error message
                    }
                    case 3 -> {   // If the user selects 3, exit the method and stop modifying toppings
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                    // If the user enters an invalid option, display an error message
                }
            }
    }

    // Method to modify the signature sandwich (e.g., modifying toppings and reviewing the sandwich details)
    public static void modifySignatureSandwich(Sandwich sandwich){
        System.out.println("\n--- Modify Signature Sandwich ---");
        modifyToppings(sandwich);    // Call another method to modify the toppings of the sandwich
        System.out.println("\nFinal Signature Sandwich:");
        System.out.println(sandwich.getDescription());   // Display the sandwich's updated description
        // Print the total price of the sandwich, formatted to two decimal places
        System.out.println("Total Price: $" + String.format("%.2f", sandwich.getPrice()));
        System.out.println("========================================\n");
    }

    // Prompts the user to select an option from a list, with custom validation logic
    public static String getOption(String optionName, String[] options, IntPredicate validator){

        // Display all available options (e.g., types of bread, sizes, etc.)
        displayOptions("Choose your " + optionName + ":", options);
        int choice = getValidatedOption(
                "Enter " + optionName + " option (1-" + options.length + ")",
                options.length,
                validator
        );
        return options[choice - 1];
        // Return the selected option from the array (subtract 1 since arrays are 0-indexed)
    }
}

