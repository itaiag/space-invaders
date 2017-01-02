package agmon.invaders.sprite;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Ship extends AnimationEntity {

	private final static int DURATION = DEFAULT_DURATION + 200;
	private final static Animation ALIVE_ANIMATION = new Animation(getSpriteSheet(), 0, 10, 1, 10, true, DURATION,
			true);
	private final static Animation EXPLODING_ANIMATION = new Animation(getSpriteSheet(), 0, 17, 2, 17, true, DURATION,
			true);
	private final static Animation DEAD_ANIMATION = new Animation(getSpriteSheet(), 0, 17, 0, 17, true, DURATION, true);

	private static final long MAX_TIME_EXPLODING = 1000;

	private long timeExploding;

	public Ship(int x, int y) throws SlickException {
		super(new Rectangle(x, y, DEFAULT_SPRITE_WIDTH, DEFAULT_SPRITE_HEIGHT), ALIVE_ANIMATION);
	}

	@Override
	public void update(long delta) {
		super.update(delta);
		if (getState() == EntityState.EXPLODING) {
			timeExploding += delta;
			if (timeExploding > MAX_TIME_EXPLODING) {
				setState(EntityState.REVIVING);
			}
		} else {
			timeExploding = 0;
		}

	}

	@Override
	public void setState(EntityState state) {
		super.setState(state);
		if (getState() == EntityState.EXPLODING || getState() == EntityState.REVIVING) {
			setAnimation(EXPLODING_ANIMATION);
		} else if (getState() == EntityState.ALIVE) {
			setAnimation(ALIVE_ANIMATION);
		} else if (getState() == EntityState.DEAD) {
			setAnimation(DEAD_ANIMATION);
		}

	}

}
