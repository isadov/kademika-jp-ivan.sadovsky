package day10.domainmodel;

import day10.domainmodel.Fruits.Fruit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class FruitBox <Type extends Fruit> {

    private List<Type> fruits;

    public FruitBox() {

        fruits = new ArrayList<Type>();
    }

    public void addFruits(Type fruit) {
        fruits.add(fruit);
    }

    public void removeFruits(Type fruit) {
        fruits.remove(fruit);
    }

    public void sortBoxByFruitName() {
        fruits.sort(new Comparator<Type>() {
            @Override
            public int compare(Type fruit1, Type fruit2) {
                int result = fruit1.getName().compareToIgnoreCase(fruit2.getName());

                if (result > 0) {
                    return -1;
                } else if (result < 0) {
                    return 1;
                }

                return 0;
            }
        });
    }

    public List<Type> getFruits() {
        return new ArrayList<Type>(fruits);
    }


}
