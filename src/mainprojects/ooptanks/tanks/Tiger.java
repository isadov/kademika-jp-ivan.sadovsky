package mainprojects.ooptanks.tanks;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import mainprojects.ooptanks.fieldsobject.BFObject;
import mainprojects.ooptanks.fieldsobject.Brick;
import mainprojects.ooptanks.fieldsobject.Eagle;
import mainprojects.ooptanks.fieldsobject.Rock;
import mainprojects.ooptanks.serviceclass.Action;
import mainprojects.ooptanks.serviceclass.ActionsByTank;
import mainprojects.ooptanks.serviceclass.BattleField;
import mainprojects.ooptanks.serviceclass.Direction;

public class Tiger extends AbstractTank {

    private int armor;

    public Tiger() {
        super();
    }

    public Tiger(BattleField battle, int x, int y,
                 Direction direction) {
        super(battle, x, y, direction);
        armor = 1;
        this.colorTank = Color.RED;
        this.colorTower = Color.BLACK;
        this.maxSpeed = 1; //12
        this.action.setPointer(this);
        this.action.setNextAct(null);
        this.action.setActionResult(true);
        this.bf.setFieldObject(y / 64, x / 64, this);
        this.imagesOfTank = new HashMap<Direction, BufferedImage>();

        try {
            this.imagesOfTank.put(Direction.UP, ImageIO.read(new File(
                    "tanks_image/TankAttTop.png")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            this.imagesOfTank.put(Direction.RIGHT, ImageIO.read(new File(
                    "tanks_image/TankAttRight.png")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            this.imagesOfTank.put(Direction.DOWN, ImageIO.read(new File(
                    "tanks_image/TankAttDown.png")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            this.imagesOfTank.put(Direction.LEFT, ImageIO.read(new File(
                    "tanks_image/TankAttLeft.png")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Tiger(BattleField battle) {
        super(battle);
        armor = 1;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void updateArmor(int armor) {
        this.armor += armor;
    }

    @Override
    public Action setup() {
        if (this.action.isActionResult()) {
            this.action.setAttemptCount(0);
            if (enemyDirect()) {
                this.action.setNextAct(ActionsByTank.FIRE);
            } else {
                this.action.setNextAct(ActionsByTank.MOVE);
            }
        } else {
            switch (this.action.getNextAct()) {
                case FIRE:
                    this.action.setNextAct(ActionsByTank.MOVE);
                    break;
                case TURN_LEFT:
                    if (enemyDirect()) {
                        this.action.setNextAct(ActionsByTank.FIRE);
                    } else {
                        this.action.setNextAct(ActionsByTank.MOVE);
                    }
                    break;
                case TURN_RIGHT:
                    if (enemyDirect()) {
                        this.action.setNextAct(ActionsByTank.FIRE);
                    } else {
                        this.action.setNextAct(ActionsByTank.MOVE);
                    }
                    break;
                case MOVE:
                    if (this.action.getPrevTurnAct() == ActionsByTank.TURN_RIGHT
                            && this.action.getAttemptCount() == 0) {
                        this.action.setNextAct(ActionsByTank.TURN_LEFT);
                        this.action.setPrevTurnAct(ActionsByTank.TURN_LEFT);
                        this.action
                                .setAttemptCount(this.action.getAttemptCount() + 1);
                    } else {
                        if (this.action.getAttemptCount() == 0) {
                            this.action.setNextAct(ActionsByTank.TURN_RIGHT);
                            this.action.setPrevTurnAct(ActionsByTank.TURN_RIGHT);
                            this.action.setAttemptCount(this.action
                                    .getAttemptCount() + 1);
                        } else {
                            this.action.setNextAct(ActionsByTank.TURN_LEFT);
                            this.action.setPrevTurnAct(ActionsByTank.TURN_LEFT);
                            this.action.setAttemptCount(this.action
                                    .getAttemptCount() + 1);
                        }
                    }
            }
        }
        return this.action;
    }

    private boolean enemyDirect() {
        boolean enemyFound = false;
        int vertical = this.y / 64;
        int horizontal = this.x / 64;

        switch (this.direction) {
            case UP:
                enemyFound = checkLine(vertical, -1, horizontal, 0);
                break;
            case DOWN:
                enemyFound = checkLine(vertical, 1, horizontal, 0);
                break;
            case LEFT:
                enemyFound = checkLine(vertical, 0, horizontal, -1);
                break;
            case RIGHT:
                enemyFound = checkLine(vertical, 0, horizontal, 1);
                break;
        }
        return enemyFound;
    }

    private boolean checkLine(int vertical, int stepVertical, int horizontal, int stepHorizontal) {
        boolean isEnemy = false;
        BFObject tmp = null;

        do {
            tmp = this.bf.getNextFieldObject(vertical, horizontal, this.getDirection());
            if (tmp instanceof Rock) {
                break;
            }
            if (tmp instanceof T34 || tmp instanceof Brick
                    || tmp instanceof Eagle) {
                isEnemy = true;
                break;
            }
            vertical += stepVertical;
            horizontal += stepHorizontal;
        }
        while (tmp != null);

        return isEnemy;
    }

}
