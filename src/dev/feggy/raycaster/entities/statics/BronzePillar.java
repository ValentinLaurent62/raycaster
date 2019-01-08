package dev.feggy.raycaster.entities.statics;

import java.awt.Graphics;

import dev.feggy.raycaster.Handler;
import dev.feggy.raycaster.gfx.Assets;

public class BronzePillar extends StaticEntity {

	public BronzePillar(Handler handler, float x, float y) {
		super(handler, x, y, Assets.bronzepillar);
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
