package day10.domainmodel;

import day10.domainmodel.Fruits.Apple;
import day10.domainmodel.Fruits.Fruit;
import day10.domainmodel.Fruits.Pear;


public class FruitBoxDemo {

    public static void main(String[] args) {

        FruitBox<Fruit> fruitsBox = new FruitBox<>();
        FruitBox<Apple> appleFruitBox = new FruitBox<>();
        FruitBox<Pear> pearFruitBox = new FruitBox<>();

        Fruit apple = new Apple();
        apple.setName("Leven");
        apple.setId(1);
        apple.setPrice(123);
        fruitsBox.addFruits(apple);

        Fruit pear = new Pear();
        pear.setName("Leden");
        pear.setId(2);
        pear.setPrice(1234);
        fruitsBox.addFruits(pear);

        Fruit pear1 = new Pear();
        pear1.setName("You");
        pear1.setId(3);
        pear.setPrice(12312);
        fruitsBox.addFruits(pear1);

        fruitsBox.sortBoxByFruitName();

        for (Fruit f : fruitsBox.getFruits()) {
            System.out.println(f.getName());
        }

        fruitsBox.removeFruits(pear1);

        fruitsBox.sortBoxByFruitName();

        System.out.println();
        for (Fruit f : fruitsBox.getFruits()) {
            System.out.println(f.getName());
        }

    }
}
