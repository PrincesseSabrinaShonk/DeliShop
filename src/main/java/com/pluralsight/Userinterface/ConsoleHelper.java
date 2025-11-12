package com.pluralsight.Userinterface;

import com.pluralsight.models.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.IntPredicate;

public class ConsoleHelper {
    private static  final Scanner scanner = new Scanner(System.in);

    // ======= BASIC INPUT METHODS =======
    public static String promptForString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    public static int promptForInt(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    public static boolean getYesNoChoice(String question) {
        System.out.println("\n" + question);
        System.out.println("1) Yes\n2) No");
        while (true) {
            int choice = promptForInt("Enter your choice (1-2)");
            if (choice == 1) return true;
            if (choice == 2) return false;
            System.out.println("Invalid choice! Please enter 1 or 2.");
        }
    }

    public static void displayOptions(String header, String[] options) {
        System.out.println("\n" + header);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ") " + options[i]);
        }
    }

    public static void displayOptions(String header, String[] options, String suffix) {
        System.out.println("\n" + header);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ") " + options[i] + suffix);
        }
    }

    public static int getValidatedOption(String prompt, int max, IntPredicate validator) {
        int option;
        while (true) {
            option = promptForInt(prompt);
            if (validator.test(option)) break;
            System.out.println("Invalid choice! Please select a number between 1 and " + max);
        }
        return option;
    }

       // ======= TOPPING METHODS =======
    public static void addToppingsToSandwich(Sandwich sandwich) {
        while (true) {
            System.out.println("\nSelect topping category:");
            System.out.println("1) Meat (Premium)\n2) Cheese (Premium)\n3) Regular Toppings\n4) Sauces\n5) Sides\n6) Done adding toppings");

            int categoryChoice = promptForInt("Enter your choice (1-6)");
            if (categoryChoice == 6) break;
            String[] toppingArray;
            String categoryType;
            switch (categoryChoice) {
                case 1 -> {
                    toppingArray = AllTopping.Meat;
                    categoryType = "MEAT";
                }
                case 2 -> {
                    toppingArray = AllTopping.Cheese;
                    categoryType = "CHEESE";
                }
                case 3 -> {
                    toppingArray = AllTopping.Regular;
                    categoryType = "REGULAR";
                }
                case 4 -> {
                    toppingArray = AllTopping.Sauce;
                    categoryType = "SAUCE";
                }
                case 5 -> {
                    toppingArray = AllTopping.Side;
                    categoryType = "SIDE";
                }
                default -> {
                    System.out.println("Invalid choice!");
                    continue;
                }
            }

            displayOptions("Available " + categoryType + " toppings:", toppingArray);
            int toppingOption = getValidatedOption("Choose topping (1-" + toppingArray.length + ")", toppingArray.length, i -> i >= 1 && i <= toppingArray.length);
            String toppingName = toppingArray[toppingOption - 1];

            boolean extra = false;
            if (categoryType.equals("MEAT") || categoryType.equals("CHEESE")) {
                extra = getYesNoChoice("Would you like EXTRA " + toppingName + "?");
                if (extra) {
                    double extraCost = sandwich.getExtraPrice(new Topping(toppingName, categoryType, true));
                    System.out.println("Added extra " + toppingName + " ($" + String.format("%.2f", extraCost) + ")");
                } else {
                    System.out.println(toppingName + " added successfully! (No extra charge)");
                }
            } else {
                if (getYesNoChoice("Would you like to add " + toppingName + "?")) {
                    System.out.println(toppingName + " added successfully! (Free topping)");
                } else {
                    System.out.println("No topping added.");
                    continue;
                }
            }

            sandwich.addTopping(new Topping(toppingName, categoryType, extra));
        }
    }
    public static void modifyToppings(Sandwich sandwich){
            while (true) {
                ArrayList<Topping> toppings = sandwich.getToppings();
                System.out.println("\nCurrent Toppings:");
                if (toppings.isEmpty()) System.out.println("No toppings yet.");
                else for (int i = 0; i < toppings.size(); i++)
                    System.out.println((i + 1) + ") " + toppings.get(i).getName());

                System.out.println("\n1) Add Topping\n2) Remove Topping\n3) Done");
                int choice = promptForInt("Enter your choice (1-3)");

                switch (choice) {
                    case 1 -> addToppingsToSandwich(sandwich);
                    case 2 -> {
                        if (toppings.isEmpty()) {
                            System.out.println("No toppings to remove.");
                            break;
                        }
                        int removeIndex = promptForInt("Enter the number of the topping to remove:");
                        if (removeIndex >= 1 && removeIndex <= toppings.size()) {
                            Topping removed = toppings.get(removeIndex - 1);

                            System.out.println(removed.getName() + " removed.");
                        } else System.out.println("Invalid selection.");
                    }
                    case 3 -> {
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            }
    }

    public static void modifySignatureSandwich(Sandwich sandwich){
        System.out.println("\n--- Modify Signature Sandwich ---");
        modifyToppings(sandwich);
        System.out.println("\nFinal Signature Sandwich:");
        System.out.println(sandwich.getDescription());
        System.out.println("Total Price: $" + String.format("%.2f", sandwich.getPrice()));
        System.out.println("========================================\n");
    }
}

