package me.Firegred.Graphics;

import java.awt.Color;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

	int width;
	int height;
	GraphicPane p;
	public GameFrame(int w, int h) {
		width=w;
		height=h;
		this.setSize(width,height);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setBackground(new Color(0, 0, 0, 0));
		p = new GraphicPane(this);
		p.setOpaque(false);
		this.add(p);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}
