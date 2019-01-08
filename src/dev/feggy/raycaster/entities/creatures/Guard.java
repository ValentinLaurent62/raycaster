package dev.feggy.raycaster.entities.creatures;

import java.awt.Graphics;

import dev.feggy.raycaster.Handler;
import dev.feggy.raycaster.entities.Entity;
import dev.feggy.raycaster.gfx.Assets;
import dev.feggy.raycaster.weapons.Gun;

public class Guard extends Creature {
	
	private byte moveTimer = 1;
	private int newXMove, newYMove;
	
	private byte painTimer = 0;
	
	private byte shootTimer = 0;
	private byte shootWait = (byte)(speed*handler.randomInt(2, 6));
	
	// 0 -> idle
	// 1 -> chase
	// 2 -> attack
	// 3 -> pain
	// 4 -> death
	private byte state = 0;
	
	// Gun
	private Gun gun;

	// Constructor
	public Guard(Handler handler, float x, float y) {
		super(handler, x, y, Assets.npcGuard_s[0]);
		speed = 1.0f;
		health = 20;
		gun = new Gun(handler);
		direction = Math.toRadians(0);
	}

	@Override
	protected void move() {
		
		// IDLE
		if (state == 0) {
			if (sprite == Assets.npcGuard_s[2])
				state = 1;
		}
		
		// CHASE
		if (state == 1) {
			shootTimer++;
			moveTimer--;
			if (moveTimer == 0) {
				newXMove = 0;
				newYMove = 0;
				if (Math.abs(y - handler.getWorld().getEntityManager().getPlayer().getY() ) < Math.abs(x - handler.getWorld().getEntityManager().getPlayer().getX() ) )
					newXMove = (int)(Math.signum(handler.getWorld().getEntityManager().getPlayer().getX() - x));
				else
					newYMove = (int)(Math.signum(handler.getWorld().getEntityManager().getPlayer().getY() - y));
				
				if (handler.getWorld().getSquare( (int)((x + 64*newXMove)/64), (int)y/64 ) > 0 || handler.getWorld().getSquare( (int)x/64, (int)((y + 64*newYMove)/64) ) > 0) {
					int oldXMove = newXMove;
					newXMove = newYMove;
					newYMove = oldXMove;
					if (handler.randomInt(0, 1) == 0) {
						newXMove = -newXMove;
						newYMove = -newYMove;
					}
				}
				
				if (newXMove == 1 && newYMove == 0) {
					direction = Math.toRadians(0);
				}
				else if (newXMove == 0 && newYMove == -1) {
					direction = Math.toRadians(90);
				}
				else if (newXMove == -1 && newYMove == 0) {
					direction = Math.toRadians(180);
				}
				else if (newXMove == 0 && newYMove == 1) {
					direction = Math.toRadians(270);
				}
				
				xMove = newXMove*speed;
				yMove = newYMove*speed;
				moveTimer = (byte)(60/speed);
			}
			
			if ( handler.getWorld().getSquare( (int)((x+radius*Math.signum(xMove)+xMove)/64), (int)(y / 64) ) <= 0
			&& !checkEntityCollisions(x + xMove, y) )
				x += xMove;
			if ( handler.getWorld().getSquare( (int)(x/64), (int)((y+radius*Math.signum(yMove)+yMove) / 64) ) <= 0
			&& !checkEntityCollisions(x, y + yMove) )
				y += yMove;
			
			if (shootTimer == shootWait) {
				shootTimer = 0;
				state = 2;
			}
		}
		
		// ATTACK
		else if (state == 2) {
			if (shootTimer == 0 || shootTimer == 30)
				sprite = Assets.npcGuard_shoot1;
			else if (shootTimer == 10) {
				sprite = Assets.npcGuard_shoot2;
				double angle = Math.atan2(handler.getWorld().getEntityManager().getPlayer().getY() - y, handler.getWorld().getEntityManager().getPlayer().getX() - x);
				//Entity obj = gun.shoot(this, x, y, Math.cos(angle), Math.sin(angle));
				//if (obj == handler.getWorld().getEntityManager().getPlayer())
				//	obj.hurt(5 * handler.randomInt(1, 3));
			}
			else if (shootTimer == 20)
				sprite = Assets.npcGuard_shoot3;
			else if (shootTimer == 40) {
				shootWait = (byte)(speed*handler.randomInt(2, 6));
				state = 1;
			}
			shootTimer++;
		}
		
		// PAIN
		else if (state == 3) {
			painTimer--;
			if (painTimer <= 0) {
				state = 1;
			}
		}
	}
	
	@Override
	public void hurt(int damage) {
		health -= damage;
		if (health <= 0)
			handler.getWorld().getEntityManager().removeEntity(this);
		painTimer = 10;
		sprite = Assets.npcGuard_pain;
		state = 3;
	}
	
	@Override
	public boolean isBright() {
		if (sprite == Assets.npcGuard_shoot2)
			return true;
		else
			return false;
	}

	@Override
	public void tick() {
		move();
		if (state < 2)
			sprite = Assets.npcGuard_s[getDirIndex()];
	}

	@Override
	public void render(Graphics g) {
		
	}

}
