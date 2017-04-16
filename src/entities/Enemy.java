package entities;

import java.awt.Rectangle;

public class Enemy {

	private int x, y, width, height, health;

	public Enemy(int x, int y, int width, int height, int health) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.health = 4;
	}

	//Getters and Setters
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void takeDamage(int dmg) {
		this.health -= dmg;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public Rectangle bounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
}
