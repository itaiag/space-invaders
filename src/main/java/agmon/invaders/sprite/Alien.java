package agmon.invaders.sprite;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import agmon.invaders.Common;

public abstract class Alien extends AnimationEntity {

	private enum Direction {
		RIGHT, LEFT
	}

	private static final long MAX_TIME_EXPLODING = 500;

	private final static int DISTANCE_BETWEEN_ROWS = 25;

	private int step = 1;

	private Direction direction = Direction.RIGHT;

	private long timeExploding;
	
	private boolean autoDownLine = true;
	
	private boolean autoIncreaseSpeed = true;

	public Alien(int x, int y, int x1, int y1, int x2, int y2) throws SlickException {
		super(new Rectangle(x, y, DEFAULT_SPRITE_WIDTH, DEFAULT_SPRITE_HEIGHT),
				new Animation(getSpriteSheet(), x1, y1, x2, y2, true, DEFAULT_DURATION, true));
	}


	@Override
	public void setState(EntityState state) {
		super.setState(state);
		if (state == EntityState.EXPLODING) {
			Animation animation = new Animation(getSpriteSheet(), 3, 17, 4, 17, true, DEFAULT_DURATION, true);
			setAnimation(animation);
		}
	}

	@Override
	public void update(long delta) {
		super.update(delta);
		if (getState() == EntityState.EXPLODING) {
			timeExploding += delta;
			if (timeExploding > MAX_TIME_EXPLODING) {
				setState(EntityState.DEAD);
			}
		}

		if (getState() != EntityState.ALIVE) {
			return;
		}
		if (direction == Direction.LEFT) {
			if (getX() > 10) {
				moveX(-step);
			} else {
				if (autoDownLine){
					moveY(DISTANCE_BETWEEN_ROWS);
					
				}
				direction = Direction.RIGHT;
			}

		} else if (direction == Direction.RIGHT) {
			if (getX() < (Common.WIDTH - getAnimation().getWidth() - 10)) {
				moveX(step);
			} else {
				if (autoDownLine){
					moveY(DISTANCE_BETWEEN_ROWS);
				}
				direction = Direction.LEFT;
			}
		}
		if (autoIncreaseSpeed && getTimeFromGameStart() > 10000) {
			step = (int) (getTimeFromGameStart() / 10000) + 1;
		}

	}

	public abstract int getScore();


	public boolean isAutoDownLine() {
		return autoDownLine;
	}


	public void setAutoDownLine(boolean autoDownLine) {
		this.autoDownLine = autoDownLine;
	}


	public boolean isAutoIncreaseSpeed() {
		return autoIncreaseSpeed;
	}


	public void setAutoIncreaseSpeed(boolean autoIncreaseSpeed) {
		this.autoIncreaseSpeed = autoIncreaseSpeed;
	}
	
	

}
