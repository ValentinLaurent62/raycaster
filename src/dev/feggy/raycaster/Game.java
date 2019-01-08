package dev.feggy.raycaster;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.feggy.raycaster.display.Display;
import dev.feggy.raycaster.gfx.Assets;
import dev.feggy.raycaster.input.KeyManager;
import dev.feggy.raycaster.states.GameState;
import dev.feggy.raycaster.states.State;

public class Game implements Runnable {
	
	private Display display;
	private String title;
	private int width, height;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	// States
	public State gameState;
	
	// Input
	private KeyManager keyManager;
	
	// Handler
	private Handler handler;
	
	// Debug mode
	private int frameCounter;
	
	// Screen mode
	private int mode;
	
	// Constructor
	public Game(String title, int width, int height, int mode) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.mode = mode;
		keyManager = new KeyManager();
	}
	
	private void init() {
		display = new Display(title, width, height, mode);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		handler = new Handler(this);
		gameState = new GameState(handler);
		State.setState(gameState, true);
	}
	
	private void tick() {
		keyManager.tick();
		
		if (State.getState() != null)
			State.getState().tick();
		
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// Clear Screen
		g.clearRect(0, 0, width, height);
		// Draw Here!
		
		if (State.getState() != null)
			State.getState().render(g);
		
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(frameCounter), 16, 16);
		
		// End Drawing!
		bs.show();
		g.dispose();
	}

	@Override
	public void run() {
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if (delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if (timer >= 1000000000) {
				frameCounter = ticks;
				ticks = 0;
				timer = 0;
			}
			
			try {
				Thread.sleep((long)(lastTime - System.nanoTime() + timePerTick)/1000000);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			catch (IllegalArgumentException e) {
				e.printStackTrace();
			}

		}
		
		stop();

	}
	
	public void closeGame() {
		running = false;
		System.exit(0);
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
