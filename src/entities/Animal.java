package entities;

import java.util.Random;

public class Animal {
	
	private int x, y, width, height;
	boolean picked = false;
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
	
}
