package dev.feggy.raycaster.entities.creatures;

import java.awt.image.BufferedImage;

import dev.feggy.raycaster.Handler;
import dev.feggy.raycaster.entities.Entity;

public abstract class Creature extends Entity {
	
	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 3.0f;
	
	protected int health;
	protected float speed;
	protected float xMove, yMove;

	// Constructor
	public Creature(Handler handler, float x, float y, BufferedImage sprite) {
		super(handler, x, y, sprite);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	// Movement stuff
	protected abstract void move();
	
	// GETTERS SETTERS

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	// Use this to get index of sprite, based on direction
	public int getDirIndex() {
		double angle = ( Math.atan2(handler.getWorld().getEntityManager().getPlayer().getY() - y, handler.getWorld().getEntityManager().getPlayer().getX() - x) );
		if (angle < 0)
			angle += Math.PI*2;
		else if (angle > Math.PI*2)
			angle -= Math.PI*2;
		return (int)( ( angle ) / (2*Math.PI) * 8);
	}

}
