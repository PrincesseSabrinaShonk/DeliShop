package com.pluralsight.models;

public class AllTopping {
    public static String[] Meat = { // Premium meat toppings
            "Steak",
            "Ham",
            "Salami",
            "Roast Beef",
            "Chicken",
            "Bacon" };

    public static String[] Cheese = {  // Premium cheese toppings
            "American",
            "Provolone",
            "Cheddar",
            "Swiss" };


    public static String[] Regular = {  // Regular toppings
            "Lettuce",
            "Peppers",
            "Onions",
            "Tomatoes",
            "Jalapeños",
            "Cucumbers",
            "Pickles",
            "Guacamole",
            "Mushrooms" };

    public static String[] Side = {  // Side toppings
            "Au Jus",
            "Sauce"
    };
    public static String[] Sauce = Sauces.Sauce;    //Sauce toppings - references the Sauces class array

    // Instance variables
    private String name;
    private String category;
    private boolean extra;


    // Constructor to create a topping instance.
    public AllTopping(String name, String category, boolean extra) {
        this.name = name;
        this.category = category;
        this.extra = extra;
    }
 }
