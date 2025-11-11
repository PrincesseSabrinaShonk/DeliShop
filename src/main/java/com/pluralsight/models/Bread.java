package com.pluralsight.models;

public class Bread {
    // Static array containing all bread types available at the deliShop
    public static String[] types = {
            "White",
            "Wheat",
            "Rye",
            "Wrap"
    };

    // Validates if the bread option selected by user is valid
    public static boolean isValid(int option){
        return option >= 1 && option <= types.length;
    }
}
