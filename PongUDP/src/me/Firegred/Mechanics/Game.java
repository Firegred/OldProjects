package me.Firegred.Mechanics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import me.Firegred.Graphics.GraphicPane;

public class Game implements KeyListener{

	GraphicPane p;
	Paddle pad;
	//Paddle p2;
	Ball b;
	Thread t;
	Score s;
	Networking n;
	public Game(GraphicPane p) {
		this.p = p;
		try {
			n = new Networking(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(!n.start) {
			
		}
		if(n.host) {
		pad=new Paddle(this,PlayerType.PLAYER_1);
		}
		else {
		pad=new Paddle(this,PlayerType.PLAYER_2);
		}
		b = new Ball(this,n);
		s = new Score(this);
		runGame();
	}
	
	public Score getScore() {
		return s;
	}
	public void resetGame() {
		b.resetBall();
	}
	
	public GraphicPane getPane() {
		return p;
	}
	public Paddle getPaddle() {
		return pad;
	}
	public Ball getBall() {
		return b;
	}
	private void paddleStuff() {
			if(pad.down) {
				pad.moveDown();
			}
			else if(pad.up) {
				pad.moveUp();
			}
		}
	private void runGame() {
		t = new Thread() {
			@Override
			public void run() {
				while(true) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				b.moveBall();
				b.collisionDetection();
				paddleStuff();
				}
			}
		};
		t.start();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(!pad.isPressed()) {
				pad.setPressed(true);
				pad.setUp(true);
				pad.setDown(false);
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(!pad.isPressed()) {
				pad.setPressed(true);
				pad.setDown(true);
				pad.setUp(false);
			}
		}
		
		try {
			Thread.sleep(20);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) {
			pad.setPressed(false);
			pad.setDown(false);
			pad.setUp(false);
		}
 		// TODO Auto-generated method stub
		
	}
	
}
