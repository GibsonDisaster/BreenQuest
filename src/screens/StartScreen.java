package screens;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Player;

public class StartScreen extends BasicGameState {
	
	private Image play, exit, pointer, breen;
	private int place = 2;
	private String currentSelection = "play";

	public StartScreen(int startMenu) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		play = new Image("res/play.png");
		exit = new Image("res/exit.png");
		pointer = new Image("res/pointer.png");
		breen = new Image("res/breen.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		breen.draw(200, 50);
		play.draw(280, 300);
		exit.draw(280, 430);
		
		if (currentSelection.equals("play"))
			pointer.draw(500, 300);
		else
			pointer.draw(500, 430);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyPressed(input.KEY_UP)) {
			place++;
		} else if (input.isKeyPressed(input.KEY_DOWN)) {
			place--;
		} else if (input.isKeyPressed(input.KEY_ENTER)) {
			doAction(currentSelection, sbg);
		}
		
		if (place % 2 == 0)
			currentSelection = "play";
		else if (place % 2 != 0)
			currentSelection = "exit";
	}
	
	public void doAction(String cs, StateBasedGame sbg) {
		if (cs.equals("play")) {
			sbg.enterState(1);
		} else if (cs.equals("exit")) {
			System.exit(0);
		}
	}

	@Override
	public int getID() {
		return 0;
	}

}
