package UCSC.UCD;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class Aggies implements MouseMotionListener {

	private BufferedImage tower;
	private Pictures pic;
	private Point po;
	private Rectangle bounds;
	private boolean destroyed;
	private int score;
	public Aggies(Pictures p) {
		pic = p;
		tower = pic.getTowerImage();
		po = new Point(60,60);
		bounds = new Rectangle((int)po.getX(), (int)po.getY(),(int)tower.getWidth()/2, (int)tower.getHeight()/2);
		destroyed = false;
		score = 0;
	}
	public boolean isDestroyed() {
		return destroyed;
	}
	public void setDestroyed(boolean t) {
		destroyed = t;
	}
	public void addScore() {
		score++;
	}
	public void drawTowers(Graphics2D g) {
		g.drawImage(tower, (int)po.getX(), (int)po.getY(),tower.getWidth()/2, tower.getHeight()/2,null);
	}

	public Rectangle getBounds() {
		return bounds;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		po = new Point(e.getX(), e.getY());
		bounds = new Rectangle((int)po.getX(), (int)po.getY(),(int)tower.getWidth()/2, (int)tower.getHeight()/2);
	}
	public void drawScore(Graphics2D g) {
		Font f = new Font("Arial", 0, 40);
		g.setFont(f);
		g.setColor(Color.WHITE);
		g.drawString("UCSC slugs dodged " + score + " times", 640/2, 100);
		g.setColor(Color.BLACK);
		g.drawString("UCSC slugs dodged " + score + " times", 3+640/2, 100);
	}
	public int getScore() {
		return score;
	}
	
	

}
