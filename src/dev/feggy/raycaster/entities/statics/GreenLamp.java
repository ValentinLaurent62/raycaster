package dev.feggy.raycaster.entities.statics;

import java.awt.Graphics;

import dev.feggy.raycaster.Handler;
import dev.feggy.raycaster.gfx.Assets;

public class GreenLamp extends StaticEntity {

	public GreenLamp(Handler handler, float x, float y) {
		super(handler, x, y, Assets.greenlamp);
	}
	
	public boolean isBright() {
		return true;
	}
	
	public boolean isSolid() {
		return false;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void hurt(int damage) {
		// TODO Auto-generated method stub
		
	}

}
