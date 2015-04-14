package day10_hw.smart_copy_service;

import java.util.ArrayList;
import java.util.List;

public class Box <Type extends Products> {

    List<Type> nestedProductsList;

    Box() {
        nestedProductsList = new ArrayList<>();
    }

    public void nestInBox(Type products) {
        nestedProductsList.add(products);
    }

    public List<Type> getNestedProductsList() {
        return nestedProductsList;
    }
}
