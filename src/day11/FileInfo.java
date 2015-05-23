package day11;

import java.io.File;

public class FileInfo {

    public static void main(String[] args) {
        File file = new File("d:\\JAVA_CLASS");

        print("File name : " + file.getName());
        print("File abs path : " + file.getAbsoluteFile());
        print("File path : " + file.getPath());
        print(file.canWrite() ? "is writable" : "is not writable");
        print(file.canRead() ? "is readable" : "is not readable");
        print(file.isAbsolute() ? "is absolute" : "is not absolute");
        print("File last modified: " + file.lastModified());
        print("File size: " + file.length() + "Bytes");

    }



    static void print(String s) {
        System.out.println(s);
    }
}
