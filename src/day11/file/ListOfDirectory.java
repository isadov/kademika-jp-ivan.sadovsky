package day11.file;

import java.io.File;

public class ListOfDirectory {

    public static void main(String[] args) {

        String dirname = "d:\\JAVA_CLASS";

        File file = new File(dirname);

        if (file.isDirectory()) {

            System.out.println(dirname);
            String s[] = file.list();

            for (int i = 0; i < s.length; i++) {

                File file1 = new File(dirname + "\\" + s[i]);
                if (file1.isDirectory()) {
                    System.out.println(s[i] + " is a directory");
                } else {
                    System.out.println(s[i] + " is a file");
                }
            }
        } else {
            System.out.println(dirname + " is not a directory");
        }
    }
}
