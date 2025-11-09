package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptFileManager {
    //Saves the order details to a receipt file in the receipt folder.
    public static void saveReceipt(Order order) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String filename = "receipts/" + now.format(formatter) + ".txt";
        try {
            // Create a FileWriter to write to the receipt file
            FileWriter w = new FileWriter(filename);

            // Write the receipt header with date/time
            w.write("===== DELI-SHOP Receipt =====\n");
            w.write("Date: " + now.format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a")) + "\n");
            w.write("================================\n\n");

            // Write all sandwiches in the order
            if (!order.getSandwiches().isEmpty()) {
                w.write("---- Sandwiches ----\n");
                for (int i = 0; i < order.getSandwiches().size(); i++) {   // Loop through each sandwich in the order
                    Sandwich sandwich = order.getSandwiches().get(i);      // Get the current sandwich object
                    w.write((i + 1) + ". " + sandwich.getDescription() + "\n");// Write the sandwich number and description to the file
                    w.write(String.format("   Price: $%.2f%n", sandwich.getPrice()));  // Write the price of the sandwich
                }
                w.write("\n");  // Add an empty line after listing all sandwiches for readability
            }
            // Write all drinks in the order
            if (!order.getDrinks().isEmpty()) {
                w.write("---- Drinks ----\n");
                for (int i = 0; i < order.getDrinks().size(); i++) {
                    Drink drink = order.getDrinks().get(i);
                    w.write((i + 1) + ". " + drink.toString() + "\n");
                }
                w.write("\n");
            }

            // Write all chips in the order
            if (!order.getChips().isEmpty()) {
                w.write("---- Chips ----\n");
                for (int i = 0; i < order.getChips().size(); i++) {
                    Chips chip = order.getChips().get(i);
                    w.write((i + 1) + ". " + chip.toString() + "\n");
                }
                w.write("\n");
            }
            // Write the total price
            w.write("================================\n");
            w.write(String.format("Total: $%.2f%n", order.calculateTotal()));
            w.write("================================\n");
            w.write("\nThank you for choosing DELI-SHOP!\n");

            // Close the writer to save the file
            w.close();
            System.out.println("\n Receipt saved successfully to: " + filename);

        } catch (IOException e) {
            // Handle any file writing errors
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}