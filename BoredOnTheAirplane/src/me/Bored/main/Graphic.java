package me.Bored.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Level.Level;
import Sprites.Player;

public class Graphic extends JPanel{

	private int width, height;
	private Frame f;
	private Level l;
	private Player p;
	private int modifier;
	public Graphic(Frame fr) {
		f = fr;
		width = f.getWidth();
		height = f.getHeight();
		modifier = 1;
		l = new Level(width, height,modifier);
		p = new Player(l, 32, 32);
		f.addKeyListener(p);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);;
		try {
			Thread.sleep(40);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Graphics2D gg = (Graphics2D) g;
		Font ff = new Font("Arial",1,40);
		gg.setFont(ff);
		p.drawGraphic(gg);
		drawTiles(gg);
		gg.setColor(Color.BLACK);
		gg.drawString("Level: " + modifier, width/2, height/10);
		gg.dispose();
		f.repaint();
		if(p.isReset()) {
			f.removeKeyListener(p);
			modifier = 1;
			l = new Level(width, height, modifier);
			p = new Player(l, 32, 32);
			f.addKeyListener(p);
		}
		if(p.isNextLevel()) {
			f.removeKeyListener(p);
			modifier++;
			l = new Level(width, height, modifier);
			p = new Player(l, 32, 32);
			f.addKeyListener(p);
		}
		
	}
	public void drawTiles(Graphics2D g) {
		for(int x = 0; x < l.geTiles().length; x++) {
			for(int y = 0; y < l.geTiles()[x].length; y++) {
//				int x1 = l.geTiles()[x][y].getX();
//				int y1 = l.geTiles()[x][y].getY();
//				int x2 = l.geTiles()[x][y].getX() + l.geTiles()[x][y].getWidth();
//				int y2 = l.geTiles()[x][y].getY() + l.geTiles()[x][y].getHeight();
//				
//				g.fillRect(x1, y1, x2, y2);
				l.geTiles()[x][y].drawGraphic(g);
			}
		}
	}
}
