package agmon.invaders.sprite;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

import agmon.invaders.Common;
import agmon.invaders.sprite.Entity.EntityState;

public class AlienBullet extends ShapeSprite {

	private static final int WIDTH = 5;

	public AlienBullet(int x, int y) {
		super(new Rectangle(x - WIDTH / 2, y - 10, WIDTH, 10), Color.green);
	}

	@Override
	public void update(long delta) {
		if (delta > 10) {
			setY((int) (getY() + delta / 3));
		} else {
			setY((int) (getY() + delta));

		}
		if (getY() > Common.HEIGHT - 50) {
			setState(EntityState.DEAD);
		}

	}

}
