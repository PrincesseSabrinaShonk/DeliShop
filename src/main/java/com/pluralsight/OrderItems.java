package com.pluralsight;

public abstract class OrderItems {   // Abstract class that serves as the base for all orderable items

    // Every order item must have a price
    public abstract double getPrice();
    public abstract String getDescription();


}
