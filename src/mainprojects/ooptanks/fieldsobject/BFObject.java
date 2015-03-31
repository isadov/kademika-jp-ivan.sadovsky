package mainprojects.ooptanks.fieldsobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import mainprojects.ooptanks.serviceclass.Drawable;

public class BFObject implements Drawable {
	protected int x;
	protected int y;
	protected Color color;
	protected Image image;

	@Override
	public void draw(Graphics g) {
		g.drawImage(this.image, this.getX(), this.getY(), 64, 64, null);
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

}