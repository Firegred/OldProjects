import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Toolkit;

import me.Firegred.Graphics.GameFrame;

public class Startup {
 
	public GameFrame f;
	public Startup() {
		int w = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int h = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		f = new GameFrame(w,h);
	}
	
	public static void main(String[] args) {
		Startup m = new Startup();
		
	}
}
