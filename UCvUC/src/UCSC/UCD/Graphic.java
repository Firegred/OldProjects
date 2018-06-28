package UCSC.UCD;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Graphic extends JPanel{
	
	public Aggies tower;
	public Frame frame;
	public ArrayList<Slugs> planes;
	public Pictures c;
	private boolean end;
	public Graphic(Frame f, Aggies t) {
		tower = t;
		frame = f;
		planes = new ArrayList<Slugs>();
		planes.add(new Slugs(f, t));
		c = new Pictures();
		end = false;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			Thread.sleep(15);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Graphics2D gg  = (Graphics2D) g;
		Random r = new Random();
		int chance = r.nextInt(35);
		c.drawDesert(gg);
		tower.drawTowers(gg);
		tower.drawScore(gg);
		
		if(chance == 0) {
			planes.add(new Slugs(frame, tower));
		}
		for(Slugs p : planes) {
			p.moveLeft();
			if(p.getX() > 0-c.getPlaneImage().getWidth()) {
			c.drawPlane(p, p.getX(), p.getY(), gg);
			}
		}
		if(tower.isDestroyed() && !end) {
			end = true;
			frame.setVisible(false);
			new EndFrame(tower, tower.getScore());
		}
	    gg.dispose();
	    frame.repaint();
	   
		
	}

}
