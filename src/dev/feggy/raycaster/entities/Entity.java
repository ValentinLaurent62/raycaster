package dev.feggy.raycaster.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.feggy.raycaster.Handler;
import dev.feggy.raycaster.entities.creatures.Player;

public abstract class Entity {
	
	protected Handler handler;
	protected float x, y;
	protected int radius;
	protected double direction;
	
	protected BufferedImage sprite;
	
	public Entity(Handler handler, float x, float y, BufferedImage sprite) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.radius = 32;
		this.sprite = sprite;
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this) || !e.isSolid())
				continue;
			if ( Math.sqrt((e.getY() - yOffset)*(e.getY() - yOffset) + (e.getX() - xOffset)*(e.getX() - xOffset)) < radius + e.getRadius() )
				return true;
		}
		return false;
	}
	
	public boolean isSolid() {
		return true;
	}
	
	public boolean isBright() {
		return false;
	}
	
	public abstract void hurt(int damage);
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}
	
	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	public Player getPlayer() {
		return handler.getWorld().getEntityManager().getPlayer();
	}

}
