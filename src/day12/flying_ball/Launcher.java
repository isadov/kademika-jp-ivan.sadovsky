package day12.flying_ball;

import java.awt.*;

public class Launcher {
    public static void main(String[] args) throws InterruptedException{
        Field field = new Field();

        FlyingBall flyingBal = new FlyingBall();
        field.add(flyingBal);

        FlyingBall flyingBall2 = new FlyingBall();
        flyingBall2.setyPosition(80);
        flyingBall2.setColor(Color.GREEN);
        flyingBall2.setSpeed(5);
        field.add(flyingBall2);

        FlyingBall flyingBall1 = new FlyingBall();
        flyingBall1.setyPosition(260);
        flyingBall1.setColor(new Color(100, 100, 100));
        flyingBall1.setSpeed(11);
        field.add(flyingBall1);

        FlyingBall flyingBall3 = new FlyingBall();
        flyingBall3.setyPosition(110);
        flyingBall3.setColor(Color.CYAN);
        flyingBall3.setSpeed(6);
        field.add(flyingBall3);

        FlyingBall flyingBall4 = new FlyingBall();
        flyingBall4.setyPosition(140);
        flyingBall4.setColor(Color.MAGENTA);
        flyingBall4.setSpeed(7);
        field.add(flyingBall4);

        FlyingBall flyingBall5 = new FlyingBall();
        flyingBall5.setyPosition(170);
        flyingBall5.setColor(Color.ORANGE);
        flyingBall5.setSpeed(8);
        field.add(flyingBall5);

        FlyingBall flyingBall6 = new FlyingBall();
        flyingBall6.setyPosition(200);
        flyingBall6.setColor(Color.RED);
        flyingBall6.setSpeed(9);
        field.add(flyingBall6);

        FlyingBall flyingBall7 = new FlyingBall();
        flyingBall7.setyPosition(230);
        flyingBall7.setColor(new Color(100, 120, 1));
        flyingBall7.setSpeed(10);
        field.add(flyingBall7);

        while (true) {
            field.repaint();
        }
    }
}
