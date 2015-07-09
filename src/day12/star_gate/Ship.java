package day12.star_gate;

import java.awt.*;

public class Ship {

    private int x;
    private int y;
    private int size;
    private Color color;

    public Ship() {
        color = Color.GREEN;
        x = 10;
        y = 120;
        size = 20;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
