package day11_hw;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TextListWriter implements ListWriter {
    @Override
    public void write(String fileName, ArrayList<Profile> profiles) {
        try
                (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream)) {

            objectOutputStream.writeObject(profiles);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
