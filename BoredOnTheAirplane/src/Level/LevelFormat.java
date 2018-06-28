package Level;

import Sprites.Tile;

public class LevelFormat {

	public Tile[][] tiles;
	public int width, height;
	public LevelFormat(int w, int h) {
		width = w;
		height = h;
		tiles = new Tile[40][30];
	}
	public Tile[][] geTiles() {
		return tiles;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void tileDefine(Tile[][] t) {
		tiles = t;
	}
}
