package com.pluralsight.data;
import com.pluralsight.models.Chips;
import com.pluralsight.models.Drink;
import com.pluralsight.models.Order;
import com.pluralsight.models.Sandwich;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class ReceiptFileManager {

    // Method to save a customer's order details to a receipt text file
    public static void saveReceipt(Order order) {
        try {
            LocalDateTime now = LocalDateTime.now();  // Get the current date and time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
            String filename = "receipts/" + now.format(formatter) + ".txt";

            FileWriter w = new FileWriter(filename);
            DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");

            // HEADER
            w.write("========================================\n");
            w.write("           WELCOME TO OUR  DELI-SHOP\n");
            w.write("========================================\n\n");


            // SANDWICHES
            if (!order.getSandwiches().isEmpty()) {
                w.write("------------ SANDWICHES ---------------\n");  // Loop through each sandwich in the 'sandwiches' list
                for (int i = 0; i < order.getSandwiches().size(); i++) {
                    Sandwich sandwich = order.getSandwiches().get(i);   // Retrieve the sandwich object
                    w.write((i + 1) + ") " + sandwich.getDescription() + "\n");   // Write the sandwich description
                    w.write(String.format("   Price: $%.2f%n", sandwich.getPrice())); // Write the price of the sandwich to the output
                    System.out.println();
                }
            }
            // DRINKS
            if (!order.getDrinks().isEmpty()) {
                w.write("--------------- DRINKS ----------------\n");
                for (int i = 0; i < order.getDrinks().size(); i++) {
                    Drink drink = order.getDrinks().get(i);
                    w.write((i + 1) + ") " + drink.toString() + "\n");
                    System.out.println();
                }
            }
            // CHIPS
            if (!order.getChips().isEmpty()) {
                w.write("--------------- CHIPS -----------------\n");
                for (int i = 0; i < order.getChips().size(); i++) {
                    Chips chip = order.getChips().get(i);
                    w.write((i + 1) + ") " + chip.toString() + "\n");
                    System.out.println();
                }
            }
            // TOTAL
            w.write("========================================\n");
            w.write(String.format(" RECEIPT TOTAL: $%.2f%n", order.calculateTotal()));
            w.write("========================================\n");
            w.write("Thank you for visiting our DELI-SHOP!\n");

            w.close();

            System.out.println("\n========================================");
            System.out.println(" Receipt saved successfully!");
            System.out.println(" File: " + filename);
            System.out.println("========================================");

        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}

