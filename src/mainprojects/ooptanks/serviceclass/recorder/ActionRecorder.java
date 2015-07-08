package mainprojects.ooptanks.serviceclass.recorder;

import mainprojects.ooptanks.serviceclass.Action;
import mainprojects.ooptanks.tanks.AbstractTank;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ActionRecorder {

    private File file;
    private File directory;
    FileOutputStream fos;
    ObjectOutputStream oos;


    public ActionRecorder() {

    }

    public void init(AbstractTank tank) {

        directory = new File("D:\\JAVA_CLASS\\ActionsByTank");
        if(!directory.exists()) {
            directory.mkdir();
        }
        file = new File(directory.getAbsolutePath() + "\\" + tank.getClass().getName().substring(tank.getClass().getName()
                .indexOf(".") + 1) + ".bin");
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            try {
                oos.close();
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void record (Action action) {
        try {
            oos.writeObject(action);
            oos.reset();
        } catch (IOException e) {
            try {
                oos.flush();
                oos.close();
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            oos.writeObject(null);
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
