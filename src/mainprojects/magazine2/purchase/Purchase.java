package mainprojects.magazine2.purchase;

import mainprojects.magazine2.customer.Customer;
import mainprojects.magazine2.products.Product;

import java.util.Calendar;

public class Purchase {

    private Customer customer;
    private Product product;
    private int number;
    private Calendar calendar;
    private double price;

    public Purchase() {

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
