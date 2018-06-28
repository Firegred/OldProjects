package Level;

import java.util.Random;

import Sprites.Coin;
import Sprites.Grass;
import Sprites.Tile;
import Sprites.Wall;

public class Level extends LevelFormat {

	private int modifier;
	public Level(int w, int h, int m) {
		super(w, h);
		modifier = m;
		defineTiles();
		
		// TODO Auto-generated constructor stub
	}
	private void defineTiles() {
	    Tile[][] t = geTiles();
	    for(int x = 0; x < t.length; x++) {
	    	for(int y = 0; y < t[x].length; y++) {
	    		t[x][y] = new Grass(32*x, 32*y);
	    	}
	    }
	    for(int i = 0; i < t.length; i++) {
	    	t[i][0] = new Wall(32*i, 0);
	    }
	    for(int i = 0; i < t.length; i++) {
	    	t[i][28] = new Wall(32*i, 32*28);
	    }
	    for(int i = 0; i < t[0].length; i++) {
	    	t[0][i] = new Wall(0,32*i);
	    }
	    for(int i = 0; i < t[39].length; i++) {
	    	t[39][i] = new Wall(32*39,32*i);
	    }
	    Random r = new Random();
	    for(int i = 0; i < 50 + (25)*(modifier-1); i++) {
		    int x = r.nextInt(40);
		    int y = r.nextInt(30);
	    	t[x][y] = new Wall(32*x, 32*y);
	    }
	    int x = r.nextInt(40);
	    int y = r.nextInt(29);
	    t[x][y] = null;
	    t[x][y] = new Coin(32*x,32*y);
	    tileDefine(t);
	    
	}
	
}
