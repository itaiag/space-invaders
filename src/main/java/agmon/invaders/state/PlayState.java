package agmon.invaders.state;

import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import agmon.invaders.Common;
import agmon.invaders.EntityFactory;
import agmon.invaders.EntityStore;
import agmon.invaders.GameState;
import agmon.invaders.Settings;
import agmon.invaders.EntityStore.EntityType;
import agmon.invaders.GameState.State;
import agmon.invaders.Settings.Control;
import agmon.invaders.sprite.AlienBullet;
import agmon.invaders.sprite.Background;
import agmon.invaders.sprite.Entity;
import agmon.invaders.sprite.Entity.EntityState;
import agmon.invaders.sprite.ShipBullet;

public class PlayState extends BasicGameState {

	private static final long TIME_BETWEEN_ALIEN_SHOOTS = 1000;
	private final int state;
	private GameState gameState;
	private Background background;
	private long lastShootTime;
	private EntityStore entities;

	public PlayState(int state) {
		this.state = state;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		entities = EntityFactory.create();
		gameState = new GameState();
		background = new Background(gameState);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		updateByInput(gc, sbg, delta);
		if (gameState.getState() == State.LOST) {
			return;
		}
		updateByTime(delta);
		collisionDetection();
		entities.removeDeads();

	}

	private void updateByInput(GameContainer gc, StateBasedGame sbg, int delta) {
		if (gc.getInput().isKeyPressed(Input.KEY_Q) || gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(0);
		}

		if (entities.get(EntityType.SHIP).isEmpty()) {
			return;
		}
		final Entity ship = entities.get(EntityType.SHIP).get(0);
		if (ship.getState() == EntityState.ALIVE) {
			if (Settings.getInstance().getControl() == Control.KEYBOARD
					|| Settings.getInstance().getControl() == Control.MOUSE_AND_KEYBOARD) {
//				int step = delta < 10 ? 1 : delta / 2;
				if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {
					ship.moveX(-10);
				}
				if (gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
					ship.moveX(10);
				}

			}
			if (Settings.getInstance().getControl() == Control.MOUSE
					|| Settings.getInstance().getControl() == Control.MOUSE_AND_KEYBOARD) {
				if (Mouse.getY() > 10 && Mouse.getY() < Common.HEIGHT - 10 && Mouse.getX() > 10
						&& Mouse.getX() < Common.WIDTH - 10) {
					if (ship.getX() - Mouse.getX() < -10) {
						ship.moveX(delta / 2);
					} else if (ship.getX() - Mouse.getX() > 10) {
						ship.moveX(-delta / 2);
					}
				}

			}
			if (gc.getInput().isKeyPressed(Input.KEY_SPACE) || gc.getInput().isKeyPressed(Input.KEY_UP)
					|| gc.getInput().isMousePressed(0)) {
				if (entities.get(EntityType.SHIP_BULLET).size() < 2) {
					entities.add(EntityType.SHIP_BULLET,
							new ShipBullet((int) ship.getBounds().getCenterX(), (int) ship.getBounds().getCenterY()));
				}
			}

		}

	}

	private void updateByTime(int delta) {
		entities.update(delta);
		Random random = new Random();
		lastShootTime += delta;
		int shooter = -1;
		List<Entity> aliens = entities.get(EntityType.ALIEN);
		if (!aliens.isEmpty()) {
			shooter = random.nextInt(aliens.size());
		}
		for (Entity alien : aliens) {
			alien.update(delta);
			if (lastShootTime > TIME_BETWEEN_ALIEN_SHOOTS && aliens.indexOf(alien) == shooter) {
				lastShootTime = 0;
				entities.add(EntityType.ALIEN_BULLET, new AlienBullet(alien.getX(), alien.getY()));
			}
		}
		final Entity ship = entities.get(EntityType.SHIP).get(0);
		final List<Entity> lives = entities.get(EntityType.SCORE);
		if (entities.get(EntityType.ALIEN).isEmpty()) {
			gameState.setState(State.WON);
			for (Entity ufo : entities.get(EntityType.UFO)) {
				ufo.setState(EntityState.DEAD);
			}

		} else if (ship.getState() == EntityState.REVIVING) {
			if (lives.size() > 0) {
				ship.setState(EntityState.ALIVE);
				lives.get(0).setState(EntityState.DEAD);
			} else {
				ship.setState(EntityState.DEAD);
				gameState.setState(State.LOST);
			}
		}

	}

	private void collisionDetection() {
		final Entity ship = entities.get(EntityType.SHIP).get(0);
		final List<Entity> aliens = entities.get(EntityType.ALIEN);
		for (Entity bullet : entities.get(EntityType.SHIP_BULLET)) {
			for (Entity alien : aliens) {
				if (bullet.getState() == EntityState.ALIVE && alien.getBounds().intersects(bullet.getBounds())) {
					bullet.setState(EntityState.DEAD);
					alien.setState(EntityState.EXPLODING);
					gameState.addScore(alien.getScore());
				}
			}
			for (Entity wall : entities.get(EntityType.WALL)) {
				if (bullet.getBounds().intersects(wall.getBounds())) {
					bullet.setState(EntityState.DEAD);
					wall.setState(EntityState.EXPLODING);
				}
			}
			for (Entity ufo : entities.get(EntityType.UFO)) {
				if (ufo.getBounds().intersects(bullet.getBounds())) {
					gameState.addScore(ufo.getScore());
					ufo.setState(EntityState.DEAD);
					bullet.setState(EntityState.DEAD);
				}
			}
		}
		if (ship.getState() == EntityState.ALIVE) {
			for (Entity alien : aliens) {
				if (alien.getBounds().intersects(ship.getBounds())) {
					ship.setState(EntityState.EXPLODING);
				}
				if (alien.getY() > ship.getY()) {
					gameState.setState(State.LOST);
				}
				for (Entity wall : entities.get(EntityType.WALL)) {
					if (alien.getBounds().intersects(wall.getBounds())) {
						wall.setState(EntityState.DEAD);
					}
				}

			}
			for (Entity bullet : entities.get(EntityType.ALIEN_BULLET)) {
				if (bullet.getBounds().intersects(ship.getBounds())) {
					ship.setState(EntityState.EXPLODING);
					bullet.setState(EntityState.DEAD);
				}
				for (Entity wall : entities.get(EntityType.WALL)) {
					if (bullet.getBounds().intersects(wall.getBounds())) {
						bullet.setState(EntityState.DEAD);
						wall.setState(EntityState.EXPLODING);
					}
				}
			}

		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw(g);
		entities.draw(g);
	}

	@Override
	public int getID() {
		return state;
	}

}
