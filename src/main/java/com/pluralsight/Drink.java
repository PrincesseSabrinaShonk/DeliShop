package com.pluralsight;

public class Drink extends  OrderItems {
    private String size;
    private String flavor;


    // Constructor
    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    public String getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }
    @Override
    public double getPrice() {
        switch (size.toLowerCase()) {
            case "small":
                return 2.00;
            case "medium":
                return 2.50;
            case "large":
                return 3.00;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s Drink - $%.2f",
                size.substring(0, 1).toUpperCase() + size.substring(1),
                flavor,
                getPrice());
    }
}


