package com.pluralsight.models;

// Abstract class that serves as the base for all orderable items
public abstract class OrderItems {

    public abstract double getPrice();
    public abstract String getDescription();

}
// The job of the abstract class is to get the price and description