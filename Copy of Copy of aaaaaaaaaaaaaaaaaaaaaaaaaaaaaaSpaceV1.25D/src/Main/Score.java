package Main;

import java.awt.Font;
import java.awt.Graphics2D;

public class Score {
    private int score, fontsize, bossscore;
    private Graphics2D gg;
    private LevelObjects ll;
    private boolean boss;
    private int counter;
    private String bossmessage;
    private int multiplier;
	public Score(LevelObjects l) {
		ll = l;
		score = 0;
		fontsize=l.getWidth()/20;
		bossscore = 0;
		boss = false;
		counter = 0;
		bossmessage = "You have slayed the Boss!";
		multiplier = 0;
	}
	public int getMultiplier() {
		return multiplier;
	}
	public int getScore() {
		return score;
	}
	public int getBossScore() {
		return bossscore;
	}
	public void scorePoint() {
		score++;
	}
	public void scoreBossPoint() {
		bossscore+=101;
		boss = true;
		multiplier++;
	}
	public void drawScore(Graphics2D g) {
		gg = g;
		gg.setFont(new Font("Arial", Font.PLAIN, fontsize)); 
		gg.drawString(String.valueOf(score+bossscore), ll.getWidth()/2, ll.getHeight()/10);
		//System.out.println(bossscore);
		if(boss == true) {
			counter++;
			gg.drawString(bossmessage, ll.getWidth()/4, ll.getHeight()/6);
			
		}
		if(counter == 10000) {
			boss = false;
			counter=0;
		}
	}
}
