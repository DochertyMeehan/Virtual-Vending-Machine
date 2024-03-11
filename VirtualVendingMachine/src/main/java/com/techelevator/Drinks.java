package com.techelevator;

import java.math.BigDecimal;

public class Drinks extends Item{


    public Drinks(String itemName, double itemPrice, int quantity) {
        super(itemName, itemPrice, quantity);
    }

    @Override
    public String getNoise() {
        return "Glug Glug, Yum!";
    }




}
