package agmon.invaders.sprite;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Wall extends AnimationEntity {

	private static final int SPRITE_WIDTH = DEFAULT_SPRITE_WIDTH * 2;
	private static final int SPRITE_HEIGHT = 37;
	private int percentComplete = 100;

	private static final Animation COMPLETE = new Animation(getSpriteSheet(SPRITE_WIDTH, SPRITE_HEIGHT), 0, 9, 0, 9,
			true, DEFAULT_DURATION, true);

	private static final Animation _80_PERCENT = new Animation(getSpriteSheet(SPRITE_WIDTH, SPRITE_HEIGHT), 1, 9, 1, 9,
			true, DEFAULT_DURATION, true);

	private static final Animation _60_PERCENT = new Animation(getSpriteSheet(SPRITE_WIDTH, SPRITE_HEIGHT), 2, 9, 2, 9,
			true, DEFAULT_DURATION, true);

	private static final Animation _40_PERCENT = new Animation(getSpriteSheet(SPRITE_WIDTH, SPRITE_HEIGHT), 3, 9, 3, 9,
			true, DEFAULT_DURATION, true);

	private static final Animation _20_PERCENT = new Animation(getSpriteSheet(SPRITE_WIDTH, SPRITE_HEIGHT), 4, 9, 4, 9,
			true, DEFAULT_DURATION, true);

	public Wall(int x, int y) throws SlickException {
		super(new Rectangle(x, y, SPRITE_WIDTH, SPRITE_HEIGHT), COMPLETE);
	}

	public void setState(EntityState state) {
		super.setState(state);
		if (state == EntityState.DEAD) {
			return;
		}
		if (state == EntityState.EXPLODING) {
			percentComplete -= 20;
		}
		if (percentComplete == 80) {
			setAnimation(_80_PERCENT);
		} else if (percentComplete == 60) {
			setAnimation(_60_PERCENT);
		} else if (percentComplete == 40) {
			setAnimation(_40_PERCENT);
		} else if (percentComplete == 20) {
			setAnimation(_20_PERCENT);
		} else if (percentComplete <= 0) {
			super.setState(EntityState.DEAD);
		}

	}

}
