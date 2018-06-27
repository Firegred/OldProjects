package Main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BossLazer {

	private int x, y, width, height;
	private int speed;
	private Graphics2D gg;
	private Object oo;
	private Rectangle rec;
	private Boss bb;
	public BossLazer(Boss b) {
		bb = b;
		x=b.getX()+b.getWidth();
		y=b.getY()+b.getHeight()/2;
		width=b.getWidth()/2;
		height=b.getHeight()/2;
		speed=(int)(b.getSpeed()*2);
		rec=new Rectangle(x,y,width,height);
	}
	public void fireLazer() {
		x+=speed;
		rec=new Rectangle(x,y,width,height);
	}
	public Rectangle getHitbox() {
		return rec;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getSpeed() {
		return speed;
	}
	public void draw(Graphics2D g) {
		gg = g;
		gg.setColor(Color.red);
		gg.fillRect(x, y, width, height);
	}
	
}
