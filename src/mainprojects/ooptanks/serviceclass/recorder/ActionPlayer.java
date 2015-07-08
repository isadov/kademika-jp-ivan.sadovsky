package mainprojects.ooptanks.serviceclass.recorder;

import mainprojects.ooptanks.serviceclass.Action;
import mainprojects.ooptanks.tanks.AbstractTank;

import java.io.*;

public class ActionPlayer {

    private File file;
    private File directory;
    FileInputStream fis;
    ObjectInputStream ois;

    public ActionPlayer() {

    }

    public void init(AbstractTank tank) {
        directory = new File("D:\\JAVA_CLASS\\ActionsByTank");
        if (!directory.exists()) {
            System.out.println("Directory " + directory.getAbsolutePath() + " not found");
            System.exit(0);
        }
        file = new File(directory.getAbsolutePath() + "\\" + tank.getClass().getName().substring(tank.getClass().getName().indexOf(".")) + ".bin");
        if (!file.exists()) {
            System.out.println("File " + file.getAbsolutePath() + " not found");
            System.exit(0);
        }
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            try {
                ois.close();
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public Action read() {
        Action action = null;
        try {
            action = (Action) ois.readObject();
        } catch (IOException e) {
            try {
                ois.close();
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return action;
    }

    public void close() {
        try {
            ois.close();
            fis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
