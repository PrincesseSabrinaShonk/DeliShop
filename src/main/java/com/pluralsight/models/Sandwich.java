package com.pluralsight.models;

import java.util.ArrayList;

//Instance variables
public class Sandwich extends OrderItems {
    private String breadsize;  // 4/8/12
    private String breadType; // white, wheat, rye, wrap
    private boolean toasted;
    private ArrayList<Topping> toppings = new ArrayList<>();

    // Constructor
    public Sandwich(String breadsize, String breadType, boolean toasted) {
        this.breadsize = breadsize;
        this.breadType = breadType;
        this.toasted = toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    // Add topping to the sandwich
    public void addTopping(Topping topping) {
        toppings.add(topping);
    }
    public double getExtraPrice(Topping t) {
        double price = 0.0;

        if (t.getType().equals("MEAT")) {
            if (breadsize.equals("4")) price = 0.50;
            else if (breadsize.equals("8")) price = 1.00;
            else if (breadsize.equals("12")) price = 1.50;
        } else if (t.getType().equals("CHEESE")) {
            if (breadsize.equals("4")) price = 0.30;
            else if (breadsize.equals("8")) price = 0.60;
            else if (breadsize.equals("12")) price = 0.90;
        }

        return price;
    }

    public boolean isToasted() {
        return toasted;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    @Override
    public double getPrice() {
        double basePrice = 0;

        // Set base sandwich price by size
        if (breadsize.equals("4")) basePrice = 5.50;
        else if (breadsize.equals("8")) basePrice = 7.00;
        else if (breadsize.equals("12")) basePrice = 8.50;

        double total = basePrice;

        // Add topping prices
        for (Topping topping : toppings) {

            switch (topping.getType().toUpperCase()) {
                case "MEAT" -> {
                    switch (breadsize) {
                        case "4" -> total += topping.isExtra() ? 0.50 : 1.00;
                        case "8" -> total += topping.isExtra() ? 1.00 : 2.00;
                        case "12" -> total += topping.isExtra() ? 1.50 : 3.00;
                    }
                }
                case "CHEESE" -> {
                    switch (breadsize) {
                        case "4" -> total += topping.isExtra() ? 0.30 : 0.75;
                        case "8" -> total += topping.isExtra() ? 0.60 : 1.50;
                        case "12" -> total += topping.isExtra() ? 0.90: 2.25;
                    }
                }
                default -> total += 0.0; // Regular, sauce, side toppings free
            }
        }

        return total;
    }

    // Show sandwich with toppings, including "(EXTRA)" if selected
    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();

        sb.append(breadsize).append("\" ").append(breadType);
        sb.append(" (Toasted: ").append(toasted ? "Yes" : "No").append(")");

        if (!toppings.isEmpty()) {
            sb.append("\n   Toppings: ");
            for (int i = 0; i < toppings.size(); i++) {
                Topping t = toppings.get(i);
                sb.append(t.toString()); // Topping.toString should include "(EXTRA)" if applicable
                if (i < toppings.size() - 1) sb.append(", ");
            }
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("%s\" %s Sandwich%s - $%.2f",
                breadsize,
                breadType,
                toasted ? " (Toasted)" : "",
                getPrice());
    }

}
