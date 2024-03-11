package com.techelevator;

import java.math.BigDecimal;

public abstract class Item{

    private String itemName;
    private double itemPrice;

    private int quantity;

    public abstract String getNoise();

    public Item(String itemName, double itemPrice, int quantity){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
