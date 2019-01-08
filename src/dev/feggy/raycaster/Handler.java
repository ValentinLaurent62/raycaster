package dev.feggy.raycaster;

import java.util.concurrent.ThreadLocalRandom;

import dev.feggy.raycaster.input.KeyManager;
import dev.feggy.raycaster.worlds.World;

public class Handler {
	
	private Game game;
	private World world;
	
	// UI gun frame
	private int frame = 0;
	
	public Handler(Game game) {
		this.game = game;
	}
	
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	
	// Game width
	public int getWidth() {
		return 320;
	}
	
	// Game height
	public int getHeight() {
		return 240;
	}
	
	// Window width
	public int getWindowWidth() {
		return game.getWidth();
	}
	
	// Window height
	public int getWindowHeight() {
		return game.getHeight();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
	public int getUIFrame() {
		return frame;
	}
	
	public void setUIFrame(int frame) {
		this.frame = frame;
	}
	
	// Generate random int
	public int randomInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

}
