package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class GameOver {

	private Level l;
	private String message, message2;
	private int width, height, fontsize;
	private Graphics2D gg;
	private boolean gameover;
	public GameOver(Level ll) {
		l = ll;
		message = "Game Over!";
		width = l.getWidth();
		height = l.getHeight();
		fontsize = l.getWidth()/15;
		message2 = "Your score: " + l.getScore();
		gameover = false;
	}
	public boolean isOver() {
		return gameover;
	}
	public void setGameOver(boolean b) {
		gameover = b;
	}
	public void draw(Graphics2D g) {
		gg = g;
		gg.setColor(Color.BLACK);
		gg.fillRect(0, 0, width, height);
		message2 = "Your score: " + (l.getScore()+l.Bosses());
		gg.setColor(Color.red);
		gg.setFont(new Font("Arial", Font.PLAIN, fontsize)); 
		gg.drawString(message, width/2-width/4, height/2);
		gg.setColor(Color.blue);
		gg.drawString(message2, width/2-width/4, height/2+height/8);
	}
}
