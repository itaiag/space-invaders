package agmon.invaders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Graphics;

import agmon.invaders.sprite.Drawable;
import agmon.invaders.sprite.Entity;
import agmon.invaders.sprite.Updatable;
import agmon.invaders.sprite.Entity.EntityState;

public class EntityStore implements Updatable, Drawable {

	public enum EntityType {
		SHIP, ALIEN, SHIP_BULLET, ALIEN_BULLET, SCORE, WALL, UFO
	}

	private Map<EntityType, List<Entity>> entities;

	public EntityStore() {
		entities = new HashMap<EntityType, List<Entity>>();
	}

	public void add(EntityType type, Entity entity) {
		if (!entities.containsKey(type)) {
			List<Entity> spriteList = new ArrayList<Entity>();
			entities.put(type, spriteList);
		}
		entities.get(type).add(entity);

	}

	public void removeDeads() {
		for (List<Entity> entityList : entities.values()) {
			Iterator<Entity> i = entityList.iterator();
			while (i.hasNext()) {
				Entity entity = i.next();
				if (entity.getState() == EntityState.DEAD) {
					i.remove();
				}
			}
		}
	}

	public void empty() {
		entities = new HashMap<EntityType, List<Entity>>();
	}

	public List<Entity> getEntities() {
		List<Entity> result = new ArrayList<Entity>();
		for (List<Entity> entityList : entities.values()) {
			result.addAll(entityList);
		}
		return result;
	}

	public List<Entity> get(EntityType type) {
		final List<Entity> result = entities.get(type);
		if (result == null) {
			return new ArrayList<Entity>();
		}

		return result;
	}

	@Override
	public void draw(Graphics g) {
		for (Entity entity : getEntities()) {
			entity.draw(g);
		}
	}

	@Override
	public void update(long delta) {
		for (Entity sprite : getEntities()) {
			sprite.update(delta);
		}
	}

	public boolean isEmpty() {
		return entities.isEmpty();
	}

}
