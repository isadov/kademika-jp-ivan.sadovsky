package day11.byte_type;

import java.io.ByteArrayInputStream;

public class PrintStreamData {

    public static void main(String[] args) {

        byte[] bytes = {63, 64, 65, 66, 67, -27, -128, 0};

        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

        printStream(inputStream);

    }

    public static void printStream(ByteArrayInputStream inputStream) {
        int i = 0;
        System.out.print("Stream:[");
        while ((i = inputStream.read()) != - 1) {
            int j = (byte) i;
            System.out.print(j + " ");
        }
        System.out.print("]");
    }
}
