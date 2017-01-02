package agmon.invaders.state;

import org.newdawn.slick.state.StateBasedGame;

import agmon.invaders.Settings;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.DropDown;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.slick2d.NiftyBasicGameState;

public class SettingsState extends NiftyBasicGameState implements ScreenController {

	private static final String CONTROL_DD = "controlDropDown";
	private static final String DIFFICULTY_DD = "difficultyDropDown";
	private final int state;
	private SettingsContoller controller;

	public SettingsState(int state) {
		super("settings");
		this.state = state;
	}

	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame game) {
		controller = new SettingsContoller(game);
		getNifty().registerScreenController(controller);
		nifty.fromXml("controls/settings.xml", "settings");
		Screen screen = getNifty().getScreen("settings");
		addUiElements(screen);
	}

	@SuppressWarnings("unchecked")
	private void addUiElements(Screen screen) {
		DropDown<String> dd = screen.findNiftyControl(CONTROL_DD, DropDown.class);
		dd.addItem(Settings.Control.MOUSE.getValue());
		dd.addItem(Settings.Control.KEYBOARD.getValue());
		dd.addItem(Settings.Control.MOUSE_AND_KEYBOARD.getValue());
		dd.selectItem(Settings.getInstance().getControl().getValue());

		dd = screen.findNiftyControl(DIFFICULTY_DD, DropDown.class);
		dd.addItem(Settings.Difficulty.EASY.getValue());
		dd.addItem(Settings.Difficulty.HARD.getValue());
		dd.selectItem(Settings.getInstance().getDifficulty().getValue());
	}


	@Override
	public int getID() {
		return state;
	}

	@Override
	public void bind(Nifty nifty, Screen screen) {
	}

	@Override
	public void onStartScreen() {
	}

	@Override
	public void onEndScreen() {
	}

}
