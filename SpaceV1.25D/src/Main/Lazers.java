package Main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Lazers {

	private Player p1;
	private int x, y, speed;
	private Color c;
	private Graphics2D gg;
	private int width, height;
	private boolean up,down,left,right;
	private Rectangle hitbox;
	private boolean hit;
	public Lazers(Player p, Color c1) {
		p1 = p;
		x = new Integer(p1.getX());
		y = new Integer(p1.getY());
		c = c1;
		up=false;
		down=false;
		left=false;
		right=false;
		height=p.getHeight()/2;		
		width=p.getWidth()/2;
		speed = p.getSpeed()*2;
		hitbox = new Rectangle(x, y, width, height);
		hit = false;
	}
	public boolean isHit() {
		return hit;
	}
	public void setHit(boolean s) {
		hit = s;
	}
	public Rectangle getHitbox() { 
		return hitbox;
	 }
	public void firing() {
		if(hit == true) {
			x=10000;
			y=10000;
		}
		if(up == true) {
			y-=speed;
		}
		if(down == true) {
			y+=speed;
		}
		if(left == true) {
			x-=speed;
		}
		if(right == true) {
			x+=speed;
		}
		hitbox = new Rectangle(x, y, width, height);
	}
	public void FireUp() {
		if(p1.isUp() == true) {
			up=true;
			down=false;
			left=false;
			right=false;
		}
	}
	public void FireDown() {
		if(p1.isDown()==true) {
			down=true;
			left=false;
			right=false;
			up=false;
		}
	}
	public void FireLeft() {
		if(p1.isLeft() == true) {
			left=true;
			
			up=false;
			down=false;
			right=false;
		}
	}
	public void FireRight() {
		if(p1.isRight() == true) {
			right=true;
			
			up=false;
			down=false;
			left=false;
			
		}
	}
	public void ModFireLeft() {
		left=true;
		
		up=false;
		down=false;
		right=false;
	}
	public void Fire(Graphics2D g) {
		gg = g;
		gg.setColor(c);
		if(hit == false) {
		if(up == true) {
			hitbox = new Rectangle(x+height/2, y+width/2, width, height);
			gg.fillRect(x, y, height, width);
		}
		if(down == true) {
			hitbox = new Rectangle(x+height/2, y, width, height);
			gg.fillRect(x, y, height, width);
		}
		if(left == true) {
			hitbox = new Rectangle(x, y+height/2, width, height);
			gg.fillRect(x, y, width, height);
		}
		if(right == true) {
			hitbox = new Rectangle(x+width/2, y+height, width, height);
			gg.fillRect(x, y, width, height);
		}
		}
		
	}
	
}
