package mainprojects.magazine.service.testing;

import mainprojects.magazine.products.Product;
import mainprojects.magazine.products.TypeOfProducts;
import mainprojects.magazine.transaction.Transaction;
import mainprojects.magazine2.purchase.Purchase;

import java.util.*;

public class DataBase {

    private Map<String, ArrayList<Product>> productTypeMap;
    private List<Transaction> transactions;
    private Comparator<Product> productComparator;
    private List<Purchase> purchaseList;

    public DataBase() {

        productTypeMap = new ProductTypeMap();
        purchaseList = new ArrayList<>();
        productComparator = new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                int result = product1.getName().compareToIgnoreCase(product2.getName());
                if (result > 0) {
                    return 1;
                } else if (result == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        };
    }


    class ProductTypeMap extends HashMap<String, ArrayList<Product>> {
        public ProductTypeMap() {
            put(TypeOfProducts.FRUIT.toString(), new ArrayList<>());
            put(TypeOfProducts.VEGETABLES.toString(), new ArrayList<>());
            put(TypeOfProducts.OTHER.toString(), new ArrayList<>());
        }
    }

    public void addProduct(Product p) {
        productTypeMap.get(p.getType().toString()).add(p);
        productTypeMap.get(p.getType().toString()).sort(productComparator);
    }

    public void removeProduct(Product p) {
        productTypeMap.get(p.getType().toString()).remove(p);
        productTypeMap.get(p.getType().toString()).sort(productComparator);
    }


    public Map<String, ArrayList<Product>> getProductTypeMap() {
        return productTypeMap;
    }

    public void setProductTypeMap(Map<String, ArrayList<Product>> productTypeMap) {
        this.productTypeMap = productTypeMap;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Comparator<Product> getProductComparator() {
        return productComparator;
    }

    public void setProductComparator(Comparator<Product> productComparator) {
        this.productComparator = productComparator;
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

}

