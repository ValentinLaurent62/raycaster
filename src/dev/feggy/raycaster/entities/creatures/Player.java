package dev.feggy.raycaster.entities.creatures;

import java.awt.Graphics;

import dev.feggy.raycaster.Handler;
import dev.feggy.raycaster.entities.Entity;
import dev.feggy.raycaster.sfx.Sounds;
import dev.feggy.raycaster.weapons.Gun;

public class Player extends Creature {
	
	private double dirX;
	private double dirY;
	
	// 2D raycaster version of camera plane
	private double planeX = 0;
	private double planeY = 0.66;
	
	// Rotation speed
	private float rotSpeed;
	
	// Gun
	private Gun gun;
	private boolean canShoot = true;
	private int gunTimer = 0;
	
	// Constructor
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, null);
		dirX = -1;
		dirY = 0;
		rotSpeed = 0.05f;
		gun = new Gun(handler);
		health = 100;
	}

	// Updates
	@Override
	public void tick() {
		if (health > 0) {
		
			// Movement
			move();
			
			// Attacking
			if (handler.getKeyManager().ctrl && canShoot) {
				attack();
				canShoot = false;
				handler.setUIFrame(1);
			}
			
			if (!canShoot) {
				gunTimer++;
				if (gunTimer == 4)
					handler.setUIFrame(2);
				if (gunTimer >= 10) {
					gunTimer = 0;
					canShoot = true;
					handler.setUIFrame(0);
				}
			}
		
		}
		
		if (handler.getKeyManager().escape)
			handler.getGame().closeGame();
	}
	
	// Attacking
	protected void attack() {
		Sounds.gunFire.play();
		Entity obj = gun.shoot(this, x, y, dirX, dirY);
		if (obj != null)
			obj.hurt(5 * handler.randomInt(1, 3));
	}
	
	// Input
	@Override
	protected void move() {
		
		// Hold SHIFT to run
		if (handler.getKeyManager().shift) {
			speed = 6.0f;
			rotSpeed = 0.08f;
		}
		else {
			speed = 3.0f;
			rotSpeed = 0.05f;
		}
		
		// Run forwards
		if (handler.getKeyManager().up){
			if (handler.getWorld().getSquare((int)((x + dirX * speed + radius * Math.signum(dirX))/64), (int)(y/64)) <= 0
					&& !checkEntityCollisions((float)(x + dirX * speed), y) )
				x += dirX * speed;
			if (handler.getWorld().getSquare((int)(x/64), (int)((y + dirY * speed + radius * Math.signum(dirY))/64)) <= 0
					&& !checkEntityCollisions(x, (float)(y + dirY * speed)) )	
				y += dirY * speed;
		}
		
		// Run backwards
		if (handler.getKeyManager().down){
			if (handler.getWorld().getSquare((int)((x - dirX * speed - radius * Math.signum(dirX))/64), (int)(y/64)) <= 0
					&& !checkEntityCollisions((float)(x - dirX * speed), y) )
				x -= dirX * speed;
			if (handler.getWorld().getSquare((int)(x/64), (int)((y - dirY * speed - radius * Math.signum(dirY))/64)) <= 0
					&& !checkEntityCollisions(x, (float)(y - dirY * speed)) )	
				y -= dirY * speed;
		}
		
		// Hold ALT to strafe
		if (handler.getKeyManager().alt) {
			double strafeX = dirY;
			double strafeY = dirX;
			if (Math.abs(strafeX) > Math.abs(strafeY))
				strafeY = 0;
			else
				strafeX = 0;
			
			// Strafe right
			if (handler.getKeyManager().left){
				if (handler.getWorld().getSquare((int)((x - strafeX * speed - radius * Math.signum(strafeX))/64), (int)(y/64)) <= 0
						&& !checkEntityCollisions((float)(x - strafeX * speed), y) )
					x -= strafeX * speed;
				if (handler.getWorld().getSquare((int)(x/64), (int)((y + strafeY * speed + radius * Math.signum(strafeY))/64)) <= 0
						&& !checkEntityCollisions(x, (float)(y + strafeY * speed)) )	
					y += strafeY * speed;
			}
			
			// Strafe left
			if (handler.getKeyManager().right){
				if (handler.getWorld().getSquare((int)((x + strafeX * speed + radius * Math.signum(strafeX))/64), (int)(y/64)) <= 0
						&& !checkEntityCollisions((float)(x + strafeX * speed), y) )
					x += strafeX * speed;
				if (handler.getWorld().getSquare((int)(x/64), (int)((y - strafeY * speed - radius * Math.signum(strafeY))/64)) <= 0
						&& !checkEntityCollisions(x, (float)(y - strafeY * speed)) )
					y -= strafeY * speed;
			}
		}
		
		// Otherwise, turn around
		else {
			// Turn right
			if (handler.getKeyManager().right){
				// Both camera direction and camera plane must be rotated
				double oldDirX = dirX;
				dirX = dirX * Math.cos(-rotSpeed) - dirY * Math.sin(-rotSpeed);
				dirY = oldDirX * Math.sin(-rotSpeed) + dirY * Math.cos(-rotSpeed);
				double oldPlaneX = planeX;
				planeX = planeX * Math.cos(-rotSpeed) - planeY * Math.sin(-rotSpeed);
				planeY = oldPlaneX * Math.sin(-rotSpeed) + planeY * Math.cos(-rotSpeed);
			}
			
			// Turn left
			if (handler.getKeyManager().left){
				// Both camera direction and camera plane must be rotated
				double oldDirX = dirX;
				dirX = dirX * Math.cos(rotSpeed) - dirY * Math.sin(rotSpeed);
				dirY = oldDirX * Math.sin(rotSpeed) + dirY * Math.cos(rotSpeed);
				double oldPlaneX = planeX;
				planeX = planeX * Math.cos(rotSpeed) - planeY * Math.sin(rotSpeed);
				planeY = oldPlaneX * Math.sin(rotSpeed) + planeY * Math.cos(rotSpeed);
			}
		}

	}
	
	@Override
	public void hurt(int damage) {
		health -= damage;
	}

	// Draw the player
	@Override
	public void render(Graphics g) {
		
	}
	
	// Getters Setters
	
	public double getDirX() {
		return dirX;
	}
	
	public double getDirY() {
		return dirY;
	}
	
	public double getPlaneX() {
		return planeX;
	}
	
	public double getPlaneY() {
		return planeY;
	}
	
	public void setDirX(double dirX) {
		this.dirX = dirX;
	}
	
	public void setDirY(double dirY) {
		this.dirY = dirY;
	}
	
	public void setPlaneX(double planeX) {
		this.planeX = planeX;
	}
	
	public void setPlaneY(double planeY) {
		this.planeY = planeY;
	}

}
