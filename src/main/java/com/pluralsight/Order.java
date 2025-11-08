package com.pluralsight;
import java.util.ArrayList;


   // Lists to hold the different types of order items
public class Order {
    private ArrayList<Sandwich> sandwiches ;
    private ArrayList<Drink> drinks;
    private ArrayList<Chips> chips;


    // Constructor initializes the lists when a new Order is created
    public Order(){
        this.drinks =new ArrayList<>();
        this.chips =new ArrayList<>() ;
        this.sandwiches = new ArrayList<>();
    }

    // Methods for adding items to the order
    public void addSandwich(Sandwich s) {
        sandwiches.add(s);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addChips(Chips chip) {
        this.chips.add(chip);
    }

    // Getter methods for each item list
    public ArrayList<Sandwich> getSandwiches() {
        return sandwiches;
    }
    public ArrayList<Drink> getDrinks() {
        return drinks;
    }
    public ArrayList<Chips> getChips() {
        return chips;
    }

    // Calculate the total price of the order
    public double calculateTotal() {
        double total = 0;
        // Using polymorphism - calling getPrice on OrderItem objects
        for (Sandwich sandwich : sandwiches) {      // Add up sandwich prices
            total += sandwich.getPrice();
        }
        for (Drink drink : drinks) {                 // Add up drink prices
            total += drink.getPrice();
        }
        for (Chips chip : chips) {                    // Add up chip prices
            total += chip.getPrice();
        }
        return total;

     }
           // Utility methods
     // Check if the order is empty
      public boolean isEmpty() {
        return sandwiches.isEmpty() && drinks.isEmpty() && chips.isEmpty();

    }
    // Get the number of sandwiches in the order.
    public int getSandwichCount() {
        return sandwiches.size();
    }

    // Check if the order contains at least one drink or chips
    public boolean hasChipsOrDrink() {
        return !drinks.isEmpty() || !chips.isEmpty();
    }

    // Display a detailed summary of the order in the console.
    public void displayOrderDetails() {
        if (isEmpty()) {      // If the order is empty, print a message and stop.
            System.out.println("No items in your order.");
            return;
        }
        System.out.println("\n===== ORDER SUMMARY =====");

        if (!sandwiches.isEmpty()) {     // Show all sandwiches with their index numbers.
            System.out.println("\n---- Sandwiches ----");
            for (int i = 0; i < sandwiches.size(); i++) {
                System.out.println((i + 1) + ". " + sandwiches.get(i));
            }
        }

        if (!drinks.isEmpty()) {
            System.out.println("---- Drinks ----");   // Display all drinks
            for (int i = 0; i < drinks.size(); i++) {
                System.out.println((i + 1) + ". " + drinks.get(i));
            }
        }

        if (!chips.isEmpty()) {
            System.out.println("---- Chips ----");      // Display all chips
            for (int i = 0; i < chips.size(); i++) {
                System.out.println((i + 1) + ". " + chips.get(i));
            }
        }
        //  // Display the total cost at the end
        System.out.printf("\nTotal: $%.2f%n", calculateTotal());
        System.out.println("========================\n");
    }
}


