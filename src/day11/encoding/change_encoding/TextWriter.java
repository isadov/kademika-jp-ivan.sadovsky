package day11.encoding.change_encoding;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class TextWriter implements FileWriter {
    @Override
    public void write(String data, String filename, String encoding) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(filename);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, encoding))
        {
            outputStreamWriter.write(data);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
