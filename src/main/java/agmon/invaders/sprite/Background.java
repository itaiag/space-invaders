package agmon.invaders.sprite;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import agmon.invaders.Common;
import agmon.invaders.EntityStore;
import agmon.invaders.GameState;
import agmon.invaders.GameState.State;

public class Background implements Drawable, Updatable {

	private final GameState gameState;


	public Background(GameState gameState) {
		this.gameState = gameState;
	}

	@Override
	public void update(long delta) {

	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.drawLine(0f, (float) (Common.HEIGHT * 0.95), (float) Common.WIDTH, (float) (Common.HEIGHT * 0.95));
		g.setColor(Common.DEFAULT_COLOR);
		g.drawString("Score: " + gameState.getScore(), 0, Common.HEIGHT - 20);

		if (gameState.getState() == State.LOST) {
			g.drawString("Game over man, game over", Common.WIDTH / 2 - 150, Common.HEIGHT / 2);
		} else if (gameState.getState() == State.WON) {
			g.drawString("You win", Common.WIDTH / 2 - 150, Common.HEIGHT / 2);
		}
	}

}
