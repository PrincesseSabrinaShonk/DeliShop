package com.pluralsight;

public class AllTopping {
    public static String[] Meat = {
            "Steak",
            "Ham",
            "Salami",
            "Roast Beef",
            "Chicken",
            "Bacon" };
    public static String[] Cheese = {
            "American",
            "Provolone",
            "Cheddar",
            "Swiss" };
    public static String[] Regular = {
            "Lettuce",
            "Peppers",
            "Onions",
            "Tomatoes",
            "Jalapeños",
            "Cucumbers",
            "Pickles",
            "Guacamole",
            "Mushrooms" };

    public static String[] Side = {
            "Au Jus",
            "Sauce"
    };
    public static String[] Sauce = Sauces.Sauce;

    // Instance variables
    private String name;
    private String category;
    private boolean extra;

    public AllTopping(String name, String category, boolean extra) {
        this.name = name;
        this.category = category;
        this.extra = extra;
    }

}
