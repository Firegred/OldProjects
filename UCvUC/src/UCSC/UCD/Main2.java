package UCSC.UCD;

public class Main2 {

	public static Frame f;
	public static Introframe in;
	public static void main(String[] args) {
	in = new Introframe();
	try {
		Thread.sleep(1000 * 2);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	in.rem();
		f = new Frame();	
	}
}
