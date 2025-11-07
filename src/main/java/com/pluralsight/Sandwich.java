package com.pluralsight;
import java.util.ArrayList;


public class Sandwich {
    private String size;
    private String breadType;
    private boolean toasted;
    private ArrayList<Topping> toppings;

    // Constructor
    public Sandwich(String size, String breadType, boolean toasted) {
        this.size = size;
        this.breadType = breadType;
        this.toasted = toasted;
        this.toppings = new ArrayList<>();
    }

    // Used to access or modify the private variables. getters and setters
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

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    // Method to Add a Topping
    public void addTopping(Topping topping) {
        toppings.add(topping);
    }


    // Method to Calculate Price of the Sandwich
    public double calculatePrice() {
        double basePrice = 0;
        // Base bread price
        switch (size) {     // Set the base sandwich price depending on its size.
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
        // Loop through all toppings and add their costs to the total.
        for (Topping topping : toppings) {
            if (topping.getCategory().equalsIgnoreCase("meat")) {   // If the topping is meat, add either regular or extra meat price.
                if (topping.isExtra()) {
                    basePrice += getExtraMeatPrice();
                } else {
                    basePrice += getMeatPrice();
                }
                // If the topping is cheese, add either regular or extra cheese price.
            } else if (topping.getCategory().equalsIgnoreCase("cheese")) {
                if (topping.isExtra()) {
                    basePrice += getExtraCheesePrice();
                } else {
                    basePrice += getCheesePrice();
                }
            }
        }
        return basePrice;  // Return the final total price of the sandwich.
    }
    private double getMeatPrice() {
        return 0;
    }
    private double getExtraMeatPrice() {
        return 0;
    }
    private double getCheesePrice() {
        return 0;
    }
    private double getExtraCheesePrice() {
        return 0;
    }
    }


