package day11.encoding.change_encoding;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextReader implements FileReader {


    @Override
    public String read(String filename, String encoding) {
        StringBuilder builder = new StringBuilder();

        try(FileInputStream fileInputStream = new FileInputStream(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, encoding);
            java.io.BufferedReader bufferedReader = new java.io.BufferedReader(inputStreamReader);
        ) {

            String string;
            while ((string = bufferedReader.readLine()) != null) {
                builder.append(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}

