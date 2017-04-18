package screens;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Player;

public class HelpScreen extends BasicGameState {
	
	private Image background;
	private Player player;

	public HelpScreen(int helpscreen) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("res/helpscreen.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw(0, 0);
		
		if (player.isScepter())
			g.fillRect(100, 100, 60, 60);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input i = gc.getInput();
		player = WorldMap.getPlayer();
		
		if (i.isKeyPressed(i.KEY_E)) {
			switch(player.getLastScreen()) {
				case "east":
					sbg.enterState(4);
					break;
				case "south":
					sbg.enterState(5);
					break;
				case "north":
					sbg.enterState(3);
					break;
				case "west":
					sbg.enterState(2);
					break;
				case "hub":
					sbg.enterState(1);
					break;
			}
		}
	}

	@Override
	public int getID() {
		return 7;
	}

}
