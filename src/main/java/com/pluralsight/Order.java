package com.pluralsight;
import java.util.ArrayList;

public class Order {

    // --- Instance Variables the data each Order object will store ---
    private ArrayList<Sandwich> sandwiches = new ArrayList<>();
    private ArrayList<String> drinks = new ArrayList<>();
    private ArrayList<String> chips = new ArrayList<>();


    public void addSandwich(Sandwich s) {     // This method takes a Sandwich object (s) and adds it to the sandwich list.
        sandwiches.add(s);
    }

    public void addDrink(String drink) {
        drinks.add(drink);
    }

    public void addChips(String chip) {
        chips.add(chip);
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

    public boolean isEmpty() {
        return sandwiches.isEmpty() && drinks.isEmpty() && chips.isEmpty();
    }

    public int getSandwichCount() {
        return sandwiches.size();
    }

    public boolean hasChipsOrDrink() {
        return !drinks.isEmpty() || !chips.isEmpty();
    }

    public void displayOrderDetails() {
        if (isEmpty()) {
            System.out.println("No items in your order.");
            return;
        }

        System.out.println("\n===== ORDER SUMMARY =====");

        if (!sandwiches.isEmpty()) {
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

        System.out.printf("\nTotal: $%.2f%n", calculateTotal());
        System.out.println("========================\n");
    }
}


