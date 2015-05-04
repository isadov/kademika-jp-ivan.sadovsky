package mainprojects.store_hashmap_structure.purchase;

import mainprojects.store_hashmap_structure.customer.Customer;
import mainprojects.store_hashmap_structure.guitar.Guitar;

import java.io.Serializable;
import java.util.Calendar;

public class Purchase implements Serializable{

	private Customer customer;
	private Guitar guitar;
	private int number;
	private Calendar date;
	private double commonPrice;
	private double discount;

	public Purchase() {
		// TODO Auto-generated constructor stub
	}	

	public Customer getCustomer() {
		return customer;
	}

	public Guitar getGuitar() {
		return guitar;
	}

	public int getNumber() {
		return number;
	}

	public Calendar getDate() {
		return date;
	}

	public double getCommonPrice() {
		return commonPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setGuitar(Guitar guitar) {
		this.guitar = guitar;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public void setCommonPrice(double commonPrice) {
		this.commonPrice = commonPrice;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

}
