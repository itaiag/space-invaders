package agmon.invaders.sprite;

import org.newdawn.slick.SlickException;

public class Alien3 extends Alien {

	public Alien3(int x, int y) throws SlickException {
		super(x, y, 16, 1, 17, 1);
	}

	@Override
	public int getScore() {
		return 3;
	}

}
