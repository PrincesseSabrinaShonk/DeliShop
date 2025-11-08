package com.pluralsight;

public class Topping {
    private String name;
    private String type; // meat, cheese, regular, sauce
    private boolean extra; // true if the customer added extra amount of this topping

    // Constructor
    public Topping(String name, String type, boolean extra) {
        this.name = name;
        this.type = type;
        this.extra = extra;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type= type;
    }

    public boolean isExtra() {
        return extra;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return String.format("%s (%s%s)",
                name,
                type,
                extra ? ", extra" : ""
        );
    }
}

