package mainprojects.magazine.service;

import mainprojects.magazine.products.Goods;
import mainprojects.magazine.products.Product;

import java.util.*;

public class Stock<T extends Goods> {

    private Map<Class, Map<String, List<Goods>>> mapStorage;


    public Stock() {

        mapStorage = new HashMap<>();

    }

    public void addMap(T g) {
        Class aClass = g.getClass();
        String goodsName = g.getName();

        if (mapStorage.isEmpty()) {
            mapStorage.put(aClass, new HashMap<String, List<Goods>>());
            mapStorage.get(aClass).put(goodsName, new LinkedList<Goods>());
            mapStorage.get(aClass).get(goodsName).add(g);
        } else {
            if (mapStorage.containsKey(aClass)) {
                if (!mapStorage.get(aClass).containsKey(goodsName)) {
                    mapStorage.get(aClass).put(goodsName, new LinkedList<Goods>());
                }
                mapStorage.get(aClass).get(goodsName).add(g);
            } else {
                mapStorage.put(aClass, new HashMap<String, List<Goods>>());
                mapStorage.get(aClass).put(goodsName, new LinkedList<Goods>());
                mapStorage.get(aClass).get(goodsName).add(g);
            }
        }
    }

    public List<Goods> getListOfGoods() {
        List<Goods> list = new ArrayList<>();

        for (Map.Entry<Class, Map<String, List<Goods>>> mapEntry : mapStorage.entrySet()) {
            Map<String, List<Goods>> mapOfGoodsTypes = mapEntry.getValue(); // Map<String, List<Goods>>;
            for (Map.Entry<String, List<Goods>> listEntry : mapOfGoodsTypes.entrySet()) {
                list.add(listEntry.getValue().get(0)); // List<Goods>, get(0) -- > Goods;
            }
        }
        return list;
    }

    public int getNumberTypesOnStockMap() {
        int num = 0;
        for (Map.Entry<Class, Map<String, List<Goods>>> entryStorage : mapStorage.entrySet()) {
            Map<String, List<Goods>> mapOfGoodsTypes = entryStorage.getValue();
            num += mapOfGoodsTypes.size();
        }
        return num;
    }

    public String getNameMap(Class aClass, String goodsName) {
        String result = null;
        Goods g = mapStorage.get(aClass).get(goodsName).get(0);

        if (g instanceof Product) {
            result = ((Product) g).getName();
        }
        return result;
    }

    public String getAttributeMap(Class aClass, String goodsBrand) {
        String result = null;
        Goods g = mapStorage.get(aClass).get(goodsBrand).get(0);

        if (g instanceof Product) {
            result = ((Product) g).getBrand().toString();
        }
        return result;

    }

    public int qetQuantityMap(Class aClass, String goodsName) {
        return mapStorage.get(aClass).get(goodsName).size();
    }

    public String qetStringQuantityMap(Class aClass, String goodsName) {
        return Integer.toString(mapStorage.get(aClass).get(goodsName).size());
    }

    public String getPriceMap(Class aClass, String goodsPrice) {
        Goods g = mapStorage.get(aClass).get(goodsPrice).get(0);
        return String.valueOf(g.getPrice());
    }

    public void addMoreThanOne(T p, int quantity) {
        for (int i = 0; i < quantity; i++) {
            addMap(p);
        }
    }

    public boolean removeMap(Class aClass, String goodsName, int quantity) {
        if (quantity <= mapStorage.get(aClass).get(goodsName).size()) {
            for (int i = 0; i < quantity; i++) {
                mapStorage.get(aClass).get(goodsName).remove(mapStorage.get(aClass).get(goodsName).size() - 1);
            }
            if (mapStorage.get(aClass).get(goodsName).isEmpty()) {
                mapStorage.get(aClass).remove(goodsName);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {
        return mapStorage.isEmpty();
    }

}
