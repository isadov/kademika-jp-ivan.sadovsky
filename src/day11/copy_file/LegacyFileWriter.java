package day11.copy_file;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class LegacyFileWriter implements FileWriter {


    @Override
    public void write(String data, String fileName) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
    }
}
