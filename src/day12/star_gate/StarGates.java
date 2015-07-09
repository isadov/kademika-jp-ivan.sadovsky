package day12.star_gate;

import javax.swing.*;
import java.awt.*;

public class StarGates extends JPanel {

    private final static int WIDTH = 400;

    private Ship ship;
    private Gates gates;
    private volatile boolean isShipArrived;

    public StarGates() {
        JFrame frame = new JFrame("STARGATES");
        frame.setLocation(450, 150);
        frame.setMinimumSize(new Dimension(WIDTH, 400));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

        ship = new Ship();
        gates = new Gates();

        turnOnGates();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    moveShip();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        repaintWhileShipIsArrived();
        System.out.println("the end");
        frame.dispose();
    }

    private void repaintWhileShipIsArrived() {
        while (!isShipArrived) {
            repaint();
            sleep(1000 / 60);
        }
    }

    public void moveShip() throws InterruptedException {
        while (ship.getX() <= WIDTH - ship.getSize() * 2) {
            checkShipNearGates();
            checkShipCrossedGates();

            ship.setX(ship.getX() + 1);
            sleep(5);

        }
        isShipArrived = true;
    }

    private boolean isShipNearGates() {
        if(ship.getX() > (gates.getX() - ship.getSize() - 10)
                && ship.getX() < gates.getX()) {
            return true;
        }
        return false;
    }

    private boolean isShipCrossedGates() {
        if(ship.getX() == (gates.getX() + gates.getWidth() + 5)) {
            return true;
        }
        return false;
    }

    private void checkShipNearGates() throws InterruptedException {
        if(!gates.isOpen() && isShipNearGates()) {
            synchronized (gates) {
                System.out.println("Ship: Asked permissions to open gates");
                gates.notify();
            }
            synchronized (ship) {
                System.out.println("gates open");
                ship.wait();
            }
        }
    }

    private void checkShipCrossedGates() throws InterruptedException {
        if(gates.isOpen() && isShipCrossedGates()) {
            synchronized (gates) {
                gates.notify();
            }
            synchronized (ship) {
                System.out.println("Ship: Now I can fly further");
                ship.wait();
            }
        }
    }

    private void turnOnGates() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (gates) {
                        System.out.println("Gates: Waiting for other ships");
                        gates.wait();
                    }

                    while (!gates.isOpen()) {
                        animateGates();
                    }
                    synchronized (ship) {
                        ship.notify();
                    }
                    synchronized (gates) {
                        gates.wait();
                    }

                    while (gates.isOpen()) {
                        animateGates();
                    }
                    synchronized (gates) {
                        gates.wait();
                    }
                    synchronized (ship) {
                        ship.notify();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void animateGates() {
        if(!gates.isOpen()) {
            if(gates.getLength() != gates.getWidth()) {
                openGates();
            } else {
                notifyShipToCrossGates();
            }
        } else {
            if(gates.getLength() != 100) {
                closeGates();
            } else {
                notifyShipToFlyFurther();
            }
        }
    }

    public void openGates() {
        gates.setLength(gates.getLength() - 1);
        sleep(10);
    }

    private void sleep (int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void notifyShipToCrossGates() {
        gates.setIsOpen(true);
        System.out.println("Gates: Gates is Opened, please come in");
        synchronized (ship) {
            sleep(1000);
            ship.notify();
        }
    }

    private void closeGates() {
        gates.setLength(gates.getLength() + 1);
        sleep(10);
    }

    private void notifyShipToFlyFurther() {
        gates.setIsOpen(false);
        System.out.println("Gates: Gates is closed");
        synchronized (ship) {
            sleep(1000);
            ship.notify();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(ship.getColor());
        g.fillRect(ship.getX(), ship.getY(), ship.getSize(), ship.getSize());

        g.setColor(gates.getColor());
        g.fillRect(gates.getX(), gates.getY(), gates.getWidth(), gates.getLength());
    }

}
