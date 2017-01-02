package agmon.invaders.sprite;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class ScoreImage extends AnimationEntity {

	public ScoreImage(int x, int y) throws SlickException {
		super(new Rectangle(x, y, DEFAULT_SPRITE_WIDTH, DEFAULT_SPRITE_HEIGHT),
				new Animation(getSpriteSheet(), 0, 10, 0, 10, true, DEFAULT_DURATION, true));
	}

}
