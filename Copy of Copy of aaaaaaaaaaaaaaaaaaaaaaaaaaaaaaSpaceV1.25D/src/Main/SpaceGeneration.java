package Main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class SpaceGeneration {

	private int width;
	private int height;
	private Level ll;
	private ArrayList<Point> point;
	private Random rand, rand2;
	private int r, r2, var;
	private boolean working;
	public SpaceGeneration(Level l) {
		ll = l;
		width = ll.getWidth();
		height = ll.getHeight();
		point = new ArrayList<Point>();
		working = false;
		rand = new Random();
		rand2 = new Random();
		Point d = new Point(10000, 10000);
		Point d2 = new Point(10000, 10000);
		point.add(d);
		point.add(d2);
		points();
		//addingPoints();
		movingPoints();
		var = 0;
		
	}
	public boolean isWorking() {
		return working;
	}
	public ArrayList<Point> getPoints() {
		return point;
	}
	public void setWorking(boolean b) {
		working = b;
	}
	public void movingPoints() {
		Thread tt = new Thread() {
			@Override
			public void run() {
				while(true) {
					if(working == false) {
						System.out.println("This code is strange. The only way it works is by system.out.println");
					}
					
					if(!ll.isPaused()) {
					if(working == true) {
					
					for(int i = 0; i < point.size(); i++) {
					    
						Point c = new Point((int)point.get(i).getX()+1, (int)point.get(i).getY());
						 if(ll.isBoss()) {
						    	c = new Point((int)point.get(i).getX()+2, (int)point.get(i).getY());
						    }
						if(c.getX() > ll.getWidth()) {
					    	c = new Point(0, (int)point.get(i).getY()); 
					    }
						point.set(i, c);
						
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
			}
			}
			}
		};
		tt.start();
		
	}
	
	public void addingPoints() {
		Thread c = new Thread()  {
			@Override
			public void run() {
				//while(true) {
					for(int x = var-width; x <0; x++) {
						for(int y = 0; y < height; y++) {
							r2 = rand2.nextInt(1000);
							if(r==0) {
								Point p = new Point(x, y);
								point.add(p);
							}
						}
					}
				//}
			}
		};
		c.start();
		
	}
	public void points() {
		Thread t = new Thread() {
			@Override
			public void run() {
				
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				r = rand.nextInt(1000);
				if(r == 0) {
					Point p = new Point(x, y);
					point.add(p);	
				}
				if(x == width-1 && y == height-1) {
					working = true;
					break;
				}
			}
		}
			}
		};
		t.start();
	}
}
