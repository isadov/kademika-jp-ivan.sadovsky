package day11;

import java.io.FileInputStream;
import java.io.IOException;

public class PrintStreamData {

    public static void main(String[] args) {

        String filename = "D:\\JAVA_CLASS\\test.txt";

        try (FileInputStream inputStream = new FileInputStream(filename)) {
            printStreamFromFile(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printStreamFromFile(FileInputStream fileInputStream) {

        StringBuilder builder = new StringBuilder();
        int i;

        try {
            while ((i = fileInputStream.read()) != -1) {
                builder.append((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(builder.toString());
    }
}
