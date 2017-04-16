package screens;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Animal;
import entities.FireBall;
import entities.Player;

public class SouthScreen extends BasicGameState {

	private Player player;
	private Image south, player_img, sheep_img, fireball;
	private ArrayList<Animal> animals;
	private ArrayList<FireBall> fireballs;
	
	public SouthScreen(int westScreen) {
		player = WorldMap.getPlayer();
		animals = new ArrayList<>();
		fireballs = new ArrayList<>();
		animals.add(new Animal(400, 300, 80, 40));
		animals.add(new Animal(500, 100, 80, 40));
		animals.add(new Animal(100, 500, 80, 40));
	}

	public void enter(GameContainer gc, StateBasedGame sbg) {
		player.setLastScreen("south");
		player.setY(0);
	}
	
	public void leave(GameContainer gc, StateBasedGame sbg) {
		fireballs.clear();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		south = new Image("res/south.png");
		player_img = new Image("res/player.png");
		sheep_img = new Image("res/sheep.png");
		fireball = new Image("res/fireball.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		south.draw(0, 0);
		player_img.draw(player.getX(), player.getY());
		
		for (Animal a : animals) {
			sheep_img.draw(a.getX(), a.getY());
		}
		
		for (FireBall f : fireballs) {
			fireball.draw(f.getX(), f.getY());
		}
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
		}
		
		for (Animal a : animals) {
			a.pickRandLocation();
			a.update();
		}
		
		if (player.getY() < 0)
			sbg.enterState(1);
	}

	@Override
	public int getID() {
		return 5;
	}

}