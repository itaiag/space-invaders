package agmon.invaders.sprite;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import agmon.invaders.Common;

public class ShapeSprite extends Entity {

	private Color color;
	
	public ShapeSprite(Rectangle bounds, Color color) {
		super(bounds);
		this.color = color;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.draw(getBounds());
		g.fill(getBounds());
		g.setColor(Common.DEFAULT_COLOR);
	}

}
