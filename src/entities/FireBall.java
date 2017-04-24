package entities;

import java.awt.Rectangle;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class FireBall {

	private int x, y, width, height, dir, dmg;
	
	public FireBall(int x, int y, int width, int height, int dir) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.dir = dir;
		this.dmg = 2;
	}
	
	public void update() {
		if (this.dir == 0)
			this.y -= 3;
		else if (this.dir == 1)
			this.y += 3;
		else if (this.dir == 2)
			this.x -= 3;
		else if (this.dir == 3)
			this.x += 3;
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

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}
	
	public Rectangle bounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	public void setDamage(int d) {
		this.dmg = d;
	}
	
	public int getDamage() {
		return this.dmg;
	}
	
}
