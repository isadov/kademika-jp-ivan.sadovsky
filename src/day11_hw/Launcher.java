package day11_hw;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Launcher {

    public static void main(String[] args) throws IOException{

        TextList textList = new TextList();
        ArrayList<Profile> profileArrayList = new ArrayList<>();

        textList.print();

        String numberOfProfiles = JOptionPane.showInputDialog("Enter number of profiles");
        int number = Integer.valueOf(numberOfProfiles);
        for(int i = 0; i < number; i++) {
            Profile profile = new Profile();
            profile.setName(JOptionPane.showInputDialog("Enter Name"));
            profile.setSecondName(JOptionPane.showInputDialog("Enter Surname"));
            profileArrayList.add(profile);
        }

        textList.add(profileArrayList);
        textList.print();

    }
}
