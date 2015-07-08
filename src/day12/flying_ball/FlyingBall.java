package day12.flying_ball;

import java.awt.*;

public class FlyingBall {

    private int xPosition;
    private int yPosition;
    private int speed;
    private Color color;
    private FlyingBallThread flyingBallThread;

    public FlyingBall() throws InterruptedException {

        yPosition = 50;
        speed = 10;
        color = Color.BLUE;
        flyingBallThread = new FlyingBallThread();
    }

    public class FlyingBallThread extends Thread {
        @Override
        public void run() {
            System.out.println("Running " + Thread.currentThread().getName() + " " + getClass().getSimpleName());

            try {
                startFlyingBall();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startFlyingBall() throws InterruptedException {
        while (true) {
            flyRight();
            flyLeft();
        }
    }

    private void flyLeft() throws InterruptedException {
        while (xPosition > 0) {
            xPosition--;
            flyingBallThread.sleep(speed);
        }
    }

    private void flyRight() throws InterruptedException {
        while (xPosition < 370) {
            xPosition++;
            flyingBallThread.sleep(speed);
        }
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public FlyingBallThread getFlyingBallThread() {
        return flyingBallThread;
    }

    public void setFlyingBallThread(FlyingBallThread flyingBallThread) {
        this.flyingBallThread = flyingBallThread;
    }
}
