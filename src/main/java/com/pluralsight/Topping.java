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

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isExtra() {
        return extra;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
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

