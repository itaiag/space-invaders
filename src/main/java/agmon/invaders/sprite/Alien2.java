package agmon.invaders.sprite;

import org.newdawn.slick.SlickException;

public class Alien2 extends Alien {

	public Alien2(int x, int y) throws SlickException {
		super(x, y, 16, 9, 17, 9);
	}

	@Override
	public int getScore() {
		return 2;
	}

}
