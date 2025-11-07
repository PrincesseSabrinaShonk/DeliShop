package com.pluralsight;

import java.util.ArrayList;

// Fields Instance Variables
public class Sandwich {
    private String size;
    private String breadType;
    private boolean toasted;
    private ArrayList<Topping> toppings;

    // Constructor
    public Sandwich(String size, String breadType, boolean toasted) {
        this.size = size;                                // Stores the size of the sandwich (e.g., small, medium, large)
        this.breadType = breadType;                      // Stores the type of bread:wrap, wheat, white, rye
        this.toasted = toasted;                          // Indicates whether the sandwich is toasted
        this.toppings = new ArrayList<>();               // A list to hold the toppings added to the sandwich
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBreadType() {
        return breadType;
    }

    public void setBreadType(String breadType) {
        this.breadType = breadType;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    // Method to Add a Topping
    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    // Method to Calculate Price
    public double calculatePrice() {
        double basePrice = 0;
        // Base bread price
        switch (size) {
            case "4":
                basePrice = 5.50;
                break;
            case "8":
                basePrice = 7.00;
                break;
            case "12":
                basePrice = 8.50;
                break;

        }
    }
}