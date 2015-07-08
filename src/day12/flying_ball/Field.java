package day12.flying_ball;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Field extends JPanel {

    private Set<FlyingBall> flyingBallsList;

    public Field() {
        JFrame frame = new JFrame("Flying Ball");
        frame.setLocation(500, 150);
        frame.setMinimumSize(new Dimension(400, 400));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

        flyingBallsList = new HashSet<>();
    }

    public void add(FlyingBall flyingBall) {
        flyingBallsList.add(flyingBall);
        flyingBall.getFlyingBallThread().start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (FlyingBall flyingBall : flyingBallsList) {
            g.setColor(flyingBall.getColor());
            g.fillOval(flyingBall.getxPosition(), flyingBall.getyPosition(), 20, 20);
        }
    }
}
