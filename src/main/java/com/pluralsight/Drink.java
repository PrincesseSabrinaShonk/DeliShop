package com.pluralsight;

public class Drink extends OrderItems {    // --- Instance variables ---
    private String size;                    // small, medium, or large
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
        switch (size.toLowerCase()) {
            case "small":
                return 2.00;
            case "medium":
                return 2.50;
            case "large":
                return 3.00;
            default:
                return 0;
        }
    }

    // Returns a short description of the drink
    @Override
    public String getDescription() {
        return size +"Drink (" + flavor +  ")";

    }

    @Override
    public String toString() {
        return String.format("%s %s Drink - $%.2f",
                size.substring(0, 1).toUpperCase() + size.substring(1),
                flavor,
                getPrice());
    }

}


