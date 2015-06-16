package day11.encoding.change_encoding;

import java.io.File;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException{
        String path = "D:\\JAVA_CLASS\\test.txt";
        String currentEncoding = "UTF-8";
        String neededEncoding = "cp1251";

        File f = new File(path);
        f.createNewFile();

        changeEncoding(f, currentEncoding, neededEncoding);

    }

    public static void changeEncoding(File file, String currentEncoding, String neededEncoding) {
        FileReader fileReader = new TextReader();
        String data = fileReader.read(file.getAbsolutePath(),currentEncoding);

        System.out.println("Current Data In file: " + data);

        FileWriter fileWriter = new TextWriter();
        fileWriter.write(data, file.getAbsolutePath(), neededEncoding);

        fileReader = new TextReader();
        data = fileReader.read(file.getAbsolutePath(), currentEncoding);
        System.out.println("Changed encoding in file: " + data);

    }
}
