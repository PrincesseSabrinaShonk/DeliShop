package com.pluralsight;

public class Chips extends OrderItems {        //Instance variables
    private String type;
    private static final double price = 1.50;   // All chips have the same price

    // Constructor initializes the chip type
    public Chips(String type) {
        this.type = type;
    }

    // Getter and setter for the chip type
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    // Override methods from OrderItems
    @Override
    public double getPrice() {     // Returns the fixed price for chips
        return price;
    }

    // Returns a short description used for receipts or menus
    @Override
    public String getDescription() {
        return type;
    }
//
//    // Defines how the chip item appears as text
//    @Override
//    public String toString() {
//        return  String.format("%s Chips - $%.2f", type, price);
    }

