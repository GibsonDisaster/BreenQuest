package entities;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Player {
	
	private int x, y, width, height, dir;
	private String lastScreen;
	private float health;
	private ArrayList<Item> backpack;
	private boolean scepter, stick, ruby, emerald, sword, shield;

	public Player(int x, int y, int width, int height, int dir) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.lastScreen = "hub";
		this.dir = dir;
		this.health = 10;
		backpack = new ArrayList<>();
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
	
	public float getHealth() {
		return this.health;
	}
	
	public void takeDamage(float d) {
		this.health -= d;
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

	public ArrayList<Item> getBackpack() {
		return backpack;
	}

	public void setBackpack(ArrayList<Item> backpack) {
		this.backpack = backpack;
	}

	public boolean isScepter() {
		return scepter;
	}

	public void setScepter(boolean scepter) {
		this.scepter = scepter;
	}

	public boolean isStick() {
		return stick;
	}

	public void setStick(boolean stick) {
		this.stick = stick;
	}

	public boolean isRuby() {
		return ruby;
	}

	public void setRuby(boolean ruby) {
		this.ruby = ruby;
	}

	public boolean isEmerald() {
		return emerald;
	}

	public void setEmerald(boolean emerald) {
		this.emerald = emerald;
	}

	public boolean isSword() {
		return sword;
	}

	public void setSword(boolean sword) {
		this.sword = sword;
	}

	public boolean isShield() {
		return shield;
	}

	public void setShield(boolean shield) {
		this.shield = shield;
	}

	public void setHealth(float health) {
		this.health = health;
	}
	
}
