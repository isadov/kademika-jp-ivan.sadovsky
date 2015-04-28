package mainprojects.magazine2.service;



import mainprojects.magazine2.customer.Customer;
import mainprojects.magazine2.products.Product;
import mainprojects.magazine2.purchase.Purchase;

import java.util.Calendar;
import java.util.List;

public class Store {

    private DataBase db;

    public Store() {
        db = new DataBase();
    }

    public void newPurchase(Product p, int number, String customerName, Calendar date) throws RuntimeException{
        Purchase purchase = new Purchase();
        Customer customer;

        if(customerName == "") {
            customer = new Customer();
        } else {
            customer = new Customer(customerName);
        }

        purchase.setCustomer(customer);
        purchase.setNumber(number);
        purchase.setCalendar(date);
        purchase.setProduct(p);

        double price = p.getPrice() * number;


        executePurchase(purchase, p.getName(), number);
    }

    private void executePurchase(Purchase purchase, String name, int number) throws IllegalStateException {
        Product p = purchase.getProduct();

        if(number < 1 || number > this.getProductNumberByName(name, db.getProductTypeMap().get(p.getType().toString()))) {
            throw new IllegalStateException();
        }

        for (int idx = 0; idx < number; idx++) {
            db.removeProductFromDb(p);
        }

        db.getPurchase().add(purchase);
    }



    int getProductNumberByName(String name, List<Product> products) {
        int quantity = 0;
        name = name.toUpperCase();

        for(Product p : products) {
            if(p.getName().toUpperCase().equals(name)) {
                quantity ++;
            }
        }
        return quantity;
    }

    private DataBase getDb() {
        return db;
    }
}
