package day10_hw.smart_copy_service;

import java.util.List;

public class SmartCopy {

    public static void copy(List<? extends Products> src, List<? super Products> dst) {
        for (Products products : src) {
            dst.add(products);
        }
    }
}
