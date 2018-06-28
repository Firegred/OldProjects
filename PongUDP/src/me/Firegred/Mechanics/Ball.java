package me.Firegred.Mechanics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

	public Game g;
	int width, height,x,y;
	Rectangle bounds;
	int xspeed;
	int yspeed;
	int oldxspeed;
	int oldyspeed;
	Random r;
	Networking n;
	public Ball(Game g, Networking n) {
		this.g = g;
		width=g.getPane().getGameFrame().getWidth()/80;
		height=g.getPane().getGameFrame().getHeight()/45;
		x=g.getPane().getGameFrame().getWidth()/2;
		y=g.getPane().getGameFrame().getHeight()/2;
		bounds=new Rectangle(x,y,width,height);
		xspeed = width/2;
		yspeed = height/2;
		oldxspeed=xspeed;
		oldyspeed=yspeed;
		r = new Random();
		this.n=n;
		if(!n.isHost()) {
			nullball();
		}
	}
	public Rectangle getBounds() {
		return bounds;
	}
	public void invertX() {
		xspeed = -xspeed;
	}
	public void invertY() {
		yspeed = -yspeed;
	}
	public void moveBall() {
		x+=xspeed;
		y+=yspeed;
		bounds.setLocation(x, y);
	}
	public void nullball() {
		x=-100;
		y=-100;
		xspeed=0;
		yspeed=0;
		bounds.setLocation(x, y);
	}
	public void resetBall() {
		y = r.nextInt(g.getPane().getGameFrame().getHeight());
		x=g.getPane().getGameFrame().getWidth()/2;
		xspeed=oldxspeed;
		yspeed=oldyspeed;
	}
	public void collisionDetection() {
		if(bounds.intersects(g.getPaddle().bounds)) {
			invertX();
			//xspeed+=1;
		}
		if(y<0) {
			invertY();
		}
		if(y+height>g.getPane().getGameFrame().getHeight()) {
			invertY();
		}
		//score point for p1. ball on p2 screen
		if(x<0 && x!=-100 && xspeed<0) {
			if(!n.host) {
			n.sendScore("P1");
			g.getBall().nullball();
			n.sendReset();
			}
			else if(n.host){
			double ty= (double) y*1;
			double ymult = ty/g.getPane().getGameFrame().getHeight();
			n.sendBall("notforhost", ymult);
			g.getBall().nullball();
			}
		}
		//score point for p2. ball on p1 screen
		if(x>g.getPane().getGameFrame().getWidth() && xspeed>0) {
			if(n.host) {
				n.sendScore("P2");
				g.getBall().resetBall();
			}
			else if(!n.host){
				double ty = (double) y*1;
				double ymult = (double) ty/g.getPane().getGameFrame().getHeight();
				n.sendBall("forhost", ymult);
				g.getBall().nullball();
			}
			
		}
	}
	public void drawBall(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}
	public void setBallStuff(int x, double inty) {
		this.x=x;
		this.y=((int) (g.getPane().getGameFrame().getHeight()*inty));
		bounds.setLocation(x, y);
		if(n.host) {
		this.xspeed=oldxspeed;
		this.yspeed=oldyspeed;
		}
		if(!n.host) {
			this.xspeed=-oldxspeed;
			this.yspeed=-oldyspeed;	
		}
	}
	
}
