package me.Bored.main;

public class Start {

	private static Frame f;
	public static void main(String[] args) {
		f = new Frame(640*2, 480*2, false);
		f.add(new Graphic(f));
	}
    public Frame getFrame() {
    	return f;
    }
}
