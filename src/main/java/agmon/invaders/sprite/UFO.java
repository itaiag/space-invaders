package agmon.invaders.sprite;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import agmon.invaders.Common;

public class UFO extends AnimationEntity {

	private enum Direction {
		LEFT, RIGHT
	}

	private Direction direction = Direction.LEFT;

	private final static Animation ALIVE_ANIMATION = new Animation(getSpriteSheet(), 14, 15, 16, 15, true,
			DEFAULT_DURATION + 200, true);

	public UFO(int x, int y) throws SlickException {
		super(new Rectangle(x, y, DEFAULT_SPRITE_WIDTH, DEFAULT_SPRITE_HEIGHT), ALIVE_ANIMATION);
		getAnimation().setPingPong(true);
	}

	@Override
	public void update(long delta) {
		super.update(delta);
		if (getX() <= 0) {
			direction = Direction.RIGHT;
		} else if (getX() >= Common.WIDTH - getBounds().getWidth()) {
			direction = Direction.LEFT;
		}
		int step = 1;
		switch (direction) {
		case LEFT:
			moveX((int) -step);
			break;
		case RIGHT:
			moveX((int) step);
			break;
		}

	}

	@Override
	public int getScore() {
		return 50;
	}

}
