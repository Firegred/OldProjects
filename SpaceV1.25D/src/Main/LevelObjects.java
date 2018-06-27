package Main;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class LevelObjects implements Runnable {
	private ArrayList<Object> o;
    private Thread thread;
    private Random r;
    private int plus, minus, mag, rand;
    private int width, height;
    private Score score;
    private Player pp;
    private Boss b;
    public LevelObjects(int width1, int height1, Player p) {
    	width = width1;
    	height = height1;
    	plus = 1;
    	minus = -1;
    	mag = height/2+height/3;
    	o = new ArrayList<Object>();
    	Object o2 = new Object(0, 20, width/10, height/10, Color.white);
    	o.add(o2);
    	score = new Score(this);
    	pp = p;
    	rand = 100;
    	b = new Boss(pp.getLevel(), 0-pp.getWidth(), pp.getHeight()/2, 30, false);
    	start();
    }
    public Boss getBoss() {
    	return b;
    }
    public int getRand() {
    	return rand;
    }
    public void IncRand() {
    	if(rand > 2) {
    	rand--;
    	}
    }
    public Score getScore() {
    	return score;
    }
    public int getWidth() {
    	return width;
    }
    public int getHeight() {
    	return height;
    }
	public void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	public ArrayList<Object> objects() {
		return o;
	}
//	public void movingObjects() {
//		Thread t = new Thread() {
//			@Override
//			public void run() {
//			   while(true) {
//				if(!o.isEmpty()) {
//				 for(Object ob : o) {
//					 ob.moveRight();
//					 //System.out.println("t");
//				 }
//				 try {
//					Thread.sleep(20);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			 }
//			}
//			}
//		};
//		t.start();
//	}
	//this code isn't used
	@Override
	public void run() {
		while(true) {
			try {
				if(!pp.getLevel().isPaused()) {
				for(int i = 0; i < 1; i++) {
				r = new Random();
				int ch = r.nextInt(rand);
				if(b.isAlive()) {  
					for(Lazers ll : pp.getLazers()) {
						if(ll.getHitbox().intersects(b.getHitBox())) {
							if(b.getLife() > 0) {
								b.ReduceLife();
								ll.setHit(true);
							}
							else {
								b.kill();
								score.scoreBossPoint();
								pp.getLevel().setBossMode(false);
							}
						}
					}
					
					b.intro();
					b.sway();
					b.attackLazer();
					if(b.getHitBox().intersects(pp.getHitbox())) {
						pp.getLevel().setGameOver(true);
					}
					for(BossLazer s : b.getLazers()) {
						s.fireLazer();
						if(s.getHitbox().intersects(pp.getHitbox())) {
							pp.getLevel().setGameOver(true);
						}
					}
					
				}
				if(ch == 0) {
					if(!b.isAlive()) {
					int x = r.nextInt(255);
					int y = r.nextInt(255);
					int z = r.nextInt(255);
					Color c = new Color(x, y, z);
					int y1 = r.nextInt(height);
					Object ob = new Object(0, y1, width/20, height/20, c);
					o.add(ob);
					}
				}
//	try {
					for(Lazers l2 : pp.getLazers()) {
						l2.firing();
					}
					if(!o.isEmpty()) {
						for(Object ob : o) {
							ob.moveRight();
							if(ob.hasLazers()) {
								ob.getLazer().FireLazer();
								
								if(ob.getLazer().getRectangle().intersects(pp.getHitbox())) {
									pp.getLevel().setGameOver(true);
								}
							}
						    for(Lazers ll : pp.getLazers()) {
						    	
						    	if(pp.getHitbox().intersects(ob.getHitbox())) {
						    		pp.getLevel().setGameOver(true);
						    	}
						    	if(ll.getHitbox().intersects(ob.getHitbox())) {
						    		ob.setHit(true);
						    		ll.setHit(true);
						    		score.scorePoint();
						    		if(score.getScore()%4 == 0) {
						    			this.IncRand();
						    		}
						    		if(score.getScore()%pp.getLevel().getBossScore() == 0) {
						    			b = new Boss(pp.getLevel(), 0-pp.getLevel().getWidth(), pp.getLevel().getHeight()/2, 30, true);
						    			o.clear();
						    			Object k = new Object(10000, 10000, 4, 4, Color.RED);
						    			pp.getLazers().clear();
						    			o.add(k);
						    			Lazers l = new Lazers(pp, Color.blue);
						    			pp.getLazers().add(l);
						    			pp.getLevel().setBossMode(true);
						    			
						    		}
						    		//o.remove(ob);
						    		//pp.getLazers().remove(ll);
						    	}
						    }
							//if(ob.getX() > width) {
							//	o.remove(ob);
							//}
						}
					}
//			} catch (java.util.ConcurrentModificationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
				}
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
		}
		
	

