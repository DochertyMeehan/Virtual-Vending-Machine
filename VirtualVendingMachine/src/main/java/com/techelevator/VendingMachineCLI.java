package com.techelevator;
import java.text.DecimalFormat;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


// This class allows user to interact with the vending machine

public class VendingMachineCLI {

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}

	private static final DecimalFormat df = new DecimalFormat("0.00");
	private boolean BOGODO = false;
	private double currentMoneyProvided = 0.00;
	private Reader reader = new Reader();
	private Logger logger = new Logger();


	// program starts
	public void run() {
		System.out.println("Welcome to Vendo-Matic 800");

		boolean stay = true;
		Scanner mainmenuScanner = new Scanner(System.in);
		HashMap<String, Item> data = reader.makeItems();

		// Main menu
		while (stay) {
			stay2 = true;
			System.out.println("********");
			System.out.println("(1) Display Vending Machine Items ");
			System.out.println("(2) Purchase");
			System.out.println("(3) Exit");
			System.out.println("********");
			String selection = mainmenuScanner.nextLine();

			if (selection.equals("1")) {
				displayMenu(data);
			} else if (selection.equals("2")) {
				buildProcessMenu(data);
			} else if (selection.equals("3")) {
				stay = false;
			} else {
			}
		}
		System.out.println("Thank you come again!");
	}

	// passes in map, loops through all keys | displays item, name, price, and quantity left for each
// uses printf to format nicely
// https://www.baeldung.com/java-printstream-printf
	public void displayMenu(HashMap<String, Item> data) {
		for (String key : data.keySet()) {
			Item value = data.get(key);
			if (value.getQuantity() <= 0) {
				System.out.printf(" %-7s %-18s %-10s %-10s %n", key, value.getItemName(), value.getItemPrice(), " SOLD OUT");
			} else {
				System.out.printf(" %-7s %-18s %-10s %-10s %n", key, value.getItemName(), value.getItemPrice(), value.getQuantity());
			}

		}
	}

	// purchasing process menu
	boolean stay2 = true;

	public void buildProcessMenu(HashMap<String, Item> data) {
		while (stay2) {

			Scanner submenu = new Scanner(System.in);
			System.out.println("********");
			System.out.printf("%s%.2f%n", "Current Money Provided: $", currentMoneyProvided);

			System.out.println("(1) Feed Money ");
			System.out.println("(2) Select Product");
			System.out.println("(3) Finish Transaction");
			System.out.println("********");
			String selection2 = submenu.nextLine();

			if (selection2.equals("1")) {
				FeedMoney();
			} else if (selection2.equals("2")) {
				PurchaseScreen(data);
			} else if (selection2.equals("3")) {
				getChange(currentMoneyProvided);
				currentMoneyProvided = 0.00;
				stay2 = false;
			} else {

			}
		}
	}


	// Allows user to feed money into vending machine as whole bills updates current money provided
	public void FeedMoney() {
		Scanner moneyscanner = new Scanner(System.in);
		System.out.println("Enter how many dollars you want to deposit");
		double moneyasDouble = 0;
		String money = null;

		// try to get user input in valid dollar amounts
		try {
			money = moneyscanner.nextLine();
			// if user input is not valid dollar amount, throw exception
			if (!money.equals("1") && !money.equals("5") && !money.equals("10") && !money.equals("20") && !money.equals("50") && !money.equals("100")) {
				throw new Exception();
			}
			// if user input is valid, parse to double and assign to variable moneyasDouble
			moneyasDouble = Double.parseDouble(money);
		} catch (Exception e) {
			System.out.println("Enter valid whole dollar amount");
		}
		currentMoneyProvided += moneyasDouble;
		logger.write("FEED MONEY $" + money + ".00 $" + df.format(currentMoneyProvided) + "\n");
	}


	// Method for purchasing an item
	public void PurchaseScreen(HashMap<String, Item> data) {
		Scanner purchaseMenuScanner = new Scanner(System.in);

		boolean stay3 = true;

		// loop through Map data, set value to corresponding key
		while (stay3) {
			for (String key : data.keySet()) {
				Item value = data.get(key);
				System.out.printf("%-7s %-18s %-10s %n", key, value.getItemName(), value.getItemPrice());
			}

			System.out.println("Enter slot of item you would like to purchase: ");
			String itemSlot = purchaseMenuScanner.nextLine();

			// checks if users selection is a key in data, if not returns null
			Item selection = data.get(itemSlot);

			// check for valid slot, if theres enough of the item left, and if the player has enough cash
			if (selection == null) {
				System.out.println("Invalid Selection");
			} else if (selection.getQuantity() <= 0) {
				System.out.println("SOLD OUT");
			} else if (selection.getItemPrice() > currentMoneyProvided) {
				System.out.println("Not enough money, enter more cash");
				stay3 = false;
			} else {

				//purchase happens
				int quantitynow = selection.getQuantity();
				int updatedquantity = quantitynow - 1;
				selection.setQuantity(updatedquantity);
				double price = selection.getItemPrice();
				if (BOGODO) {
					currentMoneyProvided = currentMoneyProvided - (price - 1);
					BOGODO = false;
				} else {
					currentMoneyProvided = currentMoneyProvided - price;
					BOGODO = true;
				}
				stay3 = false;
				printNoise(selection);
				printPurchase(selection);
				logger.write(selection.getItemName() + " " + itemSlot + " $" + selection.getItemPrice() + " $" + df.format(currentMoneyProvided) + "\n");
			}
		}
	}

	// method for giving change back to user
	public void getChange(double currentMoneyProvided) {
		double printStartMoney = currentMoneyProvided;
		int quarters = 0, nickles = 0, dimes = 0, pennies = 0;
		while (currentMoneyProvided > 0) {
			if (currentMoneyProvided >= .25) {
				currentMoneyProvided -= .25;
				quarters++;
			} else if (currentMoneyProvided >= .10) {
				currentMoneyProvided -= .10;
				dimes++;
			} else if (currentMoneyProvided >= .5) {
				currentMoneyProvided -= .5;
				nickles++;
			} else {
				currentMoneyProvided -= .1;
				pennies++;
			}
		}

		System.out.println("Your change is: " + quarters + " Quarters " + dimes + " Dimes " + nickles + " Nickles " + pennies + " Pennies");
		logger.write("GIVE CHANGE: $" + printStartMoney + " $" + "0.00");
	}


	// prints summary of what customer selected
	public void printPurchase(Item selection) {
		System.out.printf("%s%s%s%s%s%s%n", "Item: ", selection.getItemName(), " Price: $", selection.getItemPrice(), " Current Balance: $", df.format(currentMoneyProvided));
	}

	// prints noise for corresponding item
	public void printNoise(Item selection) {
		System.out.println(selection.getNoise());
	}
}

