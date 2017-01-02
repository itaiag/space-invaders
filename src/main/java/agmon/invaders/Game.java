package agmon.invaders;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import agmon.invaders.state.MenuState;
import agmon.invaders.state.PlayState;
import agmon.invaders.state.SettingsState;

public class Game extends StateBasedGame {

	private static final String GAME_NAME = "Space Invaders";
	public static final int MENU = 0;
	public static final int PLAY = 1;
	public static final int SETTINGS = 2;

	public Game(String gameName) {
		super(GAME_NAME);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new MenuState(MENU));
		addState(new PlayState(PLAY));
		addState(new SettingsState(SETTINGS));
		enterState(MENU);
	}

	public static void main(String[] args) {
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new Game(GAME_NAME));
			appgc.setDisplayMode(Common.WIDTH, Common.HEIGHT, false);
			appgc.setShowFPS(true);
			int interval = 25;
			appgc.setMaximumLogicUpdateInterval(interval);
			appgc.setMinimumLogicUpdateInterval(interval);
			appgc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
