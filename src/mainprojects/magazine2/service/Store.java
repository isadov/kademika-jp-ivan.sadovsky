package mainprojects.magazine2.service;

import mainprojects.magazine2.customer.Customer;
import mainprojects.magazine2.products.Product;
import mainprojects.magazine2.purchase.Purchase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Store {

    private DataBase db;

    public Store() {
        db = new DataBase();
    }

    public void newPurchase(Product p, int number, String customerName, Calendar date) throws RuntimeException {
        Purchase purchase = new Purchase();
        Customer customer;

        if (customerName == "") {
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

        if (number < 1 || number > this.getProductNumberByName(name, db.getProductTypeMap().get(p.getType().toString()))) {
            throw new IllegalStateException();
        }

        for (int idx = 0; idx < number; idx++) {
            db.removeProductFromDb(p);
        }

        db.getPurchase().add(purchase);
    }

    public double calculatePrice(Customer customer, double price) {

        double sum = 0.0;

        String customerName = customer.getName();

        for (Purchase p : this.getDb().getPurchase()) {
            if (customerName.equals(p.getCustomer().getName())) {
                sum += (p.getProduct().getPrice() * p.getNumber());
            }
        }

        sum += price;

        return sum;
    }

    public String printStore() {

        StringBuilder resultString = new StringBuilder("");

        for (List<Product> productType : this.getDb().getProductTypeMap().values()) {
            for (Product productTemp : productType) {
                resultString.append(productTemp.getClass().getSimpleName() + " ");
                resultString.append(productTemp.getCountryManufacturer() + " ");
                resultString.append(productTemp.getName() + " ");
                resultString.append(productTemp.getWeight() + " ");
                resultString.append(productTemp.getType() + " ");
                resultString.append(productTemp.isFresh() + " ");
                resultString.append(productTemp.getPrice() + " ");

            }

            resultString.append("\n");

        }

        return resultString.toString();

    }

    public String printPurchases(Calendar date) {

        int sum = 0;
        int number = 0;
        StringBuilder resultString = new StringBuilder("");

        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("EEEE dd.MM.YYYY k:mm");

        for(Purchase value : db.getPurchase()) {
            if(value != null) {
                if(date != null) {
                    if(!(value.getCalendar().getTime().toString().equals(date.getTime().toString()))) {
                        continue;
                    }
                }

                number ++;
                sum += (value.getNumber() * value.getProduct().getPrice());
                resultString.append("Purchase ");
                resultString.append(number + " ");
                resultString.append(value.getCustomer().getName() + " ");
                resultString.append(value.getProduct().getType() + " ");
                resultString.append(value.getProduct().getName() + " ");
                resultString.append(value.getNumber() + " ");
                resultString.append((value.getNumber() * value.getProduct().getPrice()+ " "));
                resultString.append(formatter.format(value.getCalendar().getTime()) + "\n");

            }
        }

        resultString.append("\nAll " + number + " purchases, all wasted money - " + sum + "\n");

        return resultString.toString();

    }

    public String printNumberOfPurchasesByWeek() {

        String result = "";
        Calendar today = new GregorianCalendar();
        int[] numberOfPurchasesByWeek = new int [7];

        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("EEEE dd.MM.YYYY");

        for(int i = 0; i < numberOfPurchasesByWeek.length; i++) {
            for(Purchase value : db.getPurchase()) {
                {
                    String valueDate = formatter.format(value.getCalendar().getTime());
                    String currentDate = formatter.format(today.getTime());

                    if(valueDate.equals(currentDate)) {
                        numberOfPurchasesByWeek[i] ++;
                    }
                }
            }

            today.add(Calendar.DATE, -1);
        }

        for(int i = 0; i < numberOfPurchasesByWeek.length; i++) {
            if(i == 0) {
                result += numberOfPurchasesByWeek[i] + " purchases TODAY\n";
            } else if (i == 1) {
                result += numberOfPurchasesByWeek[i] + " purchases YESTERDAY\n";
            } else
                result += numberOfPurchasesByWeek[i] + " purchases were made " + i + " days ago\n";
        }

        return result;
    }

    int getProductNumberByName(String name, List<Product> products) {
        int quantity = 0;
        name = name.toUpperCase();

        for (Product p : products) {
            if (p.getName().toUpperCase().equals(name)) {
                quantity++;
            }
        }
        return quantity;
    }

    public DataBase getDb() {
        return db;
    }
}
