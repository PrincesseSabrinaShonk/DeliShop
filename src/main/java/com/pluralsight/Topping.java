package com.pluralsight;

public class Topping {
    private String name;
    private String category; // meat, cheese, regular, sauce
    private boolean extra;

    public Topping(String name, String category, boolean extra) {
        this.name = name;
        this.category = category;
        this.extra = extra;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public boolean isExtra() {
        return extra;
    }

    @Override
    public String toString() {
        return "Topping{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", extra=" + extra +
                '}';
    }
}
