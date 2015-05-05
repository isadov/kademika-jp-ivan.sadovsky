package mainprojects.magazine.service;

import mainprojects.magazine.customer.Customer;
import mainprojects.magazine.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Shop {

	private List <Transaction> register;
	Customer customer;
	Date dateRef;
	Stock stock;

	public Shop(Date dateRef, Stock stock) {
		this.stock = stock;
		this.dateRef = dateRef;
		register = new ArrayList<>();
	}
		
	public void buy(Customer customer, int indexOfGoods, int quantityToBuy) {
		String goodsName = stock.getName(indexOfGoods);
		int price = stock.getPrice(indexOfGoods);

		if (stock.remove(indexOfGoods, quantityToBuy)) {
			register.add(new Transaction(dateRef.currentData, customer
					.getName(), goodsName, price, quantityToBuy));
			System.out.println(register.get(register.size() - 1).toString());
			System.out.println();
		} else {
			System.err.println("Not enough goods on storage");
		}
		transactionInfo();
	}

	public List <Transaction> transactionInfo() {
		int i = 0;
		for (Transaction temp : register) {
			i++;
			System.out.println("Transaction number #" + i);
			System.out.println("Last Client: " + temp.getClient());
			System.out.println("What bought ?: " + temp.getElementName());
			System.out.println("Time Of Transaction: " + temp.getCurrentDate());
			System.out.println("Quantity Of Product: " + temp.getQuantity()
					+ " item(s)");
			System.out.println("Price per 1 unit: " + temp.getPrice() + " $");
			System.out.println("Total Quantity: " + temp.getQuantity()
					* temp.getPrice() + " $");
			System.out.println();
		}
		return register;
	}

	public void setCurrentData(String currentData) {
		this.dateRef.currentData = currentData;
	}

}
