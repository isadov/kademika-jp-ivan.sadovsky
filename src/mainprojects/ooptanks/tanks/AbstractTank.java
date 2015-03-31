package mainprojects.ooptanks.tanks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.HashMap;

import mainprojects.ooptanks.fieldsobject.BFObject;
import mainprojects.ooptanks.serviceclass.Action;
import mainprojects.ooptanks.serviceclass.ActionField;
import mainprojects.ooptanks.serviceclass.BattleField;
import mainprojects.ooptanks.serviceclass.Direction;
import mainprojects.ooptanks.serviceclass.Drawable;

public abstract class AbstractTank extends BFObject implements Drawable, Serializable{
	protected Direction direction;
	protected int speed = 10;
	protected Color colorTank, colorTower;
	int maxSpeed;
	
	public BattleField bf;
	public Action action;
	protected String id;
	protected HashMap<Direction, BufferedImage> imagesOfTank;
	private ActionField af;
	private boolean destroyed = false;

	public AbstractTank() {
	}

	public AbstractTank(BattleField battle) {
		this(battle, 128, 512, Direction.UP);
	}

	public AbstractTank(BattleField bf, int x, int y, Direction direction) {

		this.direction = direction;
		this.x = x;
		this.y = y;
		this.bf = bf;
		this.action = new Action();
	}


	public abstract Action setup();

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void updateX(int x) {
		this.x += x;
	}

	public void updateY(int y) {
		this.y += y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void draw(Graphics g) {
		g.drawImage(this.imagesOfTank.get(this.getDirection()), this.getX(),
				this.getY(), 64, 64, null);

	}
}
