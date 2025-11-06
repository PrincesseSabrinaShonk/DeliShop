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
        this.breadType = breadType;                      // Stores the type of bread (e.g., wheat, white, rye
        this.toasted = toasted;                          // Indicates whether the sandwich is toasted (true or false)
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
        return 0.0;
    }

    @Override
    public String toString() {
        return "Sandwich{" +
                "size='" + size + '\'' +
                ", breadType='" + breadType + '\'' +
                ", toasted=" + toasted +
                ", toppings=" + toppings +
                '}';
    }
}
