package com.pluralsight.models;

public class Topping {
    private String name;
    private String type; // meat, cheese, regular, sauce
    private boolean extra;
    private double extraPrice;

    public Topping(String name, String type, boolean extra, double extraPrice) {
        this.name = name;
        this.type = type;
        this.extra = extra;
        this.extraPrice = extraPrice;
    }

  //  CONSTRUCTOR  lets you create toppings without needing to specify extraPrice
    public Topping(String name, String type, boolean extra) {
        this(name, type, extra, 0.0);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isExtra() {
        return extra;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
    }

    public double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(double extraPrice) {
        this.extraPrice = extraPrice;
    }
    public static boolean isValidOptions(int choice, String[] options) {
        // Returns true if the choice number is between 1 and the length of the array
        return choice >= 1 && choice <= options.length;
    }

    @Override
    public String toString() {                          // If the item is marked as "extra" but has no additional cost,
                                                       // just show the name with "(extra)" appended.
        if (extra && extraPrice > 0) {
            return String.format("%s (extra) +$%.2f", name, extraPrice);
        }
        else if (extra) {                               // Otherwise, if the item is not an extra, return only its name.
            return name + " (extra)";
        }
        else {
            return name;
        }
    }
}

