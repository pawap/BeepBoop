package beepBoop.model;

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
		int[][] newTiles = new int[size.width][size.height];
		int transX = Math.min(tiles.length, newTiles.length),
			transY = Math.min(tiles[0].length, newTiles[0].length);
		for(int x = 0; x < transX; x++) {
			for(int y = 0; y < transY; y++) {
				newTiles[x][y] = tiles[x][y];
			}
		}
		tiles = newTiles;
	}

	public void placeRect(int x, int y, int x2, int y2, int type) {
		for (int i = x; i <= x2; i++) {
			for (int j = y; j <= y2; j++){
				int id = (i + j) % 3 + type;
				place(i,j, id);
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
