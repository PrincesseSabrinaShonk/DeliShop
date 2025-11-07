package com.pluralsight;


import java.util.ArrayList;


public class Sandwich extends OrderItems {
    private String size;
    private String breadType;
    private boolean toasted;
    private ArrayList<Topping> toppings = new ArrayList<>();


    // Constructor
    public Sandwich(String size, String breadType, boolean toasted) {
        this.size = size;
        this.breadType = breadType;
        this.toasted = toasted;
    }

    //Add topping to the sandwich
    public void addTopping(Topping topping) {
        toppings.add(topping); //Add topping object to list
    }

    @Override
    public double getPrice() {
        double basePrice = 0;
        if(size.equals("4")) basePrice = 5.50;
        else if(size.equals("8")) basePrice = 7.00;
        else if(size.equals("12")) basePrice = 8.50;

        double total = basePrice; // Start with base
        // Base bread price
        for(Topping topping: toppings) {
            if(topping.getType().equals("MEAT")) {
                if(size.equals("4")) total += topping.isExtra() ? 0.50 : 1.00;
                else if(size.equals("8")) total += topping.isExtra() ? 1.00 : 2.00;
                else if(size.equals("12")) total += topping.isExtra() ? 1.50 : 3.00;
            } else if(topping.getType().equals("CHEESE")) {
                if(size.equals("4")) total += topping.isExtra() ? 0.30 : 0.75;
                else if(size.equals("8")) total += topping.isExtra() ? 0.60 : 1.50;
                else if(size.equals("12")) total += topping.isExtra() ? 0.90 : 2.25;
            }
        }
        return total; // Return total price
    }
    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append("\" ").append(breadType).append(" (Toasted: ").append(toasted).append(")\nToppings: ");
        for(Topping t : toppings) {
            sb.append(t.getName());           // Add topping name
            if(t.isExtra()) sb.append(" (extra)"); // Mark extra
            sb.append(", ");                  // Separator
        }
        return sb.toString(); // Return full description
    }
    }





