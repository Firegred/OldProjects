package me.Firegred.Graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.management.timer.Timer;
import javax.swing.JPanel;

import me.Firegred.Mechanics.Game;

public class GraphicPane extends JPanel{

	GameFrame gf;
	Graphics2D gg;
	Game game;
	public GraphicPane(GameFrame gf) {
		this.gf = gf;
		game = new Game(this);
		gf.addKeyListener(game);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		gg = (Graphics2D) g;
		game.getScore().drawScore(gg);
		game.getBall().drawBall(gg);
		game.getPaddle().drawPaddle(gg);
		gf.repaint();
		gg.dispose();
	}
	public GameFrame getGameFrame() {
		return gf;
	}
}
