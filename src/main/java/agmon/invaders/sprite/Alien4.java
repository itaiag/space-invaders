package agmon.invaders.sprite;

import org.newdawn.slick.SlickException;

public class Alien4 extends Alien {

	public Alien4(int x, int y) throws SlickException {
		super(x, y, 15, 17, 16, 17);
	}

	@Override
	public int getScore() {
		return 4;
	}

}
