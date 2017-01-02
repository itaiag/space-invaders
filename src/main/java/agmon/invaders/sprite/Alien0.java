package agmon.invaders.sprite;

import org.newdawn.slick.SlickException;

public class Alien0 extends Alien {

	public Alien0(int x, int y) throws SlickException {
		super(x, y, 10, 8, 11, 8);
	}

	@Override
	public int getScore() {
		return 1;
	}

}
