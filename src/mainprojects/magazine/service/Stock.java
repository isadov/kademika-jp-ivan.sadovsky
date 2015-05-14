package mainprojects.magazine.service;

import mainprojects.magazine.products.Goods;
import mainprojects.magazine.products.Product;

import java.util.*;

public class Stock<T extends Goods> {

    private Map<Class, Map<String, List<Goods>>> storage;
    private List<LinkedList<Goods>> arrayOfList;


    public Stock() {
        arrayOfList = new ArrayList<>(); //arrayOfList = new ArrayList<LinkedList<>>();
        storage = new HashMap<>();

    }

    public void addMap(T g) {
        Class aClass = g.getClass();
        String gName = g.getName();

        if (storage.isEmpty()) {
            storage.put(aClass, new HashMap<String, List<Goods>>());
            storage.get(aClass).put(gName, new LinkedList<Goods>());
            storage.get(aClass).get(gName).add(g);
        } else {
            if (storage.containsKey(aClass)) {
                if (!storage.get(aClass).containsKey(gName)) {
                    storage.get(aClass).put(gName, new LinkedList<Goods>());
                }
                storage.get(aClass).get(gName).add(g);
            } else {
                storage.put(aClass, new HashMap<String, List<Goods>>());
                storage.get(aClass).put(gName, new LinkedList<Goods>());
                storage.get(aClass).get(gName).add(g);
            }
        }
    }

    public List<Goods> getListOfGoods() {
        List<Goods> list = new ArrayList<>();

        for (Map.Entry<Class, Map<String, List<Goods>>> mapEntry : storage.entrySet()) {
            Map<String, List<Goods>> mapOfGoodsTypes = mapEntry.getValue(); // Map<String, List<Goods>>;
            for (Map.Entry<String, List<Goods>> listEntry : mapOfGoodsTypes.entrySet()) {
                list.add(listEntry.getValue().get(0)); // List<Goods>, get(0) -- > Goods;
            }
        }
        return list;
    }

//    public void add(T g) {
//        if (arrayOfList.isEmpty()) {
//            arrayOfList.add(new LinkedList<>());
//            arrayOfList.get(0).push(g); //esli massiv pust lozim na mesto pervoho elementa nash tovar predvaritel'no sozdav novui LinkedList
//        } else {
//            for (int i = 0; i < arrayOfList.size(); i++) {
//                if (arrayOfList.get(i).peek().equals(g)) {
//                    arrayOfList.get(i).push(g);
//                    return;
//                }
//            }
//
//            arrayOfList.add(new LinkedList<>()); // pozvoliaet dobavit' ne tolko poslednii product no i vse do neho
//            arrayOfList.get(arrayOfList.size() - 1).push(g);
//
//            // eti dve zapisi reguliruut nashe dobavlenie productov pytem polychenia razmera massiva i sozdania novoho LinkedList
//        }
//    }

    public int getNumberTypesOnStockMap() {
        int num = 0;
        for (Map.Entry<Class, Map<String, List<Goods>>> entryStorage : storage.entrySet()) {
            Map<String, List<Goods>> mapOfGoodsTypes = entryStorage.getValue();
            num += mapOfGoodsTypes.size();
        }
        return num;
    }

//    public int getNumberTypesOnStock() {
//        return arrayOfList.size();
//    }

    public String getNameMap(Class aClass, String goodsName) {
        String result = null;
        Goods g = storage.get(aClass).get(goodsName).get(0);

        if (g instanceof Product) {
            result = ((Product) g).getName();
        }
        return result;
    }

//    public String getName(int index) {
//        return arrayOfList.get(index).get(0).getName();
//    }

    public String getAttributeMap(Class aClass, String goodsBrand) {
        String result = null;
        Goods g = storage.get(aClass).get(goodsBrand).get(0);

        if (g instanceof Product) {
            result = ((Product) g).getBrand().toString();
        }
        return result;

    }

//    public String getAttribute(int index) {
//        String result = null;
//        if (arrayOfList.get(index).get(0) instanceof Product) {
//            result = ((Product) arrayOfList.get(index).get(0)).getBrand().toString();
//        }
//
//        return result;
//    }

    public int qetQuantityMap(Class aClass, String goodsName) {
        return storage.get(aClass).get(goodsName).size();
    }

    public String qetStringQuantityMap(Class aClass, String goodsName) {
        return Integer.toString(storage.get(aClass).get(goodsName).size());
    }

//    public int getQuantity(int index) {
//        return arrayOfList.get(index).size();
//    }

    public String getPriceMap(Class aClass, String goodsPrice) {
        Goods g = storage.get(aClass).get(goodsPrice).get(0);
        return String.valueOf(g.getPrice());
    }

//    public int getPrice(int index) {
//        return (int) arrayOfList.get(index).get(0).getPrice();
//    }

    public void addMoreThanOne(T p, int quantity) {
        for (int i = 0; i < quantity; i++) {
            addMap(p);
        }
    }

    public boolean removeMap(Class aClass, String goodsName, int quantity) {
        if (quantity <= storage.get(aClass).get(goodsName).size()) {
            for (int i = 0; i < quantity; i++) {
                storage.get(aClass).get(goodsName).remove(storage.get(aClass).get(goodsName).size() - 1);
            }
            if (storage.get(aClass).get(goodsName).isEmpty()) {
                storage.get(aClass).remove(goodsName);
            }
            return true;
        } else {
            return false;
        }
    }

//    public boolean remove(int index, int quantity) {
//        if (quantity <= arrayOfList.get(index).size()) {
//            for (int i = 0; i < quantity; i++) {
//                arrayOfList.get(index).pop();
//            }
//            if (arrayOfList.get(index).isEmpty()) {
//                arrayOfList.remove(index);
//            }
//            return true;
//        } else {
//            return false;
//        }
//
//    }

    public boolean isEmpty() {
        return storage.isEmpty();
    }

}
