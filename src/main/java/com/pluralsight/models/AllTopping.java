package com.pluralsight.models;

// Static array 'Meat' contains premium meat toppings available for sandwiches.
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

    // Instance variables for a topping instance
    private String name;
    private String category;
    private boolean extra;


    // Constructor to create instance of a topping.
    public AllTopping(String name, String category, boolean extra) {
        this.name = name;                // Assign the name of the topping
        this.category = category;        // Assign the category of the topping
        this.extra = extra;               // Assign if the topping is an extra charge or not
    }
 }
