package day11_hw;

import java.io.IOException;
import java.util.ArrayList;

public interface ListReader {

    public ArrayList <Profile> read(String fileName) throws IOException;
}
