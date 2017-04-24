package screens;

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

public class WestScreen extends BasicGameState {

	private Player player;
	private Image westScreen, player_img, fireball, d1_entrance, d2_entrance, ogre_img, ogre_dead;
	private ArrayList<FireBall> fireballs;
	private ArrayList<Enemy> enemies;
	
	public WestScreen(int westScreen) {
		player = WorldMap.getPlayer();
		fireballs = new ArrayList<>();
		enemies = new ArrayList<>();
		enemies.add(new Enemy(10, 20, 40, 40, 8, "ogre"));
	}

	public void enter(GameContainer gc, StateBasedGame sbg) {
		if (player.getLastScreen().equals("hub"))
			player.setX(760);
		else if (player.getLastScreen().equals("dungeon2")) {
			player.setX(170);
			player.setY(220);
		}
		
		player.setLastScreen("west");
	}
	
	public void leave(GameContainer gc, StateBasedGame sbg) {
		fireballs.clear();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		westScreen = new Image("res/west.png");
		player_img = new Image("res/player.png");
		fireball = new Image("res/fireball.png");
		d1_entrance = new Image("res/d1_entrance.png");
		d2_entrance = new Image("res/d1_entrance.png");
		ogre_img = new Image("res/ogre.png");
		ogre_dead = new Image("res/ogre_dead.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		westScreen.draw(0, 0);
		d1_entrance.draw(50, 100); //120 x 60
		d2_entrance.draw(150, 200);
		player_img.draw(player.getX(), player.getY());
		
		for (FireBall f : fireballs) {
			fireball.draw(f.getX(), f.getY());
		}
		
		for (Enemy e : enemies) {
			if (e.isAlive())
				ogre_img.draw(e.getX(), e.getY());
			else 
				ogre_dead.draw(e.getX(), e.getY());
		}
		
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
		} else if (input.isKeyPressed(input.KEY_H))
			sbg.enterState(7);
		else if (input.isKeyPressed(input.KEY_I))
			sbg.enterState(8);
		
		for (FireBall f : fireballs) {
			f.update();
			for (Enemy e : enemies) {
				if (e.collide(f) && e.isAlive()) {
					e.takeDamage(f.getDamage());
					f.setX(543433);
				}
			}
		}
		
		for (Enemy e : enemies) {
			if (e.isAlive()) {
				e.getLocation();
				e.follow();
			}
			
			if (e.collide(player))
				player.takeDamage(.02f);
			
			if (e.getHealth() <= 0)
				e.setAlive(false);
		}
		
		if (player.getX() > 800)
			sbg.enterState(1);
		if (player.getX() > 50 && player.getX() < 170)
			if (player.getY() > 100 && player.getY() + player.getHeight() < 160 && input.isKeyPressed(input.KEY_E))
				sbg.enterState(6);
		if (player.getX() > 150 && player.getX() < 270)
			if (player.getY() > 200 && player.getY() + player.getHeight() < 260 && input.isKeyPressed(input.KEY_E))
				sbg.enterState(9);
		
		if (player.getHealth() <= 0)
			sbg.enterState(10);
	}

	@Override
	public int getID() {
		return 2;
	}

}
