package agmon.invaders;

import org.newdawn.slick.SlickException;

import agmon.invaders.EntityStore.EntityType;
import agmon.invaders.Settings.Difficulty;
import agmon.invaders.sprite.Alien0;
import agmon.invaders.sprite.Alien1;
import agmon.invaders.sprite.Alien2;
import agmon.invaders.sprite.Alien3;
import agmon.invaders.sprite.Alien4;
import agmon.invaders.sprite.ScoreImage;
import agmon.invaders.sprite.Ship;
import agmon.invaders.sprite.UFO;
import agmon.invaders.sprite.Wall;

public class EntityFactory {

	public static EntityStore create() throws SlickException {
		EntityStore entities = new EntityStore();
		entities.empty();
		entities.add(EntityType.SHIP, new Ship(Common.WIDTH / 2, Common.HEIGHT - 70));

		for (int i = 0; i < 3; i++) {
			entities.add(EntityType.SCORE, new ScoreImage(Common.WIDTH - 150 + (40 * i), Common.HEIGHT - 30));
		}
		int height = 50;
		if (Settings.getInstance().getDifficulty() == Difficulty.HARD) {
			for (int i = 0; i < Common.WIDTH; i += 50) {
				entities.add(EntityType.ALIEN, new Alien4(i, height));
			}

			height = 100;
			for (int i = 0; i < Common.WIDTH; i += 50) {
				entities.add(EntityType.ALIEN, new Alien3(i, height));

			}
			height = 150;
			for (int i = 0; i < Common.WIDTH; i += 50) {
				entities.add(EntityType.ALIEN, new Alien2(i, height));

			}
		}

		height = 200;
		boolean flipAlienType = false;
		for (int i = 0; i < Common.WIDTH; i += 50) {
			entities.add(EntityType.ALIEN, flipAlienType ? new Alien0(i, height) : new Alien1(i, height));
			flipAlienType = !flipAlienType;
		}
		entities.add(EntityType.WALL, new Wall((int) (Common.WIDTH * 0.2), (int) (Common.HEIGHT * 0.8)));
		entities.add(EntityType.WALL, new Wall((int) (Common.WIDTH * 0.4), (int) (Common.HEIGHT * 0.8)));
		entities.add(EntityType.WALL, new Wall((int) (Common.WIDTH * 0.6), (int) (Common.HEIGHT * 0.8)));
		entities.add(EntityType.WALL, new Wall((int) (Common.WIDTH * 0.8), (int) (Common.HEIGHT * 0.8)));
		entities.add(EntityType.UFO, new UFO(0, (int) (Common.HEIGHT * 0.05)));
		return entities;

	}
}
