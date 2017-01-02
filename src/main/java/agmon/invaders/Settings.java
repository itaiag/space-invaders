package agmon.invaders;

/**
 * @author itai
 *
 */
public class Settings {

	/**
	 * @author itai
	 *
	 */
	public enum Control {
		MOUSE("Mouse"), KEYBOARD("Keyboard"), MOUSE_AND_KEYBOARD("Mouse and Keyboard");

		private final String value;

		private Control(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

	public enum Difficulty {
		EASY("Easy"), HARD("Hard");

		private final String value;

		private Difficulty(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

	private Control control = Control.KEYBOARD;

	private Difficulty difficulty = Difficulty.HARD;

	private static Settings instance;

	private Settings() {

	}

	public static Settings getInstance() {
		if (null == instance) {
			instance = new Settings();
		}
		return instance;
	}

	public Control getControl() {
		return control;
	}

	public void setControl(Control control) {
		this.control = control;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

}
