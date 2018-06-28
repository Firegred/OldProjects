package me.Firegred.Mechanics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Score {

	Game g;
	int score1,score2;
	Font f;
	int x1,y1,x2,y2;
	public Score(Game g) {
		this.g=g;
		score1=0;
		score2=0;
		f = new Font("Comic Sans",1,g.getPane().getGameFrame().getWidth()/35);
		x2 = g.getPane().getGameFrame().getWidth()/3;
		x1 = g.getPane().getGameFrame().getWidth()/3 * 2;
		y1 = g.getPane().getGameFrame().getHeight()/15;
		y2=y1;
	}
	public int getP1score() {
		return score1;
	}
	public int getP2score() {
		return score2;
	}
	public void ScoreP1() {
		score1++;
	}
	public void ScoreP2() {
		score2++;
	}
	public void drawScore(Graphics2D g) {
		g.setFont(f);
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(score1), x1, y1);
		g.drawString(String.valueOf(score2), x2, y2);
		g.setColor(Color.BLACK);
		g.drawString(String.valueOf(score1), x1+4, y1);
		g.drawString(String.valueOf(score2), x2+4, y2);
	}
}
