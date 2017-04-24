package screens;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

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
import entities.TeleportTile;

public class Dungeon2 extends BasicGameState {

	private Random rand;
	private Player player;
	private Image dungeon1_map, player_img, fireball, exit, ogre_img, behemoth_img, ogre_dead, behemoth_dead, teleport_tile, toilet;
	private ArrayList<FireBall> fireballs;
	private ArrayList<Enemy> enemies;
	private ArrayList<TeleportTile> tiles;
	
	public Dungeon2(int westScreen) {
		rand = new Random();
		player = WorldMap.getPlayer();
		fireballs = new ArrayList<>();
		fireballs.add(new FireBall(900, player.getY(), 20, 20, 0));
		enemies = new ArrayList<>();
		enemies.add(new Enemy(400, 399, 120, 120, 80, "toilet"));
		tiles = new ArrayList<>();
		tiles.add(new TeleportTile(600, 600));
		tiles.add(new TeleportTile(600, 100));
		tiles.add(new TeleportTile(400, 200));
	}

	public void enter(GameContainer gc, StateBasedGame sbg) {
		player.setLastScreen("dungeon2");
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
		ogre_dead = new Image("res/ogre_dead.png");
		teleport_tile = new Image("res/teleport_tile.png");
		toilet = new Image("res/toilet.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		dungeon1_map.draw(0, 0);
		exit.draw(50, 100);
		
		for (TeleportTile t : tiles) {
			teleport_tile.draw(t.getX(), t.getY());
		}
		
		for (FireBall f : fireballs) {
			fireball.draw(f.getX(), f.getY());
		}
		
		for (Enemy e : enemies) {
			if (e.isAlive()) {
				if (e.getName().equals("ogre"))
					ogre_img.draw(e.getX(), e.getY());
				else if (e.getName().equals("behemoth"))
					behemoth_img.draw(e.getX(), e.getY());
				else if (e.getName().equals("toilet"))
					toilet.draw(e.getX(), e.getY());
			} else {
				if (e.getName().equals("ogre"))
					ogre_dead.draw(e.getX(), e.getY());
				else if (e.getName().equals("behemoth"))
					behemoth_dead.draw(e.getX(), e.getY());
			}
		}
		
		player_img.draw(player.getX(), player.getY());
		g.setColor(Color.white);
		g.drawString(Float.toString(player.getHealth()), 0, 570);
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
		} 
		
		for (FireBall f : fireballs) {
			f.update();
			for (Enemy e : enemies) {				
				if (collide(f, e) && e.isAlive()) {
					e.takeDamage(f.getDamage());
					f.setX(6000);
				}
			}
		}
		
		for (Enemy e : enemies) {
			if (e.getHealth() <= 0)
				e.setAlive(false);
			if (e.isAlive()) {
				e.getLocation();
				e.follow();
			}
			if (collide(e, player) && e.isAlive()) {
				player.takeDamage(.01f);
			}
		}
		
		for (int i = 0; i < fireballs.size()-1; i++) {
			if (fireballs.get(i).getX() > 800 || fireballs.get(i).getX() < 0 || fireballs.get(i).getY() > 600 || fireballs.get(i).getY() < 0)
				fireballs.remove(i);
		}
		
		if (player.getX() > 50 && player.getX() < 130 && player.getY() > 100 && player.getY() < 140 && input.isKeyPressed(input.KEY_E))
			sbg.enterState(2);
		
		for (TeleportTile t : tiles) {
			if (t.collide(player)) {
				player.setX(rand.nextInt(760));
				player.setY(rand.nextInt(560));
			}
		}
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
		return 9;
	}

}