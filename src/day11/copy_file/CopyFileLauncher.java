package day11.copy_file;

import java.io.File;

public class CopyFileLauncher {

    public static void main(String[] args) {

        String path = "d:\\JAVA_CLASS\\test.txt";

        CopyFile.copyFile(new File(path));

    }
}
