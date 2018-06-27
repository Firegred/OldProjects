package Main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Boss {

	private int width, height, x ,y, speed, life, xspeed, yspeed, olife;
	private Random rand;
	private Level ll;
	private boolean intros, alive;
	private ArrayList<BossLazer> laz;
	private Rectangle rec;
	private Color c;
	private boolean up, right;
	private Graphics2D gg;
	public Boss(Level l, int xx, int yy, int li, boolean a) {
	ll = l;
	x = xx;
	y = yy;
	width = ll.getWidth()/5;
	height = ll.getHeight()/5;
	speed = width/90*(l.mult()+1);
	rand = new Random();
	life = li;
	intros = true;
	alive = a;
	rec = new Rectangle(x, y, width, height);
	up = true;
	right = true;
	c = Color.white;
	xspeed = speed;
	yspeed = speed;
	laz = new ArrayList<BossLazer>();
	BossLazer ls = new BossLazer(this);
	laz.add(ls);
	olife = life;
	}
	public ArrayList<BossLazer> getLazers() {
		return laz;
	}
	public boolean isAlive() {
		return alive;
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
	public Rectangle getHitBox() {
		return rec;
	}
    public int getLife() {
    	return life;
    }
    public void ReduceLife() {
    	life--;
    }
    public void kill() {
    	x = 10000;
    	y = 10000;
    	rec = new Rectangle(x, y, width, height);
    	alive = false;
    }
	public void intro() {
		if(intros==true) {
		x+=xspeed;
		rec = new Rectangle(x, y, width, height);
		//System.out.println(x);
		if(x >= 0) {
			intros=false;
			xspeed = speed/3;
		}
		}
	}
	public void attackLazer() {
		if(intros == false) {
			rand = new Random();
			int ran = rand.nextInt(200/(ll.mult()+1));
			if(ran == 0) {
				BossLazer ls = new BossLazer(this);
				laz.add(ls);
			}
		}
	}
	public void sway() {
		if(intros == false) {
		if(up == true) {
			y-=yspeed;
			rec = new Rectangle(x, y, width, height);
			if(y <= 0) {
				up=false;
			}
		}
		if(up == false) {
			y+=yspeed;
			rec = new Rectangle(x, y, width, height);
			if(y>= ll.getHeight()-height) {
				up = true;
			}
		}
		if(right == true) {
			x+=xspeed;
			rec = new Rectangle(x, y, width, height);
			if(x+width>=ll.getWidth()/2) {
				right = false;
			}
		}
		if(right == false) {
			x-=xspeed;
			rec = new Rectangle(x, y, width, height);
			if(x<=0) {
				right = true;
			}
		}
		}
	}
	public void drawing(Graphics2D g) {
		gg = g;
		gg.setColor(c);
		gg.fillRect(x, y, width, height);
		gg.setColor(Color.black);
		//eyes
		gg.fillRect(x+width/4, y+width/6, width/10, height/10);
		gg.fillRect(x+(width*3)/4, y+width/6, width/10, height/10);
		
		//mouth
		gg.setColor(Color.orange);
		gg.fillRect(x+width/4, y+(height*5)/6, width/10, height/10);
		gg.setColor(Color.RED);
		gg.fillRect(x+(width/4)+width/4, y+(height*4)/6, width/2, height/10);
		gg.setColor(Color.orange);
		gg.fillRect(x+(width*3)/4, y+(height*5)/6, width/10, height/10);
		Double d1 = new Double(life);
		Double d2 = new Double(olife);
		Double d3 = new Double(ll.getWidth()/3);
		Double d4 = d3*(d1/d2);
		int value2 = d4.intValue();
		gg.setColor(Color.RED);
		gg.fillRect(ll.getWidth()/3, ll.getHeight()/8, (ll.getWidth()/3), ll.getHeight()/20);
		gg.setColor(Color.green);
		gg.fillRect(ll.getWidth()/3, ll.getHeight()/8, value2, ll.getHeight()/20);
		
	}
	
	
}
