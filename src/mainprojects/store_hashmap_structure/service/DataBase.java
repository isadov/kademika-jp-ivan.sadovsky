package mainprojects.store_hashmap_structure.service;

import mainprojects.store_hashmap_structure.guitar.Guitar;
import mainprojects.store_hashmap_structure.guitar.GuitarBrand;
import mainprojects.store_hashmap_structure.guitar.GuitarType;
import mainprojects.store_hashmap_structure.purchase.Purchase;

import java.util.*;

public class DataBase {

    private Map<String, ArrayList<Guitar>> guitarTypeMap;
    private Map<String, ArrayList<Guitar>> guitarBrandMap;
    private List<Purchase> purchase;
    private Comparator<Guitar> guitarComparator;

    public DataBase() {

        guitarTypeMap = new GuitarTypeMap();
        guitarBrandMap = new GuitarBrandMap();
        purchase = new ArrayList<>();
        guitarComparator = new Comparator<Guitar>() {

            public int compare(Guitar guitar1, Guitar guitar2) {
                int result = guitar1.getModel().compareToIgnoreCase(guitar2.getModel());
                if(result > 0){
                    return 1;
                } else if (result == 0){
                    return 0;
                }else{
                    return -1;
                }
            }
        };
    }

    class GuitarTypeMap extends HashMap<String, ArrayList<Guitar>>{
        public GuitarTypeMap() {
            put(GuitarType.ACOUSTIC.toString(), new ArrayList<>());
            put(GuitarType.ELECTRIC.toString(), new ArrayList<>());
            put(GuitarType.BASS.toString(), new ArrayList<>());
        }
    }

    class GuitarBrandMap extends HashMap<String, ArrayList<Guitar>>{
        public GuitarBrandMap() {
            put(GuitarBrand.ESP.toString(), new ArrayList<>());
            put(GuitarBrand.FENDER.toString(), new ArrayList<>());
            put(GuitarBrand.GIBSON.toString(), new ArrayList<>());
            put(GuitarBrand.IBANEZ.toString(), new ArrayList<>());
            put(GuitarBrand.JACKSON.toString(), new ArrayList<>());
        }
    }

    public void addGuitarToDB(Guitar g) {
        guitarTypeMap.get(g.getGuitarType().toString()).add(g);
        guitarBrandMap.get(g.getGuitarBrand().toString()).add(g);
        guitarTypeMap.get(g.getGuitarType().toString()).sort(guitarComparator);
        guitarBrandMap.get(g.getGuitarBrand().toString()).sort(guitarComparator);
    }

    public void removeGuitarFromDB(Guitar g) {
        guitarTypeMap.get(g.getGuitarType().toString()).remove(g);
        guitarBrandMap.get(g.getGuitarBrand().toString()).remove(g);
        guitarTypeMap.get(g.getGuitarType().toString()).sort(guitarComparator);
        guitarBrandMap.get(g.getGuitarBrand().toString()).sort(guitarComparator);
    }

    public int getNumberOfAcousticGuitar() {
        return guitarTypeMap.get(GuitarType.ACOUSTIC.toString()).size();
    }

    public int getNumberOfElectricGuitar() {
        return guitarTypeMap.get(GuitarType.ELECTRIC.toString()).size();
    }

    public int getNumberOfBassGuitar() {
        return guitarTypeMap.get(GuitarType.BASS.toString()).size();
    }

    public List<Purchase> getPurchase() {
        return purchase;
    }

    public Map<String, ArrayList<Guitar>> getGuitarBrandMap() {
        return guitarBrandMap;
    }

    public Map<String, ArrayList<Guitar>> getGuitarTypeMap() {
        return guitarTypeMap;
    }

}