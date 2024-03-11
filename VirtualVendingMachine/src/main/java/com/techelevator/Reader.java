package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class Reader {

    File file = new File("alternate.csv");
    Reader reader = null;
    Scanner fileScanner;

    {
        try {
            fileScanner = new Scanner(file);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Store every line of csv in list as String value
    public List<String> itemsLineList() {
        List<String> itemLineList = new ArrayList<>();
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            itemLineList.add(line);
        }
        return itemLineList;
    }

// Fallen Soldier
//   public  List<String> DisplayList() {
//       List<String> DisplayList = new ArrayList<>();
//       for(String items : itemsLineList()){
//            String [] itemArray = items.split("\\,");
//           System.out.println(itemArray[1] +" " +itemArray[2]);
//       }
//       return DisplayList;
//   }

    // makes map of all items with the slot as the key string and the value as an object with
    // name price and quantity
    public HashMap<String, Item> makeItems() {
        HashMap<String, Item> itemObjects = new HashMap<>();
        for (String items : itemsLineList()) {
            String[] itemArray = items.split("\\,");
            if (itemArray[3].equals("Gum")) {
                Gum gum = new Gum(itemArray[1], Double.parseDouble(itemArray[2]), 5);
                itemObjects.put(itemArray[0], gum);
            } else if (itemArray[3].equals("Candy")) {
                Candy candy = new Candy(itemArray[1], Double.parseDouble(itemArray[2]), 5);
                itemObjects.put(itemArray[0], candy);
            } else if (itemArray[3].equals("Drink")) {
                Drinks drinks = new Drinks(itemArray[1], Double.parseDouble(itemArray[2]), 5);
                itemObjects.put(itemArray[0], drinks);
            } else if (itemArray[3].equals("Munchy")) {
                Munchie munchie = new Munchie(itemArray[1], Double.parseDouble(itemArray[2]), 5);
                itemObjects.put(itemArray[0], munchie);
            }
        }
        return itemObjects;
    }

}

