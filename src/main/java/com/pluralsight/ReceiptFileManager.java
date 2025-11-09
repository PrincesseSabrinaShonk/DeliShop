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
                for (int i = 0; i < order.getSandwiches().size(); i++) {
                    Sandwich sandwich = order.getSandwiches().get(i);
                    w.write((i + 1) + ". " + sandwich.getDescription() + "\n");
                    w.write(String.format("   Price: $%.2f%n", sandwich.getPrice()));
                }
                w.write("\n");

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}