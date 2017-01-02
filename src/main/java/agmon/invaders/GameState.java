package agmon.invaders;

public class GameState {

	public enum State {
		WON, LOST, ON_GOING
	}

	private State state = State.ON_GOING;

	private int score;

	public void addScore(int score) {
		this.score += score;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
