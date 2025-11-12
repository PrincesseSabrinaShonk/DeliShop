package com.pluralsight.models;

public class SignatureSandwiches {
    // Calls the parent Sandwich constructor with predefined properties.
    public static Sandwich BLT() {


        // Call parent constructor which is the sandwich class
        Sandwich sandwich = new Sandwich("8", "white", true);

        // Add the signature BLT toppings
        sandwich.addTopping(new Topping("Bacon", "MEAT", false));
        sandwich.addTopping(new Topping("Cheddar", "CHEESE", false));
        sandwich.addTopping(new Topping("Lettuce", "REGULAR", false));
        sandwich.addTopping(new Topping("Tomato", "REGULAR", false));
        sandwich.addTopping(new Topping("Ranch", "SAUCE", false));

        return sandwich;
    }

    // Creates and returns a Philly Cheese Steak sandwich with predefined ingredients
    public static Sandwich PhillyCheeseSteak() {
        Sandwich sandwich = new Sandwich("8", "white", true);
        sandwich.addTopping(new Topping("Steak", "MEAT", false));
        sandwich.addTopping(new Topping("American", "CHEESE", false));
        sandwich.addTopping(new Topping("Peppers", "REGULAR", false));
        sandwich.addTopping(new Topping("Mayo", "SAUCE", false));
        return sandwich;

    }
}

/*
*Provides ready-to-use sandwiches so the user can add them to an order without manually selecting bread, size, or toppings.
* Keeps signature sandwich definitions centralized in one class.
 */