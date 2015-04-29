package mainprojects.magazine2.service;

import mainprojects.magazine2.products.CountryManufacturer;

import mainprojects.magazine2.products.Product;
import mainprojects.magazine2.products.ProductType;
import mainprojects.magazine2.purchase.Purchase;

import java.util.*;

public class DataBase {

    private Map<String, ArrayList<Product>> productTypeMap;
    private Map<String, ArrayList<Product>> countryManufacturerMap;
    private List<Purchase> purchase;
    private Comparator<Product> productComparator;

    public DataBase() {

    }

    class ProductTypeMap extends HashMap<String, ArrayList<Product>> {
        public ProductTypeMap() {
            put(ProductType.FRUIT.toString(), new ArrayList<>());
            put(ProductType.VEGETABLES.toString(), new ArrayList<>());
            put(ProductType.OTHER.toString(), new ArrayList<>());

        }
    }

    class CountryManufacturerMap extends HashMap<String, ArrayList<Product>> {
        public CountryManufacturerMap() {
            put(CountryManufacturer.BRAZIL.toString(), new ArrayList<>());
            put(CountryManufacturer.CANADA.toString(), new ArrayList<>());
            put(CountryManufacturer.FRANCE.toString(), new ArrayList<>());
            put(CountryManufacturer.UKRAINE.toString(), new ArrayList<>());
        }
    }

    public void addProductToDb(Product p) {
        productTypeMap.get(p.getType().toString()).add(p);
        countryManufacturerMap.get(p.getCountryManufacturer().toString()).add(p);
        productTypeMap.get(p.getType().toString()).sort(productComparator);
        countryManufacturerMap.get(p.getCountryManufacturer().toString()).sort(productComparator);

    }

    public void removeProductFromDb(Product p) {
        productTypeMap.get(p.getType().toString()).remove(p);
        countryManufacturerMap.get(p.getCountryManufacturer().toString()).remove(p);
        productTypeMap.get(p.getType().toString()).sort(productComparator);
        countryManufacturerMap.get(p.getCountryManufacturer().toString()).sort(productComparator);

    }

    public int getNumberOfProductFruit() {
        return productTypeMap.get(ProductType.FRUIT.toString()).size();
    }

    public int getNumberOfProductVegetables() {
        return productTypeMap.get(ProductType.VEGETABLES.toString()).size();
    }

    public int getNumberOfProductOther() {
        return productTypeMap.get(ProductType.OTHER.toString()).size();
    }

    public List<Purchase> getPurchase() {
        return purchase;

    }

    public Map<String, ArrayList<Product>> getProductTypeMap() {
        return productTypeMap;
    }

    public Map<String, ArrayList<Product>> getCountryManufacturerMap() {
        return countryManufacturerMap;
    }
}


