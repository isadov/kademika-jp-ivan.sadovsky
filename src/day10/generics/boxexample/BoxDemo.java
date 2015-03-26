package day10.generics.boxexample;

public class BoxDemo {

    public static void main(String[] args) {
        Box<Integer> box = new Box<>();

        box.setItem(10);

        System.out.println(box.getItem());

    }
}
