package agmon.invaders.sprite;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 * @author itai
 *
 */
public abstract class AnimationEntity extends Entity {

	protected static final int DEFAULT_SPRITE_WIDTH = 34;
	protected static final int DEFAULT_SPRITE_HEIGHT = 22;
	protected static final int DEFAULT_DURATION = 400;

	private Animation animation;

	public AnimationEntity(Rectangle bounds, Animation animation) throws SlickException {
		super(bounds);
		this.animation = animation;
	}

	protected static SpriteSheet getSpriteSheet() {
		return getSpriteSheet(DEFAULT_SPRITE_WIDTH, DEFAULT_SPRITE_HEIGHT);
	}

	protected static SpriteSheet getSpriteSheet(int spriteWidth, int spriteHeight) {
		try {
			final SpriteSheet spriteSheet = new SpriteSheet("images/invaders.png", spriteWidth, spriteHeight,
					Color.black);
			return spriteSheet;
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void draw(Graphics g) {
		if (getState() == EntityState.HIDDEN) {
			return;
		}
		getAnimation().draw(getX(), getY(), Color.white);
	}

	@Override
	public void update(long delta) {
		super.update(delta);
		getAnimation().update(delta);
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

}
