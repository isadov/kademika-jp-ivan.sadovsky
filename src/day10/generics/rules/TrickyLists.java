package day10.generics.rules;

import java.util.ArrayList;
import java.util.List;

public class TrickyLists {

    public static void main(String[] args) {
        List <Integer> data = new ArrayList (); // always did <> cause we have indexOutOfBoundsException  !!
        List <String> strList = new ArrayList<>();

        data.add(10);

        String s = strList.get(10);


    }
}
