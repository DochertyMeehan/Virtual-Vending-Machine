package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Item{

    @Override
    public String getNoise() {
        return "Yummy Yummy, So Sweet!";
    }

    public Candy(String name, double price, int quantity){
        super(name, price, quantity);
    }


}
