package screens;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Item;

public class ShopScreen extends BasicGameState {

	private ArrayList<Item> items;
	private Image axe;
	
	public ShopScreen(int e) {
		items = new ArrayList<>();
		items.add(new Item(20, 300, 20, 20, "Axe", false));
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		axe = new Image("res/axe.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, 800, 640);
		g.setColor(Color.black);
		
		for (Item i : items) {
			if (i.getName().equals("Axe"))
				g.drawString("Axe: ¢10", i.getX(), i.getY()-40);
				axe.draw(i.getX(), i.getY());
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return 11;
	}

}
