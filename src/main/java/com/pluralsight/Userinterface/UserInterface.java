package com.pluralsight.Userinterface;


import com.pluralsight.data.ReceiptFileManager;
import com.pluralsight.models.*;

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
                case "5":
                    Sandwich sigSandwich = addSignatureSandwiches();
                    order.addSandwich(sigSandwich);
                    break;
                case "0":
                    System.out.println("\nOrder cancelled. Returning to Home Screen..");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    //Displays all available chip flavors and prompts the user to choose one.
    private static OrderItems addChips() {
        System.out.println("\n--- Add Chips ---");
        for (int i = 0; i < ChipsChoice.choice.length; i++) {
            System.out.println((i + 1) + ") " + ChipsChoice.choice[i]);
        }
        int option;
        while (true) {
            option = ConsoleHelper.promptForInt("Choose your chip flavor (1-" + ChipsChoice.choice.length + ")");
            if (option >= 1 && option <= ChipsChoice.choice.length) {
                break;
            }
            System.out.println("Invalid choice! Please select a number between 1 and " + ChipsChoice.choice.length);
        }
        String chipFlavor = ChipsChoice.choice[option - 1];
        Chips chips = new Chips(chipFlavor);
        System.out.println("\n✓ " + chipFlavor + " chips added successfully! ($1.50)\n");
        return chips;
    }

    //  you must have at least one sandwich OR chips/drink
    private static void checkout(Order order) {
        System.out.println("\n--- Checkout ---");
      // Must have at least one sandwich OR chips/drink
        if (order.getSandwichCount() == 0 && !order.hasChipsOrDrink()) {
            System.out.println("\n You must order at least one sandwich, chips, or drink to checkout.");
            System.out.println("Returning to Order Screen...\n");
            return;
        }
        order.displayOrderDetails(); //show order summary
        String confirm = ConsoleHelper.promptForString("\nWould you like to confirm your order? (yes/no)");

        if (confirm.equalsIgnoreCase("yes")) {
            ReceiptFileManager.saveReceipt(order);
            System.out.println("\n Order confirmed! Receipt saved successfully.");
            System.out.println("Thank you for your order!\n");
        } else {
            System.out.println("\nOrder cancelled. Returning to Home Screen...\n");


        }
    }

    private static OrderItems addDrink() {
        System.out.println("\n--- Add Drink ---");
        System.out.println("Select drink size:");
        System.out.println("1) Small ($2.00)");
        System.out.println("2) Medium ($2.50)");
        System.out.println("3) Large ($3.00)");
        String sizeChoice = ConsoleHelper.promptForString("Enter your drink Size");

        String size;  // Map user choice to size string
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
        System.out.println("\nAvailable Flavors:");

        for (String flavorOption : DrinkFlavor.flavors) {
            System.out.println("- " + flavorOption);
        }
        String flavor = ConsoleHelper.promptForString("Which flavor would you like");
        Drink drink = new Drink(size, flavor);

        // Confirm the drink was added
        System.out.println(size.substring(0, 1).toUpperCase() + size.substring(1) + " "
                + flavor + " drink added successfully! ($" + String.format("%.2f", drink.getPrice()) + ")\n");
        return drink;
    }   // need to implement

    private static OrderItems addSandwich() {
        System.out.println("\n---ADD SANDWICH---");
        // Choose bread type
        System.out.println("\nChoose your bread type:");
        for (int i = 0; i < Bread.types.length; i++) {
            System.out.println((i + 1) + ") " + Bread.types[i]);
        }
        int breadOption;
        while (true) {
            breadOption = ConsoleHelper.promptForInt("Enter bread option (1-" + Bread.types.length + ")");
            if (Bread.isValid(breadOption)) {
                break;
            }
            System.out.println("Invalid choice! Please select a number between 1 and " + Bread.types.length);
        }
        String breadType = Bread.types[breadOption - 1];

        // Choose sandwich size
        System.out.println("\nChoose your sandwich size:");
        for (int i = 0; i < SandwichSize.size.length; i++) {
            System.out.println((i + 1) + ") " + SandwichSize.size[i] + " Inches");
        }

        int sizeOption;
        while (true) {
            sizeOption = ConsoleHelper.promptForInt("Enter size option (1-3)");
            if (sizeOption >= 1 && sizeOption <= 3) {
                break;
            }
            System.out.println("Invalid choice! Please select 1, 2, or 3.");
        }
        String breadsize = SandwichSize.size[sizeOption - 1];

        // Choose if toasted
        System.out.println("\nWould you like it toasted?");
        System.out.println("1) Yes");
        System.out.println("2) No");

        int toastedOption;
        boolean toasted;
        while (true) {
            toastedOption = ConsoleHelper.promptForInt("Enter your choice (1-2)");
            if (toastedOption == 1) {
                toasted = true;
                break;
            } else if (toastedOption == 2) {
                toasted = false;
                break;
            }
            System.out.println("Invalid choice! Please enter 1 or 2.");
        }

        // Create the sandwich object
        Sandwich sandwich = new Sandwich(breadsize, breadType, toasted);

        // Display base sandwich info
        double basePrice = sandwich.getPrice();

        System.out.println("\n " + breadsize + "\" " + breadType + " sandwich created!");
        System.out.println("  Base price: $" + String.format("%.2f", basePrice));
        if (toasted) {
            System.out.println("  Toasted: Yes");
        }
        // Add toppings
        while (true) {
            System.out.println("\n----------------------------------------");
            System.out.println("Select topping category:");
            System.out.println("1) Meat (Premium)");
            System.out.println("2) Cheese (Premium)");
            System.out.println("3) Regular Toppings");
            System.out.println("4) Sauces");
            System.out.println("5) Sides");
            System.out.println("6) Done adding toppings");
            int categoryChoice = ConsoleHelper.promptForInt("Enter your choice (1-6)");

            if (categoryChoice == 6) {
                break;
            }
            String[] toppingArray;
            String categoryName;
            String categoryType;

            switch (categoryChoice) {
                case 1 -> { toppingArray = AllTopping.Meat; categoryType = "MEAT"; }
                case 2 -> { toppingArray = AllTopping.Cheese; categoryType = "CHEESE"; }
                case 3 -> { toppingArray = AllTopping.Regular; categoryType = "REGULAR"; }
                case 4 -> { toppingArray = AllTopping.Sauce; categoryType = "SAUCE"; }
                case 5 -> { toppingArray = AllTopping.Side; categoryType = "SIDE"; }
                default -> { System.out.println("Invalid choice!"); continue; }
            }

            // Display available toppings
            System.out.println("\nAvailable " + categoryType + " toppings:");
            for (int i = 0; i < toppingArray.length; i++) {
                System.out.println((i + 1) + ") " + toppingArray[i]);
            }

            int toppingOption;
            while (true) {
                toppingOption = ConsoleHelper.promptForInt("Choose topping (1-" + toppingArray.length + ")");
                if (toppingOption >= 1 && toppingOption <= toppingArray.length) {
                    break;
                }
                System.out.println("Invalid choice! Please select a number between 1 and " + toppingArray.length);
            }

            String toppingName = toppingArray[toppingOption - 1];
            boolean extra = false;
            double toppingCost = 0;

            // For premium toppings (meat/cheese), ask if they want extra
            if (categoryType.equals("MEAT") || categoryType.equals("CHEESE")) {
                System.out.println("\nWould you like EXTRA " + toppingName + "?");
                System.out.println("1) Yes");
                System.out.println("2) No");

                int extraChoice;
                while (true) {
                    extraChoice = ConsoleHelper.promptForInt("Enter your choice (1-2)");
                    if (extraChoice == 1) {
                        extra = true;
                        Topping tempTopping = new Topping(toppingArray[toppingOption - 1], categoryType, true);
                        double extraCost = sandwich.getExtraPrice(tempTopping);
                        System.out.println("Added extra " + toppingArray[toppingOption- 1] +
                                " (" + categoryType + ") $" + String.format("%.2f", extraCost));
                        break;
                    } else if (extraChoice == 2) {
                        extra = false;
                        System.out.println("\n " + toppingName + " added successfully! (No additional charge)");
                        break;
                    }
                    System.out.println("Invalid choice! Please enter 1 or 2.");      //todo
                }
            }
            else{
                System.out.println("\nWould you like to add " + toppingName + "?");
                System.out.println("1) Yes");
                System.out.println("2) No");

                int addChoice;
                while (true) {
                    addChoice = ConsoleHelper.promptForInt("Enter your choice (1-2)");
                    if (addChoice == 1) {
                        System.out.println(toppingName + " added successfully! (Free topping)");
                        sandwich.addTopping(new Topping(toppingName, categoryType, false));
                        break;
                    } else if (addChoice == 2) {
                        System.out.println("No topping added.");
                        break;
                    }
                    System.out.println("Invalid choice! Please enter 1 or 2.");
                }
                continue; // skip the extra section below since handled here

            }
            sandwich.addTopping(new Topping(toppingName, categoryType, extra));
        }

        // Display final sandwich summary
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
            System.out.println("\n✓ BLT sandwich added!");
        } else {
            sandwich = SignatureSandwiches.PhillyCheeseSteak();
            System.out.println("\n✓ Philly Cheese Steak added!");
        }

        System.out.println("Base price: $" + String.format("%.2f", sandwich.getPrice()));
        return sandwich;
    }

    }


