package Main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class Level {
     
    private int x, y, width1, height1, r, mult;
    private Graphics2D gg;
    private Random rand;
    
    private LevelObjects o;
    private Player p;
    private Keyboard k;
    private JFrame f;
    private boolean gameover;
    private GameOver ggg;
    private boolean pause, bossmode;
    private int bossscore=50;
    private Music1 mm;
    private SpaceGeneration sg;
	public Level(int width, int height, JFrame ff) {
		this.width1 = width;
		this.height1 = height;
		rand = new Random();
		pause = false;
		bossmode = false;
		f = ff;
		mm = new Music1();
		sg = new SpaceGeneration(this);
		gameover = false;
		p = new Player(this, width/2, height/2, width/240, Color.red);
		o = new LevelObjects(width1, height1, p);
		k = new Keyboard(p);
		ggg = new GameOver(this);
		mult = o.getScore().getMultiplier();
		f.addKeyListener(k);
		//hitcontrol();
		
	}
//	public void hitcontrol() {
//		Thread t = new Thread() {
//			@Override
//			public void run() {
//				while(true) {
//					try {
//						//System.out.println("works");
//						for(Object oo : o.objects()) {
//							for(Lazers ll : p.getLazers()) {
//								if(oo.getHitbox().intersects(ll.getHitbox())) {
//									p.getLazers().remove(ll);
//									o.objects().remove(oo);
//									
//									
//									
//									
//								}
//							}
//						}
//					} catch (java.util.ConcurrentModificationException e) {
//						// TODO Auto-generated catch block
//						
//						e.printStackTrace();
//					} catch (java.lang.NullPointerException e1) {
//						
//					}
//				}
//			}
//		};
//		//t.start();
//	}
	//doesn't work
	public void setBossMode(boolean b) {
		bossmode = b;
	}
	public boolean isBoss() {
		return bossmode;
	}
	public Music1 getMusic() {
		return mm;
	}
	public int Bosses() {
		return o.getScore().getBossScore();
	}
	public JFrame getFrame() {
		return f;
	}
	public int mult() {
		return mult;
	}
	public int getBossScore() {
		return bossscore;
	}
	public boolean isPaused() {
		return pause;
	}
	public void setPaused(boolean b) {
		pause = b;
	}
	public int getScore() {
		return o.getScore().getScore();
	}
	public void setGameOver(boolean g) {
		gameover = g;
	}
	public GameOver getGameOver() {
		return ggg;
	}
	public void drawLevel(Graphics2D gg) {
	
		try {
			if(gameover == true) {
			ggg.draw(gg);
			ggg.setGameOver(true);
			//System.out.println("work");
}
else {	
gg.setColor(Color.black);
gg.fillRect(0, 0, width1, height1);

if(!sg.getPoints().isEmpty() && sg.isWorking() == true) {
			for(Point p : sg.getPoints()) {
				gg.setColor(Color.white);
				gg.fillRect((int)p.getX(), (int)p.getY(), width1/(480), height1/(360));
			
}
}
if(!o.objects().isEmpty()) {

try {
			for(Object ob : o.objects()) {
				ob.draw(gg);
				if(ob.hasLazers()) {
					ob.getLazer().draw(gg);
				}
			}
			for(Lazers lll : p.getLazers()) {
				lll.Fire(gg);
			}
if(o.getBoss().isAlive()) {
			o.getBoss().drawing(gg);
			for(BossLazer bl : o.getBoss().getLazers()) {
				bl.draw(gg);
			}
}
gg.setColor(Color.BLUE);	
o.getScore().drawScore(gg);
} catch (java.util.ConcurrentModificationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
}

}
p.draw(gg);
mult = o.getScore().getMultiplier();

}
		} catch (java.util.ConcurrentModificationException e) {
		} catch (java.lang.NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getWidth() {
		return width1;
	}
	public int getHeight() {
		return height1;
	}
}
