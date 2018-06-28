package me.Bored.main;

import javax.swing.JFrame;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width, height;
	private boolean resize;
	public Frame(int w, int h, boolean r) {
		width =w;
		height =h;
		setSize(width, height);
		resize = r;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(resize);
		setVisible(true);
		
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}
