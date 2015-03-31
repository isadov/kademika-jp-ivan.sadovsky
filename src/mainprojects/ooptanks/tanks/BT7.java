package mainprojects.ooptanks.tanks;

import mainprojects.ooptanks.fieldsobject.BFObject;
import mainprojects.ooptanks.fieldsobject.Brick;
import mainprojects.ooptanks.fieldsobject.Eagle;
import mainprojects.ooptanks.fieldsobject.Rock;
import mainprojects.ooptanks.serviceclass.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BT7 extends AbstractTank {

	public BT7() {
		super();
	}

	public BT7(BattleField bf, int x, int y, Direction direction) {
		super(bf, x, y, direction);

		this.colorTank = Color.BLACK;
		this.colorTower = Color.BLUE;
		this.maxSpeed = 1; //12
		this.action.setPointer(this);
		this.action.setNextAct(null);
		this.action.setActionResult(true);

		this.bf.setFieldObject(y / 64, x / 64, this);
		this.imagesOfTank = new HashMap<Direction, BufferedImage>();

		try {
			this.imagesOfTank.put(Direction.UP,
					ImageIO.read(new File("tanks_image/GreenTankUp.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			this.imagesOfTank.put(Direction.RIGHT,
					ImageIO.read(new File("tanks_image/GreenTankRight.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			this.imagesOfTank.put(Direction.DOWN,
					ImageIO.read(new File("tanks_image/GreenTankDown.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			this.imagesOfTank.put(Direction.LEFT,
					ImageIO.read(new File("tanks_image/GreenTankLeft.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public BT7(ActionField af, BattleField bf) {
		super(bf);

	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Speed of BT7 is " + maxSpeed;
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
		int V = this.y / 64;
		int H = this.x / 64;

		switch (this.direction) {
		case UP:
			enemyFound = checkLine(V, -1, H, 0);
			break;
		case DOWN:
			enemyFound = checkLine(V, 1, H, 0);
			break;
		case LEFT:
			enemyFound = checkLine(V, 0, H, -1);
			break;
		case RIGHT:
			enemyFound = checkLine(V, 0, H, 1);
			break;
		}
		return enemyFound;
	}

	private boolean checkLine(int V, int stepV, int H, int stepH) {
		boolean isEnemy = false;

		BFObject tmp = null;

		do {
			tmp = this.bf.getNextFieldObject(V, H, this.getDirection());
			if (tmp instanceof Rock) {
				break;
			}
			if (tmp instanceof T34 || tmp instanceof Brick
					|| tmp instanceof Eagle) {
				isEnemy = true;
				break;
			}
			V += stepV;
			H += stepH;
		} while (tmp != null);

		return isEnemy;
	}

}