package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Item{

    public String getNoise() {
        return "Chew Chew, Yum!";
    }

    public Gum(String itemName, double itemPrice, int quantity){
        super(itemName, itemPrice, quantity);
    }


}
