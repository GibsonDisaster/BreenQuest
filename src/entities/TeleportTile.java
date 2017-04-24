package entities;

import java.awt.Rectangle;

public class TeleportTile {
	private int x, y, width, height;

	public TeleportTile(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 40;
	}
	
	public boolean collide(Player p) {
		Rectangle rect1 = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle rect2 = new Rectangle(p.getX(), p.getY(), p.getWidth(), p.getHeight());
		
		if (rect2.intersects(rect1))
			return true;
		else
			return false;
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

