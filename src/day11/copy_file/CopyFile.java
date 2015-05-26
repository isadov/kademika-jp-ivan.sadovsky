package day11.copy_file;

import java.io.File;

public class CopyFile {

    public static void copyFile(File srcFile) {

        String parent = srcFile.getParent() + "\\";
        String name = srcFile.getName().substring(0, srcFile.getName().indexOf("."));
        String ext = srcFile.getName().substring(srcFile.getName().indexOf("."));

        BufferedReader bufferedReader = new BufferedReader();
        String data = bufferedReader.read(srcFile.getAbsolutePath());

        BufferedWriter bufferedWriter = new BufferedWriter();
        bufferedWriter.write(data, parent + name + "Copy" + ext);

    }
}
