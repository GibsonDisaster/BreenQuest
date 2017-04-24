package screens;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Enemy;
import entities.FireBall;
import entities.Player;

public class Dungeon1 extends BasicGameState {

	private Player player;
	private Image dungeon1_map, player_img, fireball, exit, ogre_img, behemoth_img, ogre_dead, behemoth_dead;
	private ArrayList<FireBall> fireballs;
	private ArrayList<Enemy> enemies;
	private Color one, two, three;
	private boolean first, second, third;
	
	public Dungeon1(int westScreen) {
		player = WorldMap.getPlayer();
		fireballs = new ArrayList<>();
		fireballs.add(new FireBall(900, player.getY(), 20, 20, 0));
		enemies = new ArrayList<>();
		one = Color.blue;
		two = Color.blue;
		three = Color.darkGray;
	}

	public void enter(GameContainer gc, StateBasedGame sbg) {
		player.setLastScreen("dungeon1");
	}
	
	public void leave(GameContainer gc, StateBasedGame sbg) {
		fireballs.clear();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		dungeon1_map = new Image("res/dungeon1.png");
		player_img = new Image("res/player.png");
		fireball = new Image("res/fireball.png");
		exit = new Image("res/d1_exit.png");
		ogre_img = new Image("res/ogre.png");
		behemoth_img = new Image("res/behemoth.png");
		ogre_dead = new Image("res/ogre_dead.png");
		behemoth_dead = new Image("res/behemoth_dead.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		dungeon1_map.draw(0, 0);
		exit.draw(50, 100);
		
		for (FireBall f : fireballs) {
			fireball.draw(f.getX(), f.getY());
		}
		
		for (Enemy e : enemies) {
			if (e.isAlive()) {
				if (e.getName().equals("ogre"))
					ogre_img.draw(e.getX(), e.getY());
				else if (e.getName().equals("behemoth"))
					behemoth_img.draw(e.getX(), e.getY());
			} else {
				if (e.getName().equals("ogre"))
					ogre_dead.draw(e.getX(), e.getY());
				else if (e.getName().equals("behemoth"))
					behemoth_dead.draw(e.getX(), e.getY());
			}
		}
		
		if (third)
			thirdSwitch(g);
		
		g.setColor(one);
		g.fillRect(400, 200, 60, 60);
		
		g.setColor(two);
		g.fillRect(480, 200, 60, 60);
		
		g.setColor(three);
		g.fillRect(540, 200, 60, 60);
		
		player_img.draw(player.getX(), player.getY());
		g.setColor(Color.white);
		g.drawString("Health : " + Float.toString(player.getHealth()), 0, 570);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyDown(input.KEY_UP)) {
			player.move("up");
			player.setDir(0);
		} else if (input.isKeyDown(input.KEY_DOWN)) {
			player.move("down");
			player.setDir(1);
		} else if (input.isKeyDown(input.KEY_LEFT)) {
			player.move("left");
			player.setDir(2);
		} else if (input.isKeyDown(input.KEY_RIGHT)) {
			player.move("right");
			player.setDir(3);
		} else if (input.isKeyPressed(input.KEY_SPACE)) { 
			fireballs.add(new FireBall(player.getX(), player.getY(), 20, 20, player.getDir()));
		} else if (player.getX() > 400 && player.getX() < 460 && player.getY() > 200 && player.getY() < 260 && input.isKeyPressed(input.KEY_E) && !first)
			firstSwitch();
		else if (player.getX() > 480 && player.getX() < 540 && player.getY() > 200 && player.getY() < 260 && input.isKeyPressed(input.KEY_E) && !second)
			secondSwitch();
		else if (input.isKeyPressed(input.KEY_H))
			sbg.enterState(7);
		else if (input.isKeyPressed(input.KEY_I))
			sbg.enterState(8);
		
		for (FireBall f : fireballs) {
			f.update();
			for (Enemy e : enemies) {
				if (e.getName().equals("behemoth") && e.isAlive()  == false)
					third = true;
				
				if (collide(f, e) && e.isAlive()) {
					e.takeDamage(f.getDamage());
					f.setX(6000);
				}
				if (e.getHealth() <= 0)
					e.setAlive(false);
				System.out.println(e.getHealth());
			}
		}
		
		for (Enemy e : enemies) {
			if (e.isAlive()) {
				e.getLocation();
				e.follow();
			}
			if (collide(e, player) && e.isAlive()) {
				player.takeDamage(.01f);
			}
		}
		
		if (first && second)
			three = Color.green;
		
		for (int i = 0; i < fireballs.size()-1; i++) {
			if (fireballs.get(i).getX() > 800 || fireballs.get(i).getX() < 0 || fireballs.get(i).getY() > 600 || fireballs.get(i).getY() < 0)
				fireballs.remove(i);
		}
		
		if (player.getX() > 50 && player.getX() < 130 && player.getY() > 100 && player.getY() < 140 && input.isKeyPressed(input.KEY_E))
			sbg.enterState(2);
		
		if (player.getHealth() <= 0)
			sbg.enterState(10);
	}
	
	public void firstSwitch() {
		enemies.add(new Enemy(600, 100, 40, 40, 6, "ogre"));
		enemies.add(new Enemy(150, 300, 40, 40, 6, "ogre"));
		enemies.add(new Enemy(400, 500, 40, 40, 6, "ogre"));
		one = Color.red;
		first = true;
	}
	
	public void secondSwitch() {
		enemies.add(new Enemy(150, 300, 200, 200, 32, "behemoth"));
		two = Color.red;
		second = true;
	}
	
	public void thirdSwitch(Graphics g) {
		player.setScepter(true);
		g.drawString("You got the scepter", 140, 100);
	}
	
	public boolean collide(FireBall f, Enemy e) {
		Rectangle rect1 = f.bounds();
		Rectangle rect2 = e.bounds();
		
		if (rect1.intersects(rect2))
			return true;
		else
			return false;
	}
	
	public boolean collide(Enemy e, Player f) {
		Rectangle rect1 = f.bounds();
		Rectangle rect2 = e.bounds();
		
		if (rect1.intersects(rect2))
			return true;
		else
			return false;
	}

	@Override
	public int getID() {
		return 6;
	}

}