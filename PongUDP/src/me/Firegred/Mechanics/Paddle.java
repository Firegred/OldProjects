package me.Firegred.Mechanics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle {

	Game g;
	PlayerType p;
	Rectangle bounds;
	int width;
	int height;
	int x,y;
	int speed;
	boolean pressed;
	boolean up,down;
	public Paddle(Game g, PlayerType player) {
		this.g=g;
		pressed=false;
		p=player;
		width=g.getPane().getGameFrame().getWidth()/80;
		height=g.getPane().getGameFrame().getHeight()/8;
		setLocation();
		speed=g.getPane().getGameFrame().getHeight()/50;
		up=false;
		down=false;
	}
	private void setLocation() {
		y = g.getPane().getGameFrame().getHeight()/2;
		if(p == PlayerType.PLAYER_1) {
			x = g.getPane().getGameFrame().getWidth()-g.getPane().getGameFrame().getWidth()/45;
		}
		else if(p == PlayerType.PLAYER_2) {
			x = g.getPane().getGameFrame().getWidth()/100;
		}
		bounds = new Rectangle(x, y, width, height);
	}
	public void drawPaddle(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}
	public Rectangle getBounds() {
		return bounds;
	}
	public boolean isPressed() {
		return pressed;
	}
	public void setPressed(boolean b) {
		pressed=b;
	}
	public void setUp(boolean b) {
		up=b;
	}
	public void setDown(boolean b) {
		down=b;
	}
	public void moveUp() {
		if(y>0) {
		y-=speed;
		bounds.setLocation(x,y);
		}
	}
	public void moveDown() {
		if(y+height<g.getPane().getGameFrame().getHeight()) {
		y+=speed;
		bounds.setLocation(x,y);
		}
	}
	public void updateBounds(int x, int y, int width, int height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		bounds.setBounds(x, y, width, height);
	}
}
