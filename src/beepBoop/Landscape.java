package beepBoop;

import java.awt.Dimension;

public class Landscape {
	private Dimension size;
	private int[][] tiles;
	
	public Landscape(Dimension size) {
		this.size = size;
		tiles = new int[size.width][size.height];
	}
	
	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public void placeRect(int x, int y, int x2, int y2, int tileId) {
		for (int i = x; i <= x2; i++) {
			for (int j = y; j <= y2; j++){
				place(i,j,tileId);
			}
		}
	}

	public void place(int x, int y, int tileId) {
		tiles[x][y] = tileId;
		
	}
	
	public Tile getTile(int x, int y) {
		TileFactory tf = TileFactory.getInstance();
		return tf.get(tiles[x][y]);
	}
	

}
