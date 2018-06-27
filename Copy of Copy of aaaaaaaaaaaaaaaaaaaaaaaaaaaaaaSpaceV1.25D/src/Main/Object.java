package Main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Object {
      
	 private int x1, y1, width1, height1;
	 private Color c1;
	 private Graphics2D g2;
	 private Rectangle hitbox;
	 private boolean hit;
	 private EnemyLazers laz;
	 private int lazChance, lazChance2;
	 private Random rand;
	 private boolean lazers;
	 private double speed;
	 public Object(int x, int y, int width, int height, Color c) {
		 x1 = x;
		 y1 = y;
		 width1 = width;
		 height1 = height;
		 c1 = c;
		 hitbox = new Rectangle(x1, y1, width1, height1);
		 hit = false;
		 lazChance = 1000;
		 lazers = false;
	 }
	 public boolean hasLazers() {
		 return lazers;
	 }
	 public int getChance() {
		 return lazChance;
	 }
	 public void incChance() {
		 lazChance--;
	 }
	 public EnemyLazers getLazer() {
		 return laz;
	 }
	 public int getWidth() {
		 return width1;
	 }
	 public int getHeight() {
		 return height1;
	 }
	 public void setHit(boolean s) {
		 hit = s;
	 }
	 public void moveRight() {
		 rand = new Random();
		 lazChance2 = rand.nextInt(lazChance);
		 if(hit == true) {
			 x1=-10000;
			 y1=-10000;
			 hitbox = new Rectangle(x1, y1, width1, height1);
		 }
		 else {
		 if(lazChance2 == 0) {
			 laz = new EnemyLazers(this);
			 lazers = true;
		 }
		 x1++;
		 hitbox = new Rectangle(x1, y1, width1, height1);
		 }
	 }
	 public Rectangle getHitbox() {
		 return hitbox;
	 }
	 public void setHitbox(Rectangle l) {
		 hitbox = l;
	 }
	 public void draw(Graphics2D gg) { 
		 g2 = gg;
		 if(hit == false) {
		 g2.setColor(c1);
		 g2.fillRect(x1, y1, width1, height1);
		 }
	 }
	 public int getX() {
		 return x1;
	 }
	 public int getY() {
		 return y1;
	 }
	 public void ymod(int mod) {
		 y1+=mod;
	 }
}
