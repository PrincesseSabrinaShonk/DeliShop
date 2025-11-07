package com.pluralsight;
import java.util.ArrayList;

public class Order {

    // --- Instance Variables the data each Order object will store ---
    private ArrayList<Sandwich> sandwiches ;
    private ArrayList<Drink> drinks;
    private ArrayList<Chips> chips;

    public Order(ArrayList<Drink> drinks, ArrayList<Chips> chips) {
        this.drinks =new ArrayList<>();
        this.chips =new ArrayList<>() ;
        this.sandwiches = new ArrayList<>();
    }

    public void addSandwich(Sandwich s) {
        sandwiches.add(s);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addChips(Chips chips) {
        this.chips.add(chips);

    }
    public ArrayList<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public ArrayList<Chips> getChips() {
        return chips;
    }

    public double calculateTotal() {
        double total = 0;

        // Add sandwich prices
        for (Sandwich sandwich : sandwiches) {
            total += sandwich.calculatePrice();
        }

        // Add drink prices
        for (Drink drink : drinks) {
            total += drink.getPrice();
        }

        // Add chips prices
        for (Chips chip : chips) {
            total += chip.getPrice();
        }

        return total;
     }
     // Check if the order has no items.
      public boolean isEmpty() {
        return sandwiches.isEmpty() && drinks.isEmpty() && chips.isEmpty();
    }
    // Get the number of sandwiches in the order.
    public int getSandwichCount() {
        return sandwiches.size();
    }
    // Check if the order includes at least one drink or chips.
    public boolean hasChipsOrDrink() {
        return !drinks.isEmpty() || !chips.isEmpty();
    }
    // Display a detailed summary of the order in the console.
    public void displayOrderDetails() {
        if (isEmpty()) {      // If the order is empty, print a message and stop.
            System.out.println("No items in your order.");
            return;
        }
        System.out.println("\n===== ORDER SUMMARY =====");

        if (!sandwiches.isEmpty()) {     // Show all sandwiches with their index numbers.
            System.out.println("\n---- Sandwiches ----");
            for (int i = 0; i < sandwiches.size(); i++) {
                System.out.println((i + 1) + ". " + sandwiches.get(i));
            }
        }

        if (!drinks.isEmpty()) {
            System.out.println("---- Drinks ----");
            for (int i = 0; i < drinks.size(); i++) {
                System.out.println((i + 1) + ". " + drinks.get(i));
            }
        }

        if (!chips.isEmpty()) {
            System.out.println("---- Chips ----");
            for (int i = 0; i < chips.size(); i++) {
                System.out.println((i + 1) + ". " + chips.get(i));
            }
        }
        // Display the total cost of the entire order.
        System.out.printf("\nTotal: $%.2f%n", calculateTotal());
        System.out.println("========================\n");
    }
}


