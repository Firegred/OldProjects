package UCSC.UCD;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Frame extends JFrame{
	
	public Frame() {
		this.setSize(640*2,480*2);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Pictures p = new Pictures();
		Aggies t = new Aggies(p);
		add(new Graphic(this,t));
		this.addMouseMotionListener(t);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JFrame.
		this.getContentPane().setCursor(blankCursor);
		this.setVisible(true);
		new Pictures().playSong();
	}

}
