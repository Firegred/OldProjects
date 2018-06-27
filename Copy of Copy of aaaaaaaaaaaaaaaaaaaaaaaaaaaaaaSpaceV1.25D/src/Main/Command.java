package Main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class Command{

    private Graphics2D g2;
    private Level l;
    private Random r;
    private Thread thread;
    private int width1, height1;
    private JFrame ff;
    private IntroScreen screen;
    private Keyboard2 k;
    private Thread t;
    private boolean level;
	public Command(int width, int height, JFrame f) {
		ff = f;
		width1 = width;
		height1 = height;
//		
		screen = new IntroScreen(this);
		k = new Keyboard2(this);
		ff.addKeyListener(k);
		level = false;
//		checker();
		
	}
	public void playLevel(boolean b) {
	   level = b;
	   if(level == true) { 
		   l = new Level(width1, height1, ff);
	   }
	}
	public IntroScreen getScreen() {
		return screen;
	}
//	public void checker() {
//		t = new Thread() {
//			@Override
//			public void run() {
//				while(screen.isRunning()) {
//					if(!screen.isRunning()) {
//						l = new Level(width1, height1, ff);
//						System.out.println("woirks");
//						t.stop();
//					}
//				}
//			}
//		};
//		t.start();
//		
//	}
	public int getWidth() {
		return ff.getWidth();
	}
	public int getHeight() {
		return ff.getHeight();
	}
	
	
	public void drawGame(Graphics2D gg) {
		g2 = gg;
		if(screen.isRunning()) {
			screen.draw(g2);
		}
		else if(level = true){
		l.drawLevel(g2);
		//System.out.println("working");
		}
	}


	
	
}
