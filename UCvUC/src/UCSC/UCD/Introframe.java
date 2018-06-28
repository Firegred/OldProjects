package UCSC.UCD;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Introframe extends JPanel{

	JFrame f;
	public Introframe() {
		f = new JFrame();
		f.setSize(640*2,480*2);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.add(this);
	}
	public void rem() {
		f.setVisible(false);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gg = (Graphics2D) g;
		Font f = new Font("Arial", 0, 40);
		gg.setColor(Color.BLACK);
		gg.setBackground(Color.RED);
		gg.setFont(f);
		gg.drawString("The Slugs are invading the Aggies! ",200, 200);
		gg.drawString("You must protect UC Davis pride", 200, 250);
		
	}
}
