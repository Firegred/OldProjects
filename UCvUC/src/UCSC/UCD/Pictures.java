package UCSC.UCD;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public class Pictures {

	public BufferedImage plane;
	public BufferedImage towers;
	public BufferedImage explosion;
	public BufferedImage desert;
	public ImageIcon desert2;
	public AudioInputStream meme;
	public AudioInputStream boom;
	public Clip clip1;
	public Clip clip2;
	
	public Pictures() {
		URL resource1 = Graphic.class.getResource("/resources/plane.png");
		URL resource2 = Graphic.class.getResource("/resources/tower.gif");
		URL resource3 = Graphic.class.getResource("/resources/explosion.jpg");
		URL resource4 = Graphic.class.getResource("/resources/desert.jpg");
		try {
			boom = AudioSystem.getAudioInputStream(Graphic.class.getResource("/resources/boom.wav"));
			meme = AudioSystem.getAudioInputStream(Graphic.class.getResource("/resources/song.wav"));
		} catch (UnsupportedAudioFileException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			clip1 = AudioSystem.getClip();
			clip2 = AudioSystem.getClip();
			plane = ImageIO.read(resource1);
			towers = ImageIO.read(resource2);
			explosion = ImageIO.read(resource3);
			desert = ImageIO.read(resource4);
			//desert2 = new ImageIcon(resource4);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void playSong() {
		try {
			clip1.open(meme);
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip1.start();
	}
	public void playEnd() {
		try {
			clip2.open(boom);
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip2.start();
	}
	public BufferedImage getPlaneImage() {
		return plane;
	}
	public BufferedImage getTowerImage() {
		return towers;
	}
	public BufferedImage getExplosionImage() {
		return explosion;
	}
	public BufferedImage getDesertImage() {
		return desert;
	}
	public ImageIcon getDesert2() {
		return desert2;
	}
	public void drawPlane(Slugs p, int x, int y, Graphics2D g) {
		g.drawImage(plane, x, y,plane.getWidth(), plane.getHeight(),null);
		p.setBounds(x, y,(int)plane.getWidth(), (int)plane.getHeight());
	}
	public void drawDesert(Graphics2D g) {
		g.drawImage(desert, 0,0,640*2,480*2,null);
	}
	public void drawEnd(Graphics2D g) {
		g.drawImage(explosion, 0,0,640*2,480*2,null);
	}
}
