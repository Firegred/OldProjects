package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends JPanel{

	private int width, height;
	private JFrame f;
	private Graphics2D gg;
	private Command c;
	
	
	public Display(int width, int height, JFrame f) {
	   this.width = width;
	   this.height = height;
	   this.f = f;
	   c = new Command(width, height, this.f);
	   
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		gg = (Graphics2D) g;
		c.drawGame(gg);
		f.repaint();
		gg.dispose();
		
	}
}
