package dev.feggy.raycaster;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Launcher {
	
	// Map file to load
	public static String worldPath;
	
	// Game object
	private static Game game;
	private static int mode;
	
	// Main method
	public static void main(String[] args) {

		// Default parameters
		worldPath = "res/worlds/world1.txt";
		mode = 0;
		
		// Go through all command-line parameters
		/*
		 * -sc [mode]		Changes screen mode:
		 * 					0 -> scales to highest possible resolution, keeping aspect ratio (default)
		 * 					1 -> fullscreen, not keeping aspect ratio
		 * 					2 -> no scaling
		 * -map [file]		Loads given map file
		 */
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-map"))
				worldPath = "res/worlds/" + args[i+1] + ".txt";
			else if (args[i].equals("-sc"))
				mode = Integer.parseInt(args[i+1]);
		}
		
		// Get screen resolution
		Dimension screenSize  = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		// Find highest possible game resolution while keeping aspect ratio
		double newWidth = 320;
		double newHeight = 240;
		while (newHeight <= height-240) {
			newWidth += 320;
			newHeight += 240;
		}
		
		if (mode == 2)
			game = new Game("Raycaster", 320, 240, mode);
		else if (mode == 1)
			game = new Game("Raycaster", (int)width, (int)height, mode);
		else
			game = new Game("Raycaster", (int)newWidth, (int)newHeight, mode);
		game.start();
	}
	
}
