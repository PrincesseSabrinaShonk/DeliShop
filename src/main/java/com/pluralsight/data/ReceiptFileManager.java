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

            // Create a FileWriter to write to the receipt file
            FileWriter w = new FileWriter(filename);
            w.write("===== DELI-SHOP Receipt =====\n");

            // Print current date/time again for clarity
            w.write("Date: " + now.format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a")) + "\n");
            w.write("================================\n\n");

            // HEADER
            w.write("========================================\n");
            w.write("       WELCOME TO OUR  DELI-SHOP!       \n");
            w.write("========================================\n\n");

            w.write("Date: " + now.format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a")) + "\n");
            w.write("========================================\n\n");

            // SANDWICHES SECTION
            if (!order.getSandwiches().isEmpty()) {   // Check if there are any sandwiches in the order before printing
                w.write("------------ SANDWICHES ---------------\n");
                for (int i = 0; i < order.getSandwiches().size(); i++) {
                    Sandwich sandwich = order.getSandwiches().get(i);

                    w.write((i + 1) + ") " + sandwich.getDescription() + "\n");  // Write sandwich description and price
                    w.write(String.format("   Price: $%.2f%n", sandwich.getPrice()));
                    w.write("----------------------------------------\n");
                }
                w.write("\n");
            }

            // DRINKS SECTION
            // Print all drinks if any were ordered
            if (!order.getDrinks().isEmpty()) {
                w.write("--------------- DRINKS ----------------\n");
                for (int i = 0; i < order.getDrinks().size(); i++) {
                    Drink drink = order.getDrinks().get(i);

                    w.write((i + 1) + ") " + drink.toString() + "\n"); // Display drink info using its toString() format
                    w.write("----------------------------------------\n");
                }
                w.write("\n");
            }

            // CHIPS SECTION
            if (!order.getChips().isEmpty()) {
                w.write("--------------- CHIPS -----------------\n");
                for (int i = 0; i < order.getChips().size(); i++) {
                    Chips chip = order.getChips().get(i);
                    w.write((i + 1) + ") " + chip.toString() + "\n");
                    w.write("----------------------------------------\n");
                }
                w.write("\n");
            }

            // TOTAL
            // Print the overall total price for the entire order
            w.write("========================================\n");
            w.write(String.format(" RECEIPT TOTAL: $%.2f%n", order.calculateTotal()));
            w.write("========================================\n");
            w.write("\nThank you for visiting our DELI-SHOP!\n");

            w.close();

            System.out.println("\n========================================");
            System.out.println(" Receipt saved successfully!");
            System.out.println(" File: " + filename);
            System.out.println("========================================");

        } catch (IOException e) {   // Handle any input/output errors that occur while writing the file
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}