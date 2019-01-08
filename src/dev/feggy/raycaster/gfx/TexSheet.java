package dev.feggy.raycaster.gfx;

import java.awt.image.BufferedImage;

public class TexSheet {
	
	private BufferedImage sheet;
	
	public TexSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}

}
