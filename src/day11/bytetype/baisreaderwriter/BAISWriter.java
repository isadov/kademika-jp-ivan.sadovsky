package day11.bytetype.baisreaderwriter;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class BAISWriter {

    public static void main(String[] args) {
        byte[] bytes = {63, 64, 65, 66, 67, -27, -128, 0};

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        for (int i = 0; i < bytes.length; i++) {
            out.write(bytes[i]);
        }
        System.out.println(Arrays.toString(out.toByteArray()));
    }
}
