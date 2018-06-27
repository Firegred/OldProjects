package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class IntroScreen {
    
	private Graphics2D gg;
	private Command ll;
	private int width, height, fontsize;
	private boolean running;
	private String message;
	
	public IntroScreen(Command l) {
		ll = l;
		width = ll.getWidth();
		height = ll.getHeight();
		running = true;
		fontsize=l.getWidth()/30;
		message = "Welcome to Space game. Press Space to Start";
		}
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean b) {
		running = b;
	}
	public void draw(Graphics2D g) {
		gg = g;
		if(running = true) {
			gg.setColor(Color.red);
			gg.setFont(new Font("Arial", Font.PLAIN, fontsize)); 
			gg.drawString(message, width/10, height/2);
		}
	}
	
}
