package agmon.invaders.state;

import org.newdawn.slick.state.StateBasedGame;

import agmon.invaders.Game;
import agmon.invaders.Settings;
import agmon.invaders.Settings.Control;
import agmon.invaders.Settings.Difficulty;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.CheckBoxStateChangedEvent;
import de.lessvoid.nifty.controls.DropDownSelectionChangedEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class SettingsContoller implements ScreenController {

	private final StateBasedGame game;

	public SettingsContoller(StateBasedGame game) {
		this.game = game;
	}

	@Override
	public void bind(Nifty nifty, Screen screen) {
	}

	public void okClick() {
	}

	@NiftyEventSubscriber(id = "checkBox")
	public void onAllCheckBoxChanged(final String id, final CheckBoxStateChangedEvent event) {
	}

	@NiftyEventSubscriber(id = "controlDropDown")
	public void controlDropDownChanged(final String id, final DropDownSelectionChangedEvent<String> event) {
		if (event.getSelection().equals(Control.KEYBOARD.getValue())) {
			Settings.getInstance().setControl(Control.KEYBOARD);
		} else if (event.getSelection().equals(Control.MOUSE.getValue())) {
			Settings.getInstance().setControl(Control.MOUSE);
		} else if (event.getSelection().equals(Control.MOUSE_AND_KEYBOARD.getValue())) {
			Settings.getInstance().setControl(Control.MOUSE_AND_KEYBOARD);
		}
	}

	@NiftyEventSubscriber(id = "difficultyDropDown")
	public void difficultyDropDownChanged(final String id, final DropDownSelectionChangedEvent<String> event) {
		if (event.getSelection().equals(Difficulty.EASY.getValue())) {
			Settings.getInstance().setDifficulty(Difficulty.EASY);
		} else if (event.getSelection().equals(Difficulty.HARD.getValue())) {
			Settings.getInstance().setDifficulty(Difficulty.HARD);
		}

	}

	@NiftyEventSubscriber(pattern = ".*Button")
	public void buttonClicked(final String id, final ButtonClickedEvent event) {
		game.enterState(Game.MENU);
	}

	@Override
	public void onStartScreen() {
	}

	@Override
	public void onEndScreen() {
	}

}
