package agmon.invaders.sprite;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

public class ShipBullet extends ShapeSprite {

	private static final int WIDTH = 5;

	public ShipBullet(int x, int y) {
		super(new Rectangle(x - WIDTH / 2, y - 10, WIDTH, 10), Color.green);
	}

	@Override
	public void update(long delta) {
		setY((int)(getY() - delta));
		if (getY() < 0) {
			setState(EntityState.DEAD);
		}
	}

}
