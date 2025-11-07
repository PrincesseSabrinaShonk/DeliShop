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

    public double calculateTotal() {
        // Will calculate in Phase 2 (based on sandwich, drink, chip prices)
        return 0.0;
    }

    // This method prints out everything the customer has ordered.
    public void displayOrderDetails() {
        if (sandwiches.isEmpty() && drinks.isEmpty() && chips.isEmpty()) {    // If nothing has been added to the order, tell the user their order is empty.
            System.out.println("No items in your order.");
            return;
        }

        // --- Display all sandwiches in the order ---
        System.out.println("---- Sandwiches ----");
        for (Sandwich s : sandwiches) {
            System.out.println(s);
        }
        // --- Display all drinks in the order ---
        System.out.println("---- Drinks ----");
        for (String d : drinks) {
            System.out.println(d);
        }
        // --- Display all chips in the order ---
        System.out.println("---- Chips ----");
        for (String c : chips) {
            System.out.println(c);

        }
    }
}