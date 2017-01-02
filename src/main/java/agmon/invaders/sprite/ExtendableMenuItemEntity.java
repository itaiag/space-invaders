package agmon.invaders.sprite;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import agmon.invaders.Common;

public class ExtendableMenuItemEntity extends Entity {

	private static final float MAX_SCALE = 1.1f;

	private static final float SCALE_STEP = 0.01f;

	private Image image;

	private float scale = 1f;

	public ExtendableMenuItemEntity(Rectangle bounds, Image image) {
		super(bounds);
		this.image = image;
	}

	@Override
	public void draw(Graphics g) {
		image.draw(getX(), getY(), scale);
	}

	@Override
	public void update(long delta) {
		super.update(delta);
		if (isMouseHover()) {
			if (scale < MAX_SCALE) {
				scale += SCALE_STEP;
				moveX(-2);
			}
		} else {
			if (scale > 1) {
				scale -= SCALE_STEP;
				moveX(2);
			}
		}
	}

	public boolean isMouseHover() {
		int normalizedMouseY = Common.HEIGHT - Mouse.getY();
		if (Mouse.getX() >= getX() && Mouse.getX() <= getX() + image.getWidth()) {
			if ((normalizedMouseY >= getY() && normalizedMouseY <= getY() + image.getHeight())) {
				return true;
			}
		}
		return false;
	}

}
