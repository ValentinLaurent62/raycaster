package dev.feggy.raycaster.entities.statics;

import java.awt.image.BufferedImage;

import dev.feggy.raycaster.Handler;
import dev.feggy.raycaster.entities.Entity;

public abstract class StaticEntity extends Entity {

	public StaticEntity(Handler handler, float x, float y, BufferedImage sprite) {
		super(handler, x, y, sprite);
	}

}
