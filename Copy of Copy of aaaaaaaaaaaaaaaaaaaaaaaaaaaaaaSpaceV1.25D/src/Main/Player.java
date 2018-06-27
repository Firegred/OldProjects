package Main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Player {

	private Level ll;
	private int x, y, speed;
	private Color cc;
	private Graphics2D gg;
	private int width, height,widthl, heightl;
	private boolean u2,d2,l2,r2;
	private boolean u1,d1,l1,r1;
	public boolean shootLeft;
	private Thread t;
	public ArrayList<Lazers> la;
	private Rectangle hitbox;
	public Player(Level l, int x1, int y1, int speed1, Color c) {
		ll = l;
		x = x1;
		y = y1;
		speed = speed1;
		cc = c;
		width = ll.getWidth()/30;
		height = ll.getHeight()/30;
		widthl = ll.getWidth();
		heightl = ll.getHeight();
		l1 = true;
		r1 = false;
		d1 = false;
		u1 = false;
		l2 = false;
		r2 = false;
		d2 = false;
		u2 = false;
		la = new ArrayList<Lazers>();
		la.add(new Lazers(this, Color.BLUE));
		hitbox = new Rectangle(x, y, width, height);
		shootLeft=false;
		//runn();
		
	}
	public Level getLevel() {
		return ll;
	}
	public Rectangle getHitbox() {
		 return hitbox;
	 } 
	private void runn() {
		
			t = new Thread() {
				@Override
				public void run() {
					while(true) {
						try {
							if(!la.isEmpty()) {
								for(Lazers l2 : la) {
									l2.firing();
								}
								
									Thread.sleep(5);
								
								}
							}
						 catch (java.util.ConcurrentModificationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			};
			t.start();
		
	}
	public ArrayList<Lazers> getLazers() {
		return la;
	}
	public boolean upBlock() {
		return u2;
	}
	public boolean downBlock() {
		return d2;
	}
	public boolean leftBlock() {
		return l2;
	}
	public boolean rightBlock() {
		return r2;
	}
	public boolean isUp() {
		return u1;
	}
	public boolean isDown() {
		return d1;
	}
	public boolean isRight() {
		return r1;
	}
	public boolean isLeft() {
		return l1;
	}
	public void firing() {
		Lazers lol = new Lazers(this, Color.blue);
		//System.out.println("worjs");
		if(!shootLeft) {
		if(u1 == true) {
			lol.FireUp();
		}
		if(d1 == true) {
			lol.FireDown();
		}
		if(r1 == true) {
			lol.FireRight();
		}
		if(l1 == true) {
			lol.FireLeft();
		}
		}
		else {
			lol.ModFireLeft();
		}
		la.add(lol);
	}
	public void boundaryCheck() {
		int x1 = x-speed;
		int x2 = x+speed;
		int y1 = y-speed;
		int y2 = y+speed;
		if(x1 <= 0) {
			//x+=speed;
			l2=true;
		}
		if(x2 >= widthl-width) {
			//x-=speed;
			r2=true;
		}
		if(y1 <= 0) {
			//y+=speed;
			u2=true;
		}
		if(y2 >= heightl-height) {
			//y-=speed;
			d2=true;
		}
		//checking
		if(!(x1 <= 0)) {
			l2=false;
		}
		if(!(x2 >= widthl-width)) {
			//x-=speed;
			r2=false;
		}
		if(!(y1 <= 0)) {
			//y+=speed;
			u2=false;
		}
		if(!(y2 >= heightl-height)) {
			//y-=speed;
			d2=false;
		}
		hitbox = new Rectangle(x, y, width, height);
	}
	public void moveleft() {
		
		l1=true;
		
		r1=false;
		d1=false;
		u1=false;
	}
	public void moveRight() {
		r1=true;
		
		d1=false;
		u1=false;
		l1=false;
	}
	public void moveUp() {
        r1=false;
		
		d1=false;
		u1=true;
		l1=false;
	}
	public void moveDown() {
		 r1=false;
			
		d1=true;
		u1=false;
		l1=false;
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getSpeed() {
		return speed;
	}
	public void moveX(int xmod) {
		x = xmod;
	}
	public void moveY(int ymod) {
		y = ymod;
	}
	public void draw(Graphics2D g) {
		gg = g;
		
		if(u1 == true) {
			gg.setColor(cc);
			gg.fillRect(x, y, height, width);
			gg.setColor(Color.GREEN);
			gg.fillRect(x, y, height, width/3);
		}
		else if(d1 == true) {
			gg.setColor(cc);
			gg.fillRect(x, y, height, width);
			gg.setColor(Color.GREEN);
			gg.fillRect(x, y+height, height, width/3);
		}
		else if(r1 == true) {
			gg.setColor(cc);
			gg.fillRect(x, y, width, height);
			gg.setColor(Color.GREEN);
			gg.fillRect(x+width-width/3, y, width/3, height);	
		}
		else if(l1 == true) {
			gg.setColor(cc);
			gg.fillRect(x, y, width, height);
			gg.setColor(Color.GREEN);
			gg.fillRect(x, y, width/3, height);	
		}
		
		
	}
}
