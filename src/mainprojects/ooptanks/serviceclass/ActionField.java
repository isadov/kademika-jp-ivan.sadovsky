package mainprojects.ooptanks.serviceclass;

import mainprojects.ooptanks.fieldsobject.*;
import mainprojects.ooptanks.serviceclass.recorder.ActionTextPlayer;
import mainprojects.ooptanks.serviceclass.recorder.ActionTextRecorder;
import mainprojects.ooptanks.tanks.AbstractTank;
import mainprojects.ooptanks.tanks.BT7;
import mainprojects.ooptanks.tanks.T34;
import mainprojects.ooptanks.tanks.Tiger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ActionField extends JPanel {

    final boolean COLORDED_MODE = false;
    final int TANKS_IN_BATTLE = 2;
    BattleField battleField;

    T34 defender;
    Bullet bullet;

    AbstractTank aggressor;
    AbstractTank[] tanksOnField;

    Brick brick;
    Water water;
    Eagle eagle;
    Rock rock;

    HashMap<AbstractTank, BFObject> contentOfQuadrant;
    JFrame frameBattle, frameUI;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
    JMenu subMenu;
    JRadioButtonMenuItem rbMenuItem;
    JRadioButton rButton1, rButton2;

    ActionTextRecorder actionRecorder;
    ActionTextPlayer actionPlayer;
    JPanel startPanel, gamePanel;
    String buttonName;

    public ActionField() throws Exception {

        battleField = new BattleField();
        brick = new Brick();
        water = new Water();
        eagle = new Eagle();
        rock = new Rock();
        bullet = new Bullet(-100, -100, Direction.UP);
        contentOfQuadrant = new HashMap<AbstractTank, BFObject>();
        frameUI = new JFrame("TANKS OOP");
        frameUI.setLocation(650, 50);
        frameUI.setMinimumSize(new Dimension(battleField.BF_WIDTH,
                battleField.BF_HEIGHT - 400));
        frameUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameUI.setVisible(true);
        welcomeGameUI();
    }

    public void runTheGame(boolean isReplay) throws Exception {
        Action tmpAction;

        battleField = new BattleField();
        defender = new T34(battleField, 128, 512, Direction.UP);

        if (rButton1.isSelected()) {
            aggressor = new Tiger(battleField, 128, 64, Direction.LEFT);
        } else {
            aggressor = new BT7(battleField, 512, 256, Direction.UP);
        }

        contentOfQuadrant.put(defender,
                new Earth(defender.getX(), defender.getY()));
        contentOfQuadrant.put(aggressor,
                new Earth(aggressor.getX(), aggressor.getY()));

        frameBattle = new JFrame("TANKS OOP");
        frameBattle.setLocation(750, 150);
        frameBattle.setMinimumSize(new Dimension(battleField.BF_WIDTH,
                battleField.BF_HEIGHT + 33));// 22));
        frameBattle.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameBattle.getContentPane().add(this);
        frameBattle.pack();
        frameBattle.setVisible(true);

        tanksOnField = new AbstractTank[TANKS_IN_BATTLE];
        tanksOnField[0] = defender;
        tanksOnField[1] = aggressor;

        if(isReplay){
            actionPlayer = new ActionTextPlayer();
            actionPlayer.init(defender);

            while ((actionPlayer.read(defender.action))!=-1){
                selectAction(defender.action);
            }

            actionPlayer.close();
            closeGame();
        }else {
            actionRecorder = new ActionTextRecorder();
            actionRecorder.init();

            while (true) {
                int i = 0;
                for (AbstractTank t : tanksOnField) {
                    tmpAction = t.setup();
                    actionRecorder.record(tmpAction);
                    selectAction(tmpAction);
                }
            }

        }
    }
    private void selectAction(Action action) throws Exception {

        switch (action.getNextAct()) {
            case FIRE:
                processFire(action.getPointer());
                break;
            case MOVE:
                processMove(action);
                break;
            case TURN_RIGHT:
                turnRight(action.getPointer());
                break;
            case TURN_LEFT:
                turnLeft(action.getPointer());
                break;
        }
    }

    private void closeGame(){
        if(actionRecorder.isActive()) {
            actionRecorder.close();
        }

        frameBattle.dispose();
    }

    private void welcomeGameUI() {
        if(startPanel!=null){
            frameUI.remove(startPanel);
        }
        startPanel = new JPanel();
        startPanel.setLayout(new GridBagLayout());

        JLabel label = new JLabel("Select the tank for Aggressor");
        startPanel.add(label, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                new Insets(0, 0, 10, 0), 0, 0));

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(2, 1, 1, 1));
        ButtonGroup bGroup = new ButtonGroup();
        rButton1 = new JRadioButton("Tiger", true);
        bGroup.add(rButton1);
        radioPanel.add(rButton1);
        rButton2 = new JRadioButton("BT7", false);
        bGroup.add(rButton2);
        radioPanel.add(rButton2);
        radioPanel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        startPanel.add(radioPanel, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                new Insets(0, 0, 10, 0), 0, 0));
        JButton button1 = new JButton("Start Game");
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    public void run() {
                        try {
                            runTheGame(false);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }.start();
            }
        });
        startPanel.add(button1, new GridBagConstraints(1, 2, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                new Insets(0, 0, 10, 0), 0, 0));

        frameUI.add(startPanel);
        frameUI.pack();
        frameUI.repaint();
    }

    private void finishGameUI() {
        frameUI.remove(startPanel);
        startPanel = new JPanel();
        startPanel.setLayout(new GridBagLayout());

        JLabel label = new JLabel("Game over!");
        startPanel.add(label, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                new Insets(0, 0, 10, 0), 0, 0));
        JButton button2 = new JButton("Start New Game");
        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    public void run() {
                        welcomeGameUI();
                    }
                }.start();
            }
        });
        startPanel.add(button2, new GridBagConstraints(1, 2, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                new Insets(0, 0, 10, 0), 0, 0));
        JButton button3 = new JButton("Replay the Game");
        button3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    public void run() {
                        try {
                            runTheGame(true);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }.start();
            }
        });
        startPanel.add(button3, new GridBagConstraints(1, 4, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                new Insets(0, 0, 10, 0), 0, 0));
        frameUI.add(startPanel);
        frameUI.pack();
        frameUI.repaint();
        closeGame();
        while (true);
    }

    private String getQuadrantXY(int v, int h) {
        return (v - 1) * 64 + "_" + (h - 1) * 64;
    }

    public String getQuadrant(int x, int y) {
        return Integer.toString(y / 64) + "_" + Integer.toString(x / 64);
    }

    public int getQuadrantV(int x) {
        return x / 64;
    }

    public int getQuadrantH(int y) {
        return y / 64;
    }

    private boolean processInterception(Bullet bullet) throws Exception {
        BFObject tmpObject;

        int y = bullet.getY() / 64;
        int x = bullet.getX() / 64;
        System.out.print("Y:" + bullet.getY());
        System.out.println("X:" + bullet.getX());
        System.out.print("y:" + y);
        System.out.println("x:" + x);

        if ((y >= 0) && (y < 9) && (x >= 0) && (x < 9)) {
            tmpObject = battleField.getFieldObject(y, x);
            if (tmpObject instanceof Water || tmpObject instanceof Earth) {
                return false;
            } else {
                if (tmpObject instanceof Rock) {
                    bullet.destroy();
                    return true;
                } else {
                    if (tmpObject instanceof Eagle) {
                        battleField.setFieldObject(y, x, new Earth(x * 64,y * 64));
                        System.out.println("Made empty");
                        bullet.destroy();
                        finishGameUI();
                        return true;
                    } else {
                        if (tmpObject instanceof AbstractTank) {
                            System.out.println("Made empty");
                            bullet.destroy();
                            processDestroy((AbstractTank) tmpObject);
                            return true;
                        }else{
                            if(tmpObject instanceof Brick) {
                                battleField.setFieldObject(y, x, new Earth(x * 64,y * 64));
                                System.out.println("Made empty");
                                bullet.destroy();
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    public void turnRight(AbstractTank tank) throws Exception {

        switch (tank.getDirection()) {
            case UP:
                tank.setDirection(Direction.RIGHT);
                break;
            case RIGHT:
                tank.setDirection(Direction.DOWN);
                break;
            case DOWN:
                tank.setDirection(Direction.LEFT);
                break;
            case LEFT:
                tank.setDirection(Direction.UP);
                break;
        }
        System.out.print("Direction" + tank.getDirection());
        repaint();
    }

    public void turnLeft(AbstractTank tank) throws Exception {

        switch (tank.getDirection()) {
            case UP:
                tank.setDirection(Direction.LEFT);
                break;
            case LEFT:
                tank.setDirection(Direction.DOWN);
                break;
            case DOWN:
                tank.setDirection(Direction.RIGHT);
                break;
            case RIGHT:
                tank.setDirection(Direction.UP);
                break;
        }
        System.out.print("Direction" + tank.getDirection());
        repaint();
    }

    public void processMove(Action action) throws Exception {
        int step = 1;
        int covered = 0;
        String coordinates;
        AbstractTank tank = action.getPointer();

        if ((tank.getDirection() == Direction.UP && tank.getY() == 0)
                || (tank.getDirection() == Direction.DOWN && tank.getY() >= 512)
                || (tank.getDirection() == Direction.LEFT && tank.getX() == 0)
                || (tank.getDirection() == Direction.RIGHT && tank.getX() >= 512)
                || battleField.getNextFieldObject(getQuadrantV(tank.getY()),
                getQuadrantH(tank.getX()), tank.getDirection()) instanceof Rock
                || battleField.getNextFieldObject(getQuadrantV(tank.getY()),
                getQuadrantH(tank.getX()), tank.getDirection()) == null
                || battleField.getNextFieldObject(getQuadrantV(tank.getY()),
                getQuadrantH(tank.getX()), tank.getDirection()) instanceof Eagle
                || battleField.getNextFieldObject(getQuadrantV(tank.getY()),
                getQuadrantH(tank.getX()), tank.getDirection()) instanceof AbstractTank
                || ((tank instanceof T34) && (tank.getDirection() == Direction.UP && tank.getY() <= 384))
                || (tank instanceof T34)
                && (battleField.getNextFieldObject(getQuadrantV(tank.getY()),
                getQuadrantH(tank.getX()), tank.getDirection()) instanceof Brick)) {
            System.out.println("[illegal move] direction: "
                    + tank.getDirection() + " tankX: " + tank.getX()
                    + ", tankY: " + tank.getY());
            action.setActionResult(false);
            return;
        }
        // insert the previous content of current quadrant
        if (contentOfQuadrant.containsKey(tank)) {
            battleField.setFieldObject(getQuadrantV(tank.getY()),
                    getQuadrantH(tank.getX()), contentOfQuadrant.get(tank));
        } else {
            battleField.setFieldObject(getQuadrantV(tank.getY()),
                    getQuadrantH(tank.getX()),
                    new Earth(tank.getX(), tank.getY()));
        }

        while (covered < 64) {

            if (tank.getDirection() == Direction.UP) {
                coordinates = getQuadrant(tank.getX(), tank.getY() - 1);
                System.out.println("coordinates " + coordinates);
                tank.updateY(-step);
                System.out.println("[move up] direction: "
                        + tank.getDirection() + " tankX: " + tank.getX()
                        + ", tankY: " + tank.getY());
            } else if (tank.getDirection() == Direction.DOWN) {
                coordinates = getQuadrant(tank.getX(), tank.getY() + 64);
                System.out.println("coordinates " + coordinates);
                tank.updateY(step);
                System.out.println("[move down] direction: "
                        + tank.getDirection() + " tankX: " + tank.getX()
                        + ", tankY: " + tank.getY());
            } else if (tank.getDirection() == Direction.LEFT) {
                coordinates = getQuadrant(tank.getX() - 1, tank.getY());
                System.out.println("coordinates " + coordinates);
                tank.updateX(-step);
                System.out.println("[move left] direction: "
                        + tank.getDirection() + " tankX: " + tank.getX()
                        + ", tankY: " + tank.getY());
            } else {
                coordinates = getQuadrant(tank.getX() + 64, tank.getY());
                System.out.println("coordinates " + coordinates);
                tank.updateX(step);
                System.out.println("[move right] direction: "
                        + tank.getDirection() + " tankX: " + tank.getX()
                        + ", tankY: " + tank.getY());
            }
            covered += step;
            repaint();
            Thread.sleep(tank.getMaxSpeed());
        }
        contentOfQuadrant.put(tank, battleField.getFieldObject(
                getQuadrantV(tank.getY()), getQuadrantH(tank.getX())));
        battleField.setFieldObject(getQuadrantV(tank.getY()),
                getQuadrantH(tank.getX()), tank);
        action.setActionResult(true);
    }

    public void processFire(AbstractTank tank) throws Exception {
        bullet = new Bullet(tank.getX() + 25, tank.getY() + 25,
                tank.getDirection());
        int covered = 0;
        int step = 1;

        while (bullet.getX() > 0 && bullet.getX() <= 576 && bullet.getY() > 0
                && bullet.getY() <= 576) {
            while (covered < 64) {
                if (bullet.getDirection() == Direction.UP) {
                    bullet.updateY(-step);
                    System.out.println("[move up] direction: "
                            + bullet.getDirection() + " bulX: " + bullet.getX()
                            + ", bulletY: " + bullet.getY());
                } else if (bullet.getDirection() == Direction.DOWN) {
                    bullet.updateY(step);
                    System.out.println("[move down] direction: "
                            + bullet.getDirection() + " bulX: " + bullet.getX()
                            + ", bulletY: " + bullet.getY());
                } else if (bullet.getDirection() == Direction.LEFT) {
                    bullet.updateX(-step);
                    System.out.println("[move left] direction: "
                            + bullet.getDirection() + " bulX: " + bullet.getX()
                            + ", bulletY: " + bullet.getY());
                } else {
                    bullet.updateX(step);
                    System.out.println("[move right] direction: "
                            + bullet.getDirection() + " bulX: " + bullet.getX()
                            + ", bulletY: " + bullet.getY());
                }
                covered += step;
                repaint();
                Thread.sleep(bullet.getSpeed());
            }
            covered = 0;
            if (processInterception(bullet)) {
                repaint();
                Thread.sleep(bullet.getSpeed());
            }
        }
    }

    public void processDestroy(AbstractTank tank) throws Exception {

        if (tank instanceof Tiger) {
            if (((Tiger) tank).getArmor() > 0) {
                ((Tiger) tank).setArmor(0);
                return;
            }
        }
        tank.setX(-100);
        tank.setY(-100);
        repaint();
        finishGameUI();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        BFObject tmpObject;
        int i = 0;

        Color cc;
        for (int v = 0; v < 9; v++) {
            for (int h = 0; h < 9; h++) {
                if (COLORDED_MODE) {
                    if (i % 2 == 0) {
                        cc = new Color(252, 241, 177);
                    } else {
                        cc = new Color(233, 243, 255);
                    }
                } else {
                    cc = new Color(180, 180, 180);
                }
                i++;
                g.setColor(cc);
                g.clearRect(h * 64, v * 64, 64, 64);
            }
        }

        for (int v = 0; v < battleField.getDimentionY(); v++) {
            for (int h = 0; h < battleField.getDimentionX(); h++) {
                tmpObject = battleField.getFieldObject(v, h);
                if (!(tmpObject instanceof Water)) {
                    if ((tmpObject instanceof AbstractTank)
                            && (!(contentOfQuadrant
                            .get((AbstractTank) tmpObject) instanceof Water))) {
                        contentOfQuadrant.get((AbstractTank) tmpObject).draw(g);
                    } else {
                        tmpObject.draw(g);
                    }
                }
            }
        }
        if (defender != null) {
            defender.draw(g);
        }
        if (aggressor != null) {
            aggressor.draw(g);
        }

        for (int v = 0; v < battleField.getDimentionY(); v++) {
            for (int h = 0; h < battleField.getDimentionX(); h++) {
                tmpObject = battleField.getFieldObject(v, h);
                if (tmpObject instanceof Water) {
                    tmpObject.draw(g);
                } else {
                    if ((tmpObject instanceof AbstractTank)
                            && (contentOfQuadrant.get((AbstractTank) tmpObject) instanceof Water)) {
                        contentOfQuadrant.get((AbstractTank) tmpObject).draw(g);
                    }
                }
            }
        }
        bullet.draw(g);
    }
}