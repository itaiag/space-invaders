package agmon.invaders.sprite;

import org.newdawn.slick.geom.Rectangle;

import agmon.invaders.Common;

public abstract class Entity implements Drawable, Updatable {

	public enum EntityState {
		ALIVE, EXPLODING, REVIVING, DEAD, HIDDEN
	}

	private long timeFromGameStart;

	private EntityState state = EntityState.ALIVE;

	private Rectangle bounds;

	public Entity(Rectangle bounds) {
		this.bounds = bounds;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getX() {
		return (int) getBounds().getX();
	}

	public int getY() {
		return (int) getBounds().getY();
	}

	public void setX(int x) {
		if (x < 0 || x > (Common.WIDTH - getBounds().getWidth())) {
			return;
		}
		getBounds().setLocation(x, getY());
	}

	public void setY(int y) {
		getBounds().setLocation(getX(), y);
	}

	public void setLocation(int x, int y) {
		getBounds().setLocation(x, y);
	}

	public void moveX(int x) {
		setX(getX() + x);
	}

	public void moveY(int y) {
		setY(getY() + y);
	}

	public EntityState getState() {
		return state;
	}

	public void setState(EntityState state) {
		this.state = state;
	}

	@Override
	public void update(long delta) {
		timeFromGameStart += delta;
	}

	protected long getTimeFromGameStart() {
		return timeFromGameStart;
	}

	public int getScore() {
		return 0;
	}

}
