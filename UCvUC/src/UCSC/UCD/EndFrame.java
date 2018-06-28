package UCSC.UCD;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class EndFrame extends JPanel{

	public JFrame f;
	public int score;
	public Pictures p;
	public EndFrame(Aggies t, int s) {
		score = s;
		f = new JFrame();
		f.setSize(640*2,480*2);
		f.add(this);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		p = new Pictures();
		p.playEnd();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gg = (Graphics2D) g;
		p.drawEnd(gg);
		
		Font f = new Font("Arial", 0, 40);
		gg.setFont(f);
		gg.setColor(Color.BLACK);
		gg.drawString("You saved UC Davis pride only " + score + " times", 640/2, 100);
		gg.setColor(Color.RED);
		gg.drawString("You saved UC Davis pride only " + score + " times", 3+640/2, 100);
	}
}
