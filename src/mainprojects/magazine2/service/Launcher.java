package mainprojects.magazine2.service;

import mainprojects.magazine2.products.CountryManufacturer;
import mainprojects.magazine2.products.Product;
import mainprojects.magazine2.products.ProductType;

import java.io.IOException;
import java.util.GregorianCalendar;

public class Launcher {

    public static void main(String[] args) throws InterruptedException, IOException {

        Store store = new Store();

        loadDB(store.getDb());

        store.newPurchase(new Product("Apple", ProductType.FRUIT, 3, 10, true, CountryManufacturer.UKRAINE), 10, "Ivan", new GregorianCalendar()); // Product p, int number, String customerName, Calendar date
                                            // String name, ProductType type, int weight, double price, boolean isFresh, CountryManufacturer countryManufacturer

        new StoreGUI(store);
    }

    public static void loadDB(DataBase db) {
        Product p = new Product("Apple", ProductType.FRUIT, 3, 5, true, CountryManufacturer.UKRAINE);

        db.addProductToDb(p);

        Product p1 = new Product();
        p1.setName("Pear");
        p1.setCountryManufacturer(CountryManufacturer.BRAZIL);
        p1.setIsFresh(true);
        p1.setPrice(12);
        p1.setWeight(2);
        p1.setType(ProductType.FRUIT);

        db.addProductToDb(p1);

        Product p2 = new Product();
        p2.setName("Mandarin");
        p2.setCountryManufacturer(CountryManufacturer.CANADA);
        p2.setIsFresh(true);
        p2.setPrice(9);
        p2.setWeight(1);
        p2.setType(ProductType.FRUIT);

        db.addProductToDb(p2);

        Product p3 = new Product();
        p3.setName("Orange");
        p3.setCountryManufacturer(CountryManufacturer.FRANCE);
        p3.setIsFresh(true);
        p3.setPrice(12);
        p3.setWeight(2);
        p3.setType(ProductType.FRUIT);

        db.addProductToDb(p3);

        Product p4 = new Product();
        p4.setName("Onion");
        p4.setCountryManufacturer(CountryManufacturer.UKRAINE);
        p4.setIsFresh(true);
        p4.setPrice(3);
        p4.setWeight(2);
        p4.setType(ProductType.VEGETABLES);

        db.addProductToDb(p4);

    }
}
