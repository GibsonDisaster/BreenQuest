package entities;

import java.awt.Rectangle;

import screens.WorldMap;

public class Enemy {

	private int x, y, width, height, health;
	Player player;
	int px, py = 0;
	private boolean alive;
	private String name;
	
	public Enemy(int x, int y, int width, int height, int health, String name) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.health = health;
		this.alive = true;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void getLocation() {
		player = WorldMap.getPlayer();
		px = player.getX();
		py = player.getY();
	}
	
	public void follow() {
		if (this.x != px) {
			if (this.x > px) {
				this.x -= 1;
			} else {
				this.x += 1;
			}
		} else if (this.y != py) {
			if (this.y > py) {
				this.y -= 1;
			} else {
				this.y += 1;
			}
		}
	}
	
	public boolean collide(FireBall f) {
		Rectangle r1 = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle r2 = new Rectangle(f.getX(), f.getY(), f.getWidth(), f.getHeight());
		
		if (r2.intersects(r1))
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
	
	public void takeDamage(int dmg) {
		this.health -= dmg;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public Rectangle bounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	public boolean isAlive() {
		return this.alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
}
