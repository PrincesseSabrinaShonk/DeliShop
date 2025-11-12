package com.pluralsight.models;

import java.util.ArrayList;
public class Order {
    private ArrayList<Sandwich> sandwiches;
    private ArrayList<Drink> drinks;
    private ArrayList<Chips> chips;

    // Constructor initializes the lists when a new Order is created
    public Order() {
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
        this.sandwiches = new ArrayList<>();
    }

    // Methods for adding items to the order
    public void addSandwich(Sandwich s) {
        sandwiches.add(s);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addChips(Chips chip) {
        this.chips.add(chip);
    }

    // Getter methods for each item list
    public ArrayList<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public ArrayList<Chips> getChips() {
        return chips;
    }

    // Calculate the total price of the order
    public double calculateTotal() {
        double total = 0;
        // Using polymorphism - calling getPrice on OrderItem objects
        for (Sandwich sandwich : sandwiches) {      // Add up sandwich prices
            total += sandwich.getPrice();
        }
        for (Drink drink : drinks) {                 // Add up drink prices
            total += drink.getPrice();
        }
        for (Chips chip : chips) {                    // Add up chip prices
            total += chip.getPrice();
        }
        return total;

    }

    // Utility methods
    // Check if the order is empty
    public boolean isEmpty() {
        return sandwiches.isEmpty() && drinks.isEmpty() && chips.isEmpty();

    }

    // Get the number of sandwiches in the order.
    public int getSandwichCount() {
        return sandwiches.size();
    }

    // Check if the order contains at least one drink or chips
    public boolean hasChipsOrDrink() {
        return !drinks.isEmpty() || !chips.isEmpty();
    }

    public String getOrderDetails() {
        StringBuilder details = new StringBuilder();    // Use StringBuilder for efficient string concatenation

        details.append("\n===== ORDER SUMMARY =====\n");
        if (!sandwiches.isEmpty()) {                          // Check if there are any sandwiches in the order
            details.append("\n---- Sandwiches ----\n");
            for (int i = 0; i < sandwiches.size(); i++) {      // Loop through all sandwiches in the order
                details.append((i + 1))
                        .append(". ").append(sandwiches.get(i).getDescription())
                        .append("\n");
                details.append(String.format("   Price: $%.2f%n", sandwiches.get(i).getPrice()));
            }
        }

        if (!drinks.isEmpty()) {
            details.append("\n---- Drinks ----\n");
            for (int i = 0; i < drinks.size(); i++) {
                details.append((i + 1))
                        .append(". ")
                        .append(drinks.get(i).toString()).append("\n");
            }
        }
        if (!chips.isEmpty()) {
            details.append("\n---- Chips ----\n");
            for (int i = 0; i < chips.size(); i++) {
                details.append((i + 1))
                        .append(". ")
                        .append(chips.get(i).toString()).append("\n");
            }
        }
        details.append(String.format("\nTotal: $%.2f%n", calculateTotal()));
        details.append("========================\n");
        return details.toString();
    }

    // Display a detailed summary of the order in the console.
    public String displayOrderDetails() {
        if (isEmpty()) {      // If the order is empty, print a message and stop
            return "No items in your order.";
        }
        return getOrderDetails();
    }   // todo display as String


    public void addSignatureSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);

       }
   }


