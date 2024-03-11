package com.techelevator;

import java.math.BigDecimal;

public class Munchie extends Item{

    public Munchie(String itemName, double itemPrice, int quantity) {
        super(itemName, itemPrice, quantity);
    }

    @Override
    public String getNoise() {
        return "Crunch Crunch, Yum!";
    }


}
