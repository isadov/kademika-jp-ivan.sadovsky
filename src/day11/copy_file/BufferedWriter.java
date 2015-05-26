package day11.copy_file;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedWriter implements FileWriter {

    @Override
    public void write(String data, String fileName) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 256)) {

            bufferedOutputStream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
