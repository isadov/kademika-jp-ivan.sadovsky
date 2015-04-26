package mainprojects.ooptanks.serviceclass;

import mainprojects.ooptanks.tanks.AbstractTank;

public class Action {

    private AbstractTank pointer;
    private ActionsByTank nextAct;
    private ActionsByTank prevTurnAct = ActionsByTank.TURN_LEFT;
    private boolean actionResult;
    private int attemptCount;


    public int getAttemptCount() {
        return attemptCount;
    }

    public void setAttemptCount(int attemptCount) {
        this.attemptCount = attemptCount;
    }

    public ActionsByTank getPrevTurnAct() {
        return prevTurnAct;
    }

    public void setPrevTurnAct(ActionsByTank prevTurnAct) {
        this.prevTurnAct = prevTurnAct;
    }

    public boolean isActionResult() {
        return actionResult;
    }

    public void setActionResult(boolean actionResult) {
        this.actionResult = actionResult;
    }

    public AbstractTank getPointer() {
        return pointer;
    }

    public void setPointer(AbstractTank pointer) {
        this.pointer = pointer;
    }

    public ActionsByTank getNextAct() {
        return nextAct;
    }

    public void setNextAct(ActionsByTank nextAct) {
        this.nextAct = nextAct;
    }

}