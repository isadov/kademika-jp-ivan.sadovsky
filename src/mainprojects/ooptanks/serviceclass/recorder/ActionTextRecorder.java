package mainprojects.ooptanks.serviceclass.recorder;

import mainprojects.ooptanks.serviceclass.Action;
import mainprojects.ooptanks.serviceclass.ActionsByTank;
import mainprojects.ooptanks.tanks.AbstractTank;
import mainprojects.ooptanks.tanks.BT7;
import mainprojects.ooptanks.tanks.T34;
import mainprojects.ooptanks.tanks.Tiger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ActionTextRecorder {

    private File file;
    private File directory;
    private FileWriter fileWriter;
    private Date curDate;
    private boolean active;

    public ActionTextRecorder() {

    }

    public void init() {

        directory = new File("D:\\JAVA_CLASS\\ActionsByTank");
        if (!directory.exists()) {
            directory.mkdir();
        }
        curDate = new Date();

        file = new File(directory.getAbsolutePath() + "\\" + "tankrec" + curDate.getCurrentDate() + ".txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file, true);
        } catch (IOException e) {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        setActive(true);

    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void record(Action action) {
        try {
            char[] actionCode = codeAction(action);
            fileWriter.write(actionCode);
        } catch(IOException e) {
            try{
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public boolean isActive() {
        return active;
    }

    private char[] codeAction(Action a) {
        char[] code = new char[5];

        code[0] = defineTankCode(a.getPointer());
        code[1] = defineActionCode(a.getNextAct());
        code[2] = definePrevTurnActionCode(a.getPrevTurnAct());
        code[3] = defineActionResultCode(a.isActionResult());
        code[4] = defineAttemptCountCode(a.getAttemptCount());
        return code;
    }

    private char defineTankCode(AbstractTank tank) {
        if (tank instanceof T34) {
            return 'a';
        } else {
            if (tank instanceof Tiger) {
                return 'b';
            } else {
                if (tank instanceof BT7) {
                    return 'c';
                } else {
                    throw new IllegalArgumentException("Illegal type of tank");
                }
            }
        }
    }

    private char defineActionCode(ActionsByTank tankAction) {
        char result;

        switch (tankAction) {
            case FIRE:
                result = 'f';
                break;
            case MOVE:
                result = 'm';
                break;
            case TURN_RIGHT:
                result = 'r';
                break;
            case TURN_LEFT:
                result = 'l';
                break;
            default:
                throw new IllegalArgumentException("Bad output code: tank action");
        }
        return result;
    }

    private char definePrevTurnActionCode(ActionsByTank tankAction) {
        char result;

        switch (tankAction) {
            case TURN_RIGHT:
                result = 'R';
                break;
            case TURN_LEFT:
                result = 'L';
                break;
            default:
                throw new IllegalArgumentException("Bad output code: previous turn action");
        }
        return result;
    }

    private char defineActionResultCode(boolean actionResult) {
        char result;
        if(actionResult) {
            result = '1';
        } else {
            result = '0';
        }
        return result;
    }

    private char defineAttemptCountCode(int attempts) {
        return Integer.toString(attempts).charAt(0);
    }

    public void close () {
        try {
            fileWriter.flush();
            fileWriter.close();
            setActive(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
