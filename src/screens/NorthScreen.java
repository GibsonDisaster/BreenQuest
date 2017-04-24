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

import entities.FireBall;
import entities.Player;

public class NorthScreen extends BasicGameState {

	private Player player;
	private Image north, player_img, fireball;
	private ArrayList<FireBall> fireballs;
	
	public NorthScreen(int westScreen) {
		player = WorldMap.getPlayer();
		fireballs = new ArrayList<>();
	}

	public void enter(GameContainer gc, StateBasedGame sbg) {
		player.setLastScreen("north");
		player.setY(560);
	}
	
	public void leave(GameContainer gc, StateBasedGame sbg) {
		fireballs.clear();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		north = new Image("res/north.png");
		player_img = new Image("res/player.png");
		fireball = new Image("res/fireball.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		north.draw(0, 0);
		player_img.draw(player.getX(), player.getY());
		
		for (FireBall f : fireballs) {
			fireball.draw(f.getX(), f.getY());
		}
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
		} else if (input.isKeyPressed(input.KEY_H))
			sbg.enterState(7);
		else if (input.isKeyPressed(input.KEY_I))
			sbg.enterState(8);
		
		for (FireBall f : fireballs) {
			f.update();
		}
		
		if (player.getY() > 600)
			sbg.enterState(1);
	}

	@Override
	public int getID() {
		return 3;
	}

}