package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener,Runnable{

	private Player pl;
	private int x, y, speed;
	private boolean w,a,s,d, space,turbo;
	private Thread turboThread;
	public Keyboard(Player p) {
		pl = p;
		start();
		x = pl.getX();
		y = pl.getY();
		speed = pl.getSpeed();
		w = false;
		a = false;
		s = false;
		d = false;
		space = false;
		turbo=false;
		movement();
	}
	private void Turbo() {
		turboThread=new Thread() {
			@Override
			public void run() {
				while(true) {
					if(!pl.getLevel().isPaused())
					pl.firing();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
	}
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}
	public void movement() {
		Thread t = new Thread() {
			@Override
			public void run() {
				while(true) {
					pl.boundaryCheck();
					
					if(w == true && pl.upBlock() == false && !pl.getLevel().isPaused()) {
				    	pl.moveY(y-=speed);
				    }
				    if(a == true && pl.leftBlock() == false && !pl.getLevel().isPaused()) {
				    	pl.moveX(x-=speed);
				    }
				    if( s == true && pl.downBlock() == false && !pl.getLevel().isPaused()) {
				    	pl.moveY(y+=speed);
				    	//System.out.println("test");
				    	
				    }
				    if(d == true && pl.rightBlock() == false && !pl.getLevel().isPaused()) {
				    	pl.moveX(x+=speed);
				    }
				    try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				}
				
		};
		t.start();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_M) {
			if(pl.getLevel().getMusic().isPlaying()) {
				pl.getLevel().getMusic().setPlaying(false);
				pl.getLevel().getMusic().pause();
				pl.getLevel().getMusic().setMuted(true);
			}
			else {
				pl.getLevel().getMusic().setPlaying(true);
				pl.getLevel().getMusic().unpause();
				pl.getLevel().getMusic().setMuted(false);
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!pl.getLevel().isPaused()) {
				pl.getLevel().setPaused(true);
				if(!pl.getLevel().getMusic().isMuted()) {
				pl.getLevel().getMusic().pause();
				}
				
			}
			else {
				pl.getLevel().setPaused(false);
				if(!pl.getLevel().getMusic().isMuted()) {
				pl.getLevel().getMusic().unpause();
				}
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_R) {
			Main m = new Main();
			pl.getLevel().getMusic().stop();
			pl.getLevel().getFrame().dispose();
			
			
		}
		if(!pl.getLevel().isPaused()) {
		if(!pl.getLevel().getGameOver().isOver()) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE && space == false) {
			space = true;
			pl.firing();
		}
		if(e.getKeyCode() == KeyEvent.VK_T) {
			if(turbo==false) {
				turbo=true;
				Turbo();
				turboThread.start();
			}
			else if(turbo==true) {
				turbo=false;
				turboThread.stop();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_O) {
			if(!pl.shootLeft) {
				pl.shootLeft=true;
			}
			else {
				pl.shootLeft=false;
			}
		}
		
		if((e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) && w == false) {
	    	w = true;
	    	pl.moveUp();
	    }
		else if((e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) && a == false) {
	    	a = true;
	    	pl.moveleft();
	    }
		else if((e.getKeyCode() == KeyEvent.VK_S ||e.getKeyCode() == KeyEvent.VK_DOWN) && s == false) {
	    	s = true;
	    	pl.moveDown();
	    	
	    }
		else if((e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) && d == false) {
	    	d = true;
	    	pl.moveRight();
	    }
		}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE && space == true) {
			space = false;
		}
		
		if((e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) && w == true) {
	    	w = false;
	    }
		else if((e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) && a == true) {
	    	a = false;
	    }
		else if((e.getKeyCode() == KeyEvent.VK_S ||e.getKeyCode() == KeyEvent.VK_DOWN) && s == true) {
	    	s = false;
	    }
		else if((e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) && d == true) {
	    	d = false;
	    }
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
