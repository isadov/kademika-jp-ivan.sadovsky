package day10_hw.smart_copy_service;

import java.util.ArrayList;
import java.util.List;

public class SmartCopyLauncher {

    public static void main(String[] args) {

        Box<Products> box = new Box<>();

        Fruit fruit1 = new Fruit();
        fruit1.setName("Apple");

        Fruit fruit2 = new Fruit();
        fruit2.setName("Pear");

        Fruit fruit3 = new Fruit();
        fruit3.setName("Mandarin");


        Vegetable vegetable1 = new Vegetable();
        vegetable1.setName("Kartoshka");

        Vegetable vegetable2 = new Vegetable();
        vegetable2.setName("Buryak");

        Vegetable vegetable3 = new Vegetable();
        vegetable3.setName("Luk");

        List<Fruit> fruitList = new ArrayList<>();
        List<Vegetable> vegetableList = new ArrayList<>();

        fruitList.add(fruit1);
        fruitList.add(fruit2);
        fruitList.add(fruit3);

        vegetableList.add(vegetable1);
        vegetableList.add(vegetable2);
        vegetableList.add(vegetable3);

        box.nestInBox(fruit1);
        box.nestInBox(fruit2);
        box.nestInBox(fruit3);

        box.nestInBox(vegetable1);
        box.nestInBox(vegetable2);
        box.nestInBox(vegetable3);

        for (Products products : box.getNestedProductsList()) {
            System.out.println("TypeOfProduct: " + products.getType() + " --- NameOfProduct: " + products.getName());

        }

        List<Products> productsList = new ArrayList<>();

        SmartCopy.copy(fruitList, productsList);
        SmartCopy.copy(vegetableList, productsList);

        for(Products products : productsList) {
            System.out.println("TypeOfProduct: " + products.getType() + " --- NameOfProduct: " + products.getName());
        }
    }
}
