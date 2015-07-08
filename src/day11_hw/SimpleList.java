package day11_hw;

import java.util.ArrayList;

public interface SimpleList {

    public void add(ArrayList<Profile> profiles);
    public void remove(int index);
    public Profile get(int index);
    public void print();

}
