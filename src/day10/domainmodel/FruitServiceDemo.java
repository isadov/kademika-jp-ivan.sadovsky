package day10.domainmodel;

import day10.domainmodel.Fruits.Apple;
import day10.domainmodel.Fruits.Fruit;
import day10.domainmodel.Fruits.Pear;

import java.util.ArrayList;
import java.util.List;

public class FruitServiceDemo {

    public static void main(String[] args) {
        List <Apple> apples = getApples();
        List <Pear> pears = getPears();

        List<Fruit> fruits = new ArrayList<>();

        FruitService.copyFruit(apples, fruits);
        FruitService.copyFruit(pears, fruits);

        //FruitService.copyFruit(apples, pears) // will not compile !!


        for (Apple a : apples) {
            System.out.println(a.getId());
        }

        System.out.println();

        for (Pear p : pears) {
            System.out.println(p.getId());
        }

    }

    public static List<Apple> getApples() {
        List<Apple> apples = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            apples.add(new Apple());
        }
        return apples;
    }

    public static List<Pear> getPears() {
        List<Pear> pears = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            pears.add(new Pear());
        }
        return pears;
    }
}
