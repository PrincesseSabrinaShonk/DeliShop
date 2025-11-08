package com.pluralsight;

public class Chips extends OrderItems {  // --- Instance variables ---
    private String type;
    private static final double price = 1.50;


    public Chips(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String toString() {
        return  String.format("%s Chips - $%.2f", type, price);
    }
}

