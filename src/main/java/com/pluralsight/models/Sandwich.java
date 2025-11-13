package com.pluralsight.models;
import java.util.ArrayList;


// Class representing a sandwich item in an order Inherits from OrderItems
public class Sandwich extends OrderItems {

    //Instance variables
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

     //Calculates the additional price for a given topping based on its type,(MEAT or CHEESE) and the sandwich bread size.

    public double getExtraPrice(Topping t) {
        double price = 0.0;

        if (t.getType().equals("MEAT")) {             // Check if the topping type is MEAT
            if (breadsize.equals("4")) price = 0.50;     // Assign price based on bread size
            else if (breadsize.equals("8")) price = 1.00;
            else if (breadsize.equals("12")) price = 1.50;

        } else if (t.getType().equals("CHEESE")) {     // Check if the topping type is CHEESE
            if (breadsize.equals("4")) price = 0.30;       // Assign price based on bread size
            else if (breadsize.equals("8")) price = 0.60;
            else if (breadsize.equals("12")) price = 0.90;
        }

        return price;                  // Return the calculated extra price
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

         // Determine the base sandwich price based on bread size
        if (breadsize.equals("4")) basePrice = 5.50;  // If the bread size is "4" inches, set the base price to $5.50
        else if (breadsize.equals("8")) basePrice = 7.00;
        else if (breadsize.equals("12")) basePrice = 8.50;

        double total = basePrice;   // Start total with base price

        // Add topping prices
        for (Topping topping : toppings) {

            // Loop through each topping and add its price to the total
            switch (topping.getType().toUpperCase()) {
                case "MEAT" -> {
                    switch (breadsize) {
                        case "4" -> total += topping.isExtra() ? 0.50 : 1.00;// For a 4-inch sandwich, add cost based on whether the topping is extra or not
                        case "8" -> total += topping.isExtra() ? 1.00 : 2.00;
                        case "12" -> total += topping.isExtra() ? 1.50 : 3.00;
                    }
                }
                case "CHEESE" -> {
                    switch (breadsize) {   // Adjust cheese topping prices depending on sandwich size and extra status
                        case "4" -> total += topping.isExtra() ? 0.30 : 0.75;
                        case "8" -> total += topping.isExtra() ? 0.60 : 1.50;
                        case "12" -> total += topping.isExtra() ? 0.90: 2.25;
                    }
                }
                default -> total += 0.0; // Regular, sauce, side toppings free
            }
        }
        return total;   // Return the final calculated price
    }

    // Show sandwich with toppings, including "(EXTRA)" if selected
    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(breadsize).append("\" ").append(breadType);       // Append bread size and type "8" Wheat)
        sb.append(" (Toasted: ").append(toasted ? "Yes" : "No").append(")");

        if (!toppings.isEmpty()) {  // If the sandwich has toppings, list them
            sb.append("\n   Toppings: ");

            for (int i = 0; i < toppings.size(); i++) {   // Loop through each topping
                Topping t = toppings.get(i);
                sb.append(t.toString()); // Topping.toString should include "(EXTRA)" if applicable
                if (i < toppings.size() - 1) sb.append(", "); // Add comma between toppings, but not after the last one
            }
        }
        return sb.toString();
    }


    // Return a concise summary of the sandwich, including size, bread type, toasted status, and price
    @Override
    public String toString() {
        return String.format("%s\" %s Sandwich%s - $%.2f",
                breadsize,
                breadType,
                toasted ? " (Toasted)" : "",
                getPrice());
    }

}
