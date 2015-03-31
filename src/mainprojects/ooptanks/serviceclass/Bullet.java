package mainprojects.ooptanks.serviceclass;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet implements Drawable,Destroyable{
	private int x;
	private int y;
	private int speed = 5;
	private Direction direction;
	private boolean isDestroyed = false;
	
	public Bullet(int x, int y, Direction direction){
		   this.x = x; 
		   this.y = y;
		   this.direction = direction;
		   isDestroyed = true;
		}
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
		
	public int getSpeed() {
		return speed;
	}
		
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public void updateX(int x){
		this.x += x;
	}
	public void updateY(int y){
		this.y += y;
	}
	public void destroy(){
		x = -100;
		y = -100;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(255, 255, 0));
		g.fillRect(this.getX(), this.getY(), 14, 14);
		
	}
	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return isDestroyed;
	}
	
}
