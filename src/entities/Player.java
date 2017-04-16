package entities;

import java.awt.Rectangle;

public class Player {
	private int x, y, width, height, dir;
	private String lastScreen;

	public Player(int x, int y, int width, int height, int dir) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.lastScreen = "null";
		this.dir = dir;
	}
	
	public void move(String dir) {
		switch(dir) {
		case "up":
			this.y -= 10;
			break;
		case "down":
			this.y += 10;
			break;
		case "left":
			this.x -= 10;
			break;
		case "right":
			this.x += 10;
			break;
		}
	}
	
	public Rectangle bounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	//Getters and Setters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public String getLastScreen() {
		return this.lastScreen;
	}
	
	public void setLastScreen(String last) {
		this.lastScreen = last;
	}
	
	public void setDir(int dir) {
		this.dir = dir;
	}
	
	public int getDir() {
		return this.dir;
	}
	
}
