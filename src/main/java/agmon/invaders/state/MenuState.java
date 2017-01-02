package agmon.invaders.state;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import agmon.invaders.Common;
import agmon.invaders.Game;
import agmon.invaders.sprite.Alien;
import agmon.invaders.sprite.Alien0;
import agmon.invaders.sprite.Alien1;
import agmon.invaders.sprite.Alien2;
import agmon.invaders.sprite.Alien3;
import agmon.invaders.sprite.Alien4;
import agmon.invaders.sprite.ExtendableMenuItemEntity;
import agmon.invaders.sprite.Ship;

public class MenuState extends BasicGameState {

	private final int state;

	private ExtendableMenuItemEntity startGame;

	private ExtendableMenuItemEntity settings;

	private Ship ship;

	private List<Alien> aliens;

	public MenuState(int state) {
		this.state = state;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Image startGameImage = new Image("images/start_game.png", false, Image.FILTER_NEAREST, Color.black);
		startGame = new ExtendableMenuItemEntity(new Rectangle((Common.WIDTH / 2) - (startGameImage.getWidth() / 2),
				Common.HEIGHT / 2 - 110, startGameImage.getWidth(), startGameImage.getHeight()), startGameImage);

		Image settingsImage = new Image("images/settings.png", false, Image.FILTER_NEAREST, Color.black);
		settings = new ExtendableMenuItemEntity(new Rectangle((Common.WIDTH / 2) - (settingsImage.getWidth() / 2),
				Common.HEIGHT / 2 + 10, settingsImage.getWidth(), settingsImage.getHeight()), settingsImage);

		ship = new Ship(Common.WIDTH / 2, Common.HEIGHT - 100);
		createAliens();
	}

	private void createAliens() throws SlickException {
		aliens = new ArrayList<Alien>();
		Alien alien = new Alien0(Common.WIDTH / 2 - 200, 100);
		aliens.add(alien);
		alien = new Alien1(Common.WIDTH / 2 - 100, 100);
		aliens.add(alien);
		alien = new Alien2(Common.WIDTH / 2, 100);
		aliens.add(alien);
		alien = new Alien3(Common.WIDTH / 2 + 100, 100);
		aliens.add(alien);
		alien = new Alien4(Common.WIDTH / 2 + 200, 100);
		aliens.add(alien);

		for (Alien curAlien : aliens) {
			curAlien.setAutoIncreaseSpeed(false);
			curAlien.setAutoDownLine(false);
		}

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		startGame.draw(g);
		settings.draw(g);
		ship.draw(g);
		for (Alien alien : aliens) {
			alien.draw(g);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		startGame.update(delta);
		settings.update(delta);
		ship.update(delta);
		for (Alien alien : aliens) {
			alien.update(delta);
		}
		ship.moveX(5);
		if (ship.getX() > (Common.WIDTH - ship.getBounds().getWidth() - 10)) {
			ship.setX(0);
		}
		if (startGame.isMouseHover()) {
			if (gc.getInput().isMouseButtonDown(0)) {
				sbg.getState(Game.PLAY).init(gc, sbg);
				sbg.enterState(Game.PLAY);
			}
		} else if (settings.isMouseHover()) {
			if (gc.getInput().isMouseButtonDown(0)) {
				sbg.enterState(Game.SETTINGS);
			}
		}
		if (gc.getInput().isKeyPressed(Input.KEY_S)) {
			sbg.enterState(Game.SETTINGS);
		}

		if (gc.getInput().isKeyPressed(Input.KEY_Q) || gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			System.exit(0);
		}

	}

	@Override
	public int getID() {
		return state;
	}

}
