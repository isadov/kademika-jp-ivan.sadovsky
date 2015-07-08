package day11_hw;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TextList implements SimpleList {

    private TextListReader textListReader;
    private TextListWriter textListWriter;
    private ArrayList<Profile> profileArrayList;
    private final String fileName = "list.txt";
    private String path;
    private File file;

    public TextList() {
        System.out.println();
        textListReader = new TextListReader();
        textListWriter = new TextListWriter();
        profileArrayList = new ArrayList<>();
        file = new File("");
        path = file.getAbsolutePath();
        path = path + "\\testing_folder\\";
        file = new File(path + fileName);
        checkFileContains();

    }

    @Override
    public void add(ArrayList<Profile> profiles) {
        for (Profile profile : profiles) {
            profileArrayList.add(profile);
        }
        textListWriter.write(path + fileName, profileArrayList);
    }

    @Override
    public void remove(int index) {
        checkFileContains();
        if(index < profileArrayList.size() && index >=0) {
            profileArrayList.remove(index);
            textListWriter.write(path + fileName, profileArrayList);
        } else {
            throw new IllegalArgumentException("Illegal Parameter -- REMOVE");
        }
    }

    @Override
    public Profile get(int index) {
        checkFileContains();
        if(index < profileArrayList.size() && index >= 0) {
            return profileArrayList.get(index);
        } else {
            throw new IllegalArgumentException("Illegal Parameter -- GET");
        }
    }

    @Override
    public void print() {
        checkFileContains();
        System.out.println("List Contains " + profileArrayList.size() + " notes");
        int i = 0;
        for ( Profile profile : profileArrayList) {
            System.out.println(((i++) + 1) + ": " + profile.getName() + " " + profile.getSecondName());
        }
        System.out.println();
    }

    private void checkFileContains() {
        if(file.exists() && file.length() > 0) {
            try {
                profileArrayList = textListReader.read(path + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
