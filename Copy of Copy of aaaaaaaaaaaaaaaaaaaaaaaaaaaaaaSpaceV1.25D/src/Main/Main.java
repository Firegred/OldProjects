package Main;

import javax.swing.JFrame;

public class Main implements Runnable{

	private JFrame frame;
	private int width, height;
	private double scale;
	private int fps = 0, updates = 0;
	private volatile boolean running = false;
	private Thread thread;
	private Display d;
	public Main() {
		scale=2.5;
		width=(int)(480*scale);
		height=(int)(360*scale); 
		frame = new JFrame("game");
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		start();
		d = new Display(width, height, frame);
		frame.add(d);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		Main m = new Main();
		
	}
	public void start() {
		thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {
		running = true;
		long timer = System.currentTimeMillis();
		while(running == true) {
			updates++;
			fps++;
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("updates: " + updates + " Fps: " + fps);
				timer = System.currentTimeMillis();
				updates = 0;
			    fps = 0;
			}
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
