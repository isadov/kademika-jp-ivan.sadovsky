package day10.reflection.example;

public class ClassTester {

    public static void main(String[] args) {
        Duck duck = new Duck();
        System.out.println(duck.getClass().getName());
        System.out.println(duck.getClass().getSimpleName());
    }
}
