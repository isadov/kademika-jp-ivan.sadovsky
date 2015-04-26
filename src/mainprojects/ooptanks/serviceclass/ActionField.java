package mainprojects.ooptanks.serviceclass;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.WindowConstants;

import mainprojects.ooptanks.fieldsobject.BFObject;
import mainprojects.ooptanks.fieldsobject.Brick;
import mainprojects.ooptanks.fieldsobject.Eagle;
import mainprojects.ooptanks.fieldsobject.Earth;
import mainprojects.ooptanks.fieldsobject.Rock;
import mainprojects.ooptanks.fieldsobject.Water;
import mainprojects.ooptanks.tanks.AbstractTank;
import mainprojects.ooptanks.tanks.BT7;
import mainprojects.ooptanks.tanks.T34;
import mainprojects.ooptanks.tanks.Tiger;

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

    JPanel startPanel, gamePanel;
    String buttonName;

    private JLabel tankIcon;

    public ActionField() throws Exception {

        battleField = new BattleField();
        brick = new Brick();
        water = new Water();
        eagle = new Eagle();
        rock = new Rock();

        bullet = new Bullet(-100, -100, Direction.UP);
        contentOfQuadrant = new HashMap<AbstractTank, BFObject>();

        frameUI = new JFrame("TANKS OOP");
        frameUI.setLocationRelativeTo(null);
        frameUI.setMinimumSize(new Dimension(battleField.BF_WIDTH,
                battleField.BF_HEIGHT - 400));
        frameUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameUI.setVisible(true);
        welcomeGameUI();
    }

    public void runTheGame(boolean isReplay) throws Exception {
        Action tmp;

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
        frameBattle.setMinimumSize(new Dimension(battleField.BF_WIDTH,
                battleField.BF_HEIGHT + 33));
        frameBattle.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameBattle.getContentPane().add(this);
        frameBattle.pack();
        frameBattle.setLocationRelativeTo(null);
        frameBattle.setVisible(true);

        tanksOnField = new AbstractTank[TANKS_IN_BATTLE];
        tanksOnField[0] = defender;
        tanksOnField[1] = aggressor;

        while (true) {
            for (AbstractTank t : tanksOnField) {
                tmp = t.setup();
                switch (tmp.getNextAct()) {
                    case FIRE:
                        processFire(tmp.getPointer());
                        break;
                    case MOVE:
                        processMove(tmp);
                        break;
                    case TURN_RIGHT:
                        turnRight(tmp.getPointer());
                        break;
                    case TURN_LEFT:
                        turnLeft(tmp.getPointer());
                        break;
                }

            }

        }

    }

    private void welcomeGameUI() {
        if (startPanel != null) {
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

        radioPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
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
        frameUI.setLocationRelativeTo(null);
        frameUI.repaint();
    }

    private void finishGameUI() {
        frameUI.remove(startPanel);
        startPanel = new JPanel();
        startPanel.setLayout(new GridBagLayout());

        JLabel label = new JLabel("Game Over!");
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
                        frameBattle.dispose();
                    }
                }.start();
            }
        });
        startPanel.add(button2, new GridBagConstraints(1, 2, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                new Insets(0, 0, 10, 0), 0, 0));

        JButton button3 = new JButton("Replay The Game");
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
        frameUI.setLocation(0, 10);
        frameUI.pack();
        frameBattle.dispose();
        frameUI.repaint();
        while (true)
            ;
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
                        battleField.setFieldObject(y, x, new Earth(x * 64,
                                y * 64));
                        bullet.destroy();
                        finishGameUI();
                        return true;
                    } else {
                        if (tmpObject instanceof AbstractTank) {
                            bullet.destroy();
                            processDestroy((AbstractTank) tmpObject);
                            return true;
                        } else {
                            if (tmpObject instanceof Brick) {
                                battleField.setFieldObject(y, x, new Earth(
                                        x * 64, y * 64));
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
        repaint();
    }

    public void processMove(Action action) throws Exception {
        int step = 1;
        int covered = 0;
        String coordinates;
        AbstractTank tank = action.getPointer();

        // //////////////////////////////////////////////////////////
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
                || ((tank instanceof T34) && (tank.getDirection() == Direction.UP && tank
                .getY() <= 384))
                || (tank instanceof T34)
                && (battleField.getNextFieldObject(getQuadrantV(tank.getY()),
                getQuadrantH(tank.getX()), tank.getDirection()) instanceof Brick)) {
            action.setActionResult(false);
            return;
        }
        // ////////////////////////////////////////////////////////
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

                tank.updateY(-step);

            } else if (tank.getDirection() == Direction.DOWN) {
                coordinates = getQuadrant(tank.getX(), tank.getY() + 64);

                tank.updateY(step);

            } else if (tank.getDirection() == Direction.LEFT) {
                coordinates = getQuadrant(tank.getX() - 1, tank.getY());

                tank.updateX(-step);

            } else {
                coordinates = getQuadrant(tank.getX() + 64, tank.getY());

                tank.updateX(step);

            }
            covered += step;
            repaint();
            Thread.sleep(tank.getMaxSpeed());
        }
        // ///////////////////////////////////////////////////////////////
        contentOfQuadrant.put(tank, battleField.getFieldObject(
                getQuadrantV(tank.getY()), getQuadrantH(tank.getX())));
        // ///////////////////////////////////////////////////////////////
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

                } else if (bullet.getDirection() == Direction.DOWN) {
                    bullet.updateY(step);

                } else if (bullet.getDirection() == Direction.LEFT) {
                    bullet.updateX(-step);

                } else {
                    bullet.updateX(step);

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