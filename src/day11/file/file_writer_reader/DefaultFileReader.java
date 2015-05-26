package day11.file.file_writer_reader;

import java.io.FileInputStream;
import java.io.IOException;

public class DefaultFileReader implements FileReader {
    @Override
    public String read(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(fileName)) {
            int i;
            while ((i = fis.read()) != -1) {
                builder.append((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
