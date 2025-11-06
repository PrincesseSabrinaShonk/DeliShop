package com.pluralsight;

import java.util.ArrayList;

public class Order {

    private ArrayList<Sandwich> sandwiches = new ArrayList<>();
    private ArrayList<String> drinks = new ArrayList<>();
    private ArrayList<String> chips = new ArrayList<>();


    public void addSandwich(Sandwich s) {
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

    public void displayOrderDetails() {
        if (sandwiches.isEmpty() && drinks.isEmpty() && chips.isEmpty()) {
            System.out.println("No items in your order.");
            return;
        }

        System.out.println("---- Sandwiches ----");
        for (Sandwich s : sandwiches) {
            System.out.println(s);
        }

        System.out.println("---- Drinks ----");
        for (String d : drinks) {
            System.out.println(d);
        }

        System.out.println("---- Chips ----");
        for (String c : chips) {
            System.out.println(c);

        }
    }
}