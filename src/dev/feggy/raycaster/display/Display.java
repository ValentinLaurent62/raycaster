package dev.feggy.raycaster.display;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Display {
	
	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int width, height, mode;
	
	// Constructor
	public Display(String title, int width, int height, int mode) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.mode = mode;
		
		createDisplay();
	}
	
	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		// Undecorated in fullscreen mode
		if (mode == 1)
			frame.setUndecorated(true);
		
		// Disable cursor
		frame.setVisible(true);
		frame.setCursor( frame.getToolkit().createCustomCursor(
	            new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ),
	            new Point(),
	            null ) );
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
	}
	
	// Getters
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
