package day10.generics.method;

import java.util.Arrays;
import java.util.List;

public class GenericsMethod {

    public static void main(String[] args) {

        List <String> strings = Arrays.asList(new String [] {"one", "two", "three"});
        print(strings);

        List <Integer> integers = Arrays.asList(new Integer[] {1, 2, 3});
        print(integers);


    }

    public static <E> void print(List<E> elements) {
        for (E e : elements) {
            System.out.println(e.toString());
        }

    }
}
