package mainprojects.magazine.service;

import mainprojects.magazine.products.Goods;
import mainprojects.magazine.products.Product;

import java.util.*;

public class Stock<T extends Goods> {

    private Product product;
    private List<LinkedList<Goods>> arrayOfList;
    private Map<String, Product> productNameMap;

//попробовать Map. Попробовать связать по ид. Поле ид в типах. 

    public Stock() {
        arrayOfList = new ArrayList<>(); //arrayOfList = new ArrayList<LinkedList<>>();
        productNameMap = new HashMap<>();

    }

    public void addMap(T p) {
        if (productNameMap.isEmpty()) {
            productNameMap.put(p.getName(), (Product) p);
        } else {
            productNameMap.putIfAbsent(p.getName(), (Product) p);
            return;
        }
    }


    public void add(T g) {
        if (arrayOfList.isEmpty()) {
            arrayOfList.add(new LinkedList<>());
            arrayOfList.get(0).push(g); //esli massiv pust lozim na mesto pervoho elementa nash tovar predvaritel'no sozdav novui LinkedList
        } else {
            for (int i = 0; i < arrayOfList.size(); i++) {
                if (arrayOfList.get(i).peek().equals(g)) {
                    arrayOfList.get(i).push(g);
                    return;
                }
            }

            arrayOfList.add(new LinkedList<>()); // pozvoliaet dobavit' ne tolko poslednii product no i vse do neho
            arrayOfList.get(arrayOfList.size() - 1).push(g);

            // eti dve zapisi reguliruut nashe dobavlenie productov pytem polychenia razmera massiva i sozdania novoho LinkedList
        }
    }

    public int getNumberTypesOnStockMap() {
        return productNameMap.size();
    }

    public int getNumberTypesOnStock() {
        return arrayOfList.size();
    }

    public String getNameMap(int index) {

    }

    public String getName(int index) {
        return arrayOfList.get(index).get(0).getName();
    }

    public String getAttributeMap(int index) {
        String result = null;
        if (productNameMap.get(index) instanceof Product) {
            result = productNameMap.get(index).getBrand().toString();
        }
        return result;
    }

    public String getAttribute(int index) {
        String result = null;
        if (arrayOfList.get(index).get(0) instanceof Product) {
            result = ((Product) arrayOfList.get(index).get(0)).getBrand().toString();
        }

        return result;
    }

    public int qetQuantityMap(int index) {
        return productNameMap.size();
    }

    public int getQuantity(int index) {
        return arrayOfList.get(index).size();
    }

    public int getPriceMap(int index) {
        return (int) productNameMap.get(index).getPrice();
    }

    public int getPrice(int index) {
        return (int) arrayOfList.get(index).get(0).getPrice();
    }

    public void addMoreThanOne(T p, int quantity) {
        for (int i = 0; i < quantity; i++) {
            addMap(p);
        }
    }

    public boolean removeMap(int index, int quantity) {
        if (quantity <= productNameMap.size()) {
            for (int i = 0; i < quantity; i++) {
                productNameMap.remove(index);
            }
            if (productNameMap.isEmpty()) {
                productNameMap.remove(index);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(int index, int quantity) {
        if (quantity <= arrayOfList.get(index).size()) {
            for (int i = 0; i < quantity; i++) {
                arrayOfList.get(index).pop();
            }
            if (arrayOfList.get(index).isEmpty()) {
                arrayOfList.remove(index);
            }
            return true;
        } else {
            return false;
        }

    }

}
