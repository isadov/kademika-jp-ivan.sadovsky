package day11_hw;

import java.io.*;
import java.util.ArrayList;

public class TextListReader implements ListReader {


    @Override
    public ArrayList<Profile> read(String fileName) throws IOException {
        ArrayList<Profile> returnObject = null;
        File file = new File(fileName);

        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
             ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream)) {

            returnObject = (ArrayList<Profile>) objectInputStream.readObject();

        } catch (FileNotFoundException ef) {
            file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return returnObject;
    }
}
