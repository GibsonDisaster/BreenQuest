package entities;

import java.awt.Rectangle;
import java.util.Random;

public class Animal {
	
	private int x, y, width, height;
	boolean picked = false;
	boolean onFire = false;
	Random rand = new Random();
	int newX = this.x;
	int newY = this.y;
	
	public Animal(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void pickRandLocation() {
		if (!picked)
			newX = rand.nextInt(760) + 1;
		if (!picked)
			newY = rand.nextInt(560) + 1;
		picked = true;
	}
	
	public void update() {
		
		if (this.x != newX) {
			if (this.x > newX) {
				this.x -= 1;
			} else {
				this.x += 1;
			}
		} else if (this.y != newY) {
			if (this.y > newY) {
				this.y -= 1;
			} else {
				this.y += 1;
			}
		}else if (this.x == newX && this.y == newY) {
			picked = false;
		}
	}
	
	public boolean collide(FireBall f) {
		Rectangle rect1 = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle rect2 = f.bounds();
		
		if (rect2.intersects(rect1))
			return true;
		else
			return false;
	}

	//Getters and Setters
	public boolean isOnFire() {
		return this.onFire;
	}
	
	public void setOnFire(boolean f) {
		this.onFire = f;
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
	
}
