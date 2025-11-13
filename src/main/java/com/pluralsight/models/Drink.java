package com.pluralsight.models;

public class Drink extends OrderItems {    // --- Instance variables ---
    private String size;                    // small, medium, or large drink
    private String flavor;                  // the flavor of the drink: Coke, Sprite, Lemonade


    // Constructor
    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    //Getters and Setters
    public String getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    //Override methods from OrderItems
    @Override
    public double getPrice() {
        switch (size.toLowerCase()) {   // Use a switch statement to return the price based on the size of the item
            case "small":
                return 2.00;    // Price for small size
            case "medium":
                return 2.50;    // Price for medium size
            case "large":
                return 3.00;       // Price for large size
            default:
                return 0;
        }
    }

    // Returns a short description of the drink
    @Override
    // Capitalize the first letter of the size and combine it with the flavor to generate a description for the drink.
    public String getDescription() {
        return size.substring(0, 1).toUpperCase()
                + size.substring(1) + " " + flavor + " Drink";

    }

    @Override
    public String toString() {
        return String.format("%s %s Drink - $%.2f",
                size.substring(0, 1).toUpperCase() + size.substring(1),
                flavor,
                getPrice());
    }

}


