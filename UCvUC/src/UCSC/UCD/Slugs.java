package UCSC.UCD;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Slugs {

	//private BufferedImage tower;
	//private Pictures pic;
	private Point po;
	private Rectangle bounds;
	private Aggies tt;
	private int x,y;
	boolean dead;
	public Slugs(Frame f, Aggies t) {
		//pic = p;
		Random r = new Random();
		x = f.getWidth()+100;
		y = r.nextInt(f.getHeight());
		tt = t;
		po = new Point(x,y);
		bounds = new Rectangle((int)po.getX(), (int)po.getY(),1000, 1000);
		dead = false;
}
	public void drawPlane(Graphics2D g) {
		//g.drawImage(tower, x, y,tower.getWidth()/5, tower.getHeight()/5,null);
		//bounds = new Rectangle(x, y,(int)tower.getWidth()/5, (int)tower.getHeight()/5);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void moveLeft() {
		x-=10;
		if(bounds.intersects(tt.getBounds())) {
			if(!tt.isDestroyed()) {
			tt.setDestroyed(true);
			}
		}
		if(x < 0) {
			if(!dead) {
				dead = true;
				tt.addScore();
			}
		}
	}
	public void setBounds(int x,int y,int w, int h) {
		bounds = new Rectangle(x, y,w,h);
	}

}

