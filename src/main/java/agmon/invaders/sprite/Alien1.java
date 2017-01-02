package agmon.invaders.sprite;

import org.newdawn.slick.SlickException;

public class Alien1 extends Alien {

	public Alien1(int x, int y) throws SlickException {
		super(x, y, 4, 8, 5, 8);
	}

	@Override
	public int getScore() {
		return 1;
	}

}
