package Main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyLazers {

	private int x, y, width, height;
	private double speed;
	private Graphics2D gg;
	private Object oo;
	private Rectangle rec;
	public EnemyLazers(Object o) {
		oo = o;
		
		width = oo.getWidth()/2;
		height = oo.getHeight()/2;
		x = oo.getX()+width;
		y = oo.getY()+oo.getHeight()/2;
        rec = new Rectangle(x, y, width, height);
		speed = 3;
	}
	public void FireLazer() {	
		x+=speed;
		rec = new Rectangle(x, y, width, height);
		
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Rectangle getRectangle() {
		return rec;
	}
	public void draw(Graphics2D g) {
		gg = g;
		gg.setColor(Color.red);
		gg.fillRect(x, y, width, height);
	}
	
}
