package Sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Tile {

	private int[][] pixels;
	private int x,y;
	private Rectangle bounds;
	private Color c;
	
	public Tile(int locx, int locy) {
		x = locx;
		y = locy;
		bounds = new Rectangle(32, 32);
	}
	public int getX() {
		return x;
	}
	public void setColor(Color g) {
		c = g;
	}
	public int getY() {
		return y;
	}
	public Rectangle getBounds() {
		return bounds;
	}
	public int getWidth() {
		return (int) bounds.getWidth();
	}
	public int getHeight() {
		return (int) bounds.getHeight();
	}
	public void drawGraphic(Graphics2D g) {
		g.setColor(c);
		g.fillRect(x, y, (int) ((int) bounds.getWidth()), (int)bounds.getHeight());
	}
	
}
