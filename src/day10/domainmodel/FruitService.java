package day10.domainmodel;

import day10.domainmodel.Fruits.Fruit;

import java.util.List;

public class FruitService {

    public static void copyFruit(List<? extends Fruit> src, List<? super Fruit> dst) {
        for (Fruit f : src) {
            dst.add(f);
        }
    }
}

