package Sprites;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Level.LevelFormat;

public class Player extends Tile implements KeyListener{

	private boolean up,down,left,right,nextlevel,reset;
	private boolean[] moves = {up,down,left,right};
	private int[] keys = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
	private LevelFormat lf;
	private int x11,y11;
	public Player(LevelFormat l, int locx, int locy) {
		super(locx, locy);
		this.setColor(Color.BLUE);
		lf = l;
		x11=locx/32;
		y11 = locy/32;
		lf.geTiles()[locx/32][locy/32] = this;
		up = false;
		down = false;
		left = false;
		right = false;
		nextlevel = false;
		reset = false;
		moveout();
		// TODO Auto-generated constructor stub
	}

    public boolean isNextLevel() {
    	return nextlevel;
    }
	public void moveout() {
		for(int i = 2; i < lf.geTiles()[1].length; i++) {
			y11++;
			if(lf.geTiles()[1][i] instanceof Grass) {
				lf.geTiles()[x11][y11] = this;
				break;
			}
		}
	}
	public void moveUp() {
		if(lf.geTiles()[x11][y11-1] instanceof Coin) {
			nextlevel = true; 
		}
		if(!(lf.geTiles()[x11][y11-1] instanceof Wall)) {
		lf.geTiles()[x11][y11] = new Grass(32*x11, 32*y11);
		y11--;
		lf.geTiles()[x11][y11] = this;
		}
		
		
	}
	
	public void moveDown() {
		if(lf.geTiles()[x11][y11+1] instanceof Coin) {
			nextlevel = true; 
		}
		if(!(lf.geTiles()[x11][y11+1] instanceof Wall)) {
		lf.geTiles()[x11][y11] = new Grass(32*x11, 32*y11);
		y11++;
		lf.geTiles()[x11][y11] = this;
		}

	}
	public void moveLeft() {
		 if(lf.geTiles()[x11-1][y11] instanceof Coin) {
				nextlevel = true; 
			}
		if(!(lf.geTiles()[x11-1][y11] instanceof Wall)) {
		lf.geTiles()[x11][y11] = new Grass(32*x11, 32*y11);
		x11--;
		lf.geTiles()[x11][y11] = this;
		}
	   
	}
	public void moveRight() {
		if(lf.geTiles()[x11+1][y11] instanceof Coin) {
			nextlevel = true; 
		}
		if(!(lf.geTiles()[x11+1][y11] instanceof Wall)) {
		lf.geTiles()[x11][y11] = new Grass(32*x11, 32*y11);
		x11++;
		lf.geTiles()[x11][y11] = this;
		}
		
	}
	public void move(int num) {
		if(num == 0) {
			moveUp();
		}
		if(num == 1) {
			moveDown();
		}
		if(num == 2) {
			moveLeft();
		}
		if(num == 3) {
			moveRight();
		}
		
	}
	public boolean isReset() {
		return reset;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		for(int i = 0; i < keys.length; i++) {
			if(e.getKeyCode() == keys[i]) {
				if(moves[i] == false) {
					move(i);
					moves[i] = true;
				}
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_R) {
			reset = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for(int i = 0; i < keys.length; i++) {
			if(e.getKeyCode() == keys[i]) {
				if(moves[i] == true) {
					moves[i] = false;
				}
			}
		}
		
	}
	
	

	
}
