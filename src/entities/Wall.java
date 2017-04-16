package entities;

import java.awt.Rectangle;

public class Wall {
	
	private int x, y, width, height;

	public Wall(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
	
}
