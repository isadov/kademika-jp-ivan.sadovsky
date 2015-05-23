package day11.bytetype.baisreaderwriter;

import java.io.ByteArrayInputStream;

public class BAISReader {

    public static void main(String[] args) {
        byte[] bytes = {63, 64, 65, 66, 67, -27, -128, 0}; //63 64 65 66 67 229 128 0 ??

        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        int i;
        while ((i = in.read()) != -1) {
            System.out.print(i);
            System.out.print(" ");
        }
    }
}
