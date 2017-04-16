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
import entities.Wall;

public class WorldMap extends BasicGameState {
	
	private Image map, player_img, fire_img, ogre_img;
	private static Player player;
	private Enemy ogre;
	private ArrayList<FireBall> fireballs;

	public WorldMap(int playScreen) {
		player = new Player(360, 260, 40, 40, 0);
		ogre = new Enemy(100, 100, 40, 40, 4);
		fireballs = new ArrayList<>();
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) {
		if (player.getLastScreen().equals("west"))
			player.setX(0);
		else if (player.getLastScreen().equals("north"))
			player.setY(0);
		else if (player.getLastScreen().equals("east")) {
			player.setY(260);
			player.setX(760);
		} else if (player.getLastScreen().equals("south"))
			player.setY(560);
	}

	public void leave(GameContainer gc, StateBasedGame sbg) {
		fireballs.clear();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		map = new Image("res/worldMap.png");
		player_img = new Image("res/player.png");
		fire_img = new Image("res/fireball.png");
		ogre_img = new Image("res/ogre.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		map.draw(0, 0);
		g.setColor(Color.white);
		player_img.draw(player.getX(), player.getY());
		
		for (FireBall f : fireballs) {
			fire_img.draw(f.getX(), f.getY());
		}
		
		if (ogre.getHealth() > 0)
			ogre_img.draw(ogre.getX(), ogre.getY());
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
			if (collide(f, ogre)) {
				ogre.takeDamage(f.getDamage());
				f.setX(60000);
			}
		}
		
		if (player.getX() < 0)
			sbg.enterState(2);
		else if (player.getY() < 0)
			sbg.enterState(3);
		else if (player.getX()+player.getWidth() > 800)
			sbg.enterState(4);
		else if (player.getY() > 600)
			sbg.enterState(5);
	}
	
	public boolean collide(FireBall b, Enemy e) {
		Rectangle rect1 = b.bounds();
		Rectangle rect2 = e.bounds();
		
		if (rect1.intersects(rect2)) {
			return true;
		} else {
			return false;
		}
	}
	
	//Getters and Setters
	@Override
	public int getID() {
		return 1;
	}
	
	public static Player getPlayer() {
		return player;
	}

}
