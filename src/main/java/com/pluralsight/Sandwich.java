package com.pluralsight;
import java.util.ArrayList;

//Instance variables
public class Sandwich extends OrderItems {
    private String size;
    private String breadType;
    private boolean toasted;
    private ArrayList<Topping> toppings = new ArrayList<>();


    // Constructor
    public Sandwich(String size, String breadType, boolean toasted) {
        this.size = size;
        this.breadType = breadType;
        this.toasted = toasted;
    }
    public void setToasted(boolean toasted) {
        this.toasted = toasted; // setters
    }

    //Add topping to the sandwich
    public void addTopping(Topping topping) {
        toppings.add(topping); //Add topping object to list
    }

    // Calculate sandwich price
    @Override
    public double getPrice() {
        double basePrice = 0;
        // Set the base price depending on sandwich size
        if(size.equals("4")) basePrice = 5.50;
        else if(size.equals("8")) basePrice = 7.00;
        else if(size.equals("12")) basePrice = 8.50;


        // Start total with the base bread price
        double total = basePrice;
        // Base bread price
        for(Topping topping: toppings) {
            if(topping.getType().equals("MEAT")) {    // If the topping is meat
                // Add meat price depending on sandwich size and whether it's extra
                if(size.equals("4")) total += topping.isExtra() ? 0.50 : 1.00;
                else if(size.equals("8")) total += topping.isExtra() ? 1.00 : 2.00;
                else if(size.equals("12")) total += topping.isExtra() ? 1.50 : 3.00;

            } else if(topping.getType().equals("CHEESE")) {    // If the topping is cheese

                // Add cheese price depending on sandwich size and whether it's extra
                if(size.equals("4")) total += topping.isExtra() ? 0.30 : 0.75;
                else if(size.equals("8")) total += topping.isExtra() ? 0.60 : 1.50;
                else if(size.equals("12")) total += topping.isExtra() ? 0.90 : 2.25;
            }
        }
        //Return the final total price of the sandwich including all topping
        return total;
    }
    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append("\" ").append(breadType).append(" (Toasted: ").append(toasted).append(")\nToppings: ");
        for(Topping t : toppings) {
            sb.append(t.getName());
            if(t.isExtra()) sb.append(" (extra)");
            sb.append(", ");
        }
        return sb.toString();
        }
    }





