package day11.file;

import java.io.File;

public class PathToFile {

    public static void main(String[] args) {

        File file = new File("test.txt");

        System.out.println(System.getProperty("user.dir"));
        System.out.println(File.separator);
        System.out.println(File.pathSeparator);

        for(File f : File.listRoots()) {
            System.out.println(f.getAbsolutePath());
        }

        System.out.println(getRelativeFileDir());
        System.out.println(getFiledPath());
    }

    public static String getFiledPath() {
        String path = "src/com/kademika/day11/files/";
        path = path.replace("/", File.separator);

        File thisFile = new File(path, PathToFile.class.getSimpleName() + ".java");

        return thisFile.getAbsolutePath();
    }

    public static String getRelativeFileDir() {
        String path = "src/com/kademika/day11/io/files/";
        return path.replace("/", File.separator);
    }
}
