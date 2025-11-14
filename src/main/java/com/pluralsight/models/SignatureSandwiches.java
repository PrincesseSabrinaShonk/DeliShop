package com.pluralsight.models;

public class SignatureSandwiches {
    // Creates and returns a BLT sandwich with predefined ingredients
    public static Sandwich BLT() {


        // Make a new Sandwich: 8-inch, white bread, toasted
        Sandwich sandwich = new Sandwich("8", "white", true);

        // Add the signature BLT toppings
        sandwich.addTopping(new Topping("Bacon", "MEAT", false));
        sandwich.addTopping(new Topping("Cheddar", "CHEESE", false));
        sandwich.addTopping(new Topping("Lettuce", "REGULAR", false));
        sandwich.addTopping(new Topping("Tomato", "REGULAR", false));
        sandwich.addTopping(new Topping("Ranch", "SAUCE", false));
        return sandwich;   // Return the fully made BLT sandwich
    }

    // Creates and returns a Philly Cheese Steak sandwich with predefined ingredients
    public static Sandwich PhillyCheeseSteak() {   // Add all the toppings for a Philly Cheese Steak

        Sandwich sandwich = new Sandwich("8", "white", true);
        sandwich.addTopping(new Topping("Steak", "MEAT", false));
        sandwich.addTopping(new Topping("American", "CHEESE", false));
        sandwich.addTopping(new Topping("Peppers", "REGULAR", false));
        sandwich.addTopping(new Topping("Mayo", "SAUCE", false));
        return sandwich;  // Return the fully made Philly Cheese Steak

    }
}
// Call parent constructor which is the sandwich class
/*
*Provides ready-to-use sandwiches so the user can add them to an order without manually selecting bread, size, or toppings.
* Keeps signature sandwich definitions centralized in one class.
 */