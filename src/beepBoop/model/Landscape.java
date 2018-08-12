package beepBoop.model;

import java.awt.Dimension;
import java.io.Serializable;

import beepBoop.service.TileFactory;

/**
 * PTP 2018
 * BeepBoop - the RobotGame
 * 
 * A Landscape holds a grid of tiles. Each BeepBoop Level uses a Landscape which defines the basic terrain. 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class Landscape implements Serializable{

	private static final long serialVersionUID = -4911774013421889355L;
	private Dimension size;
	private int[][] tiles;
	
	/**
	 * Constructor
	 * @param size the width and height of the Landscape measured in tiles
	 */
	public Landscape(Dimension size) {
		this.size = size;
		tiles = new int[size.width][size.height];
	}
	
	/**
	 * @return the width and height of the Landscape measured in tiles
	 */
	public Dimension getSize() {
		return size;
	}

	/**
	 * Set the size of the Landscape. As many of the old Tiles as fit in the new Dimension are kept.
	 * @param size the new width and height
	 */
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

	/**
	 * Place a rectangle of Tiles of the same type
	 * @param x the x coordinate of the top left corner of the rectangle
	 * @param y the y coordinate of the top left corner of the rectangle
	 * @param x2 the x coordinate of the bottom right corner of the rectangle
	 * @param y2 the y coordinate of the bottom right corner of the rectangle
	 * @param type the desired Tile type. One of the static values stored in TileFactory.
	 */
	public void placeRect(int x, int y, int x2, int y2, int type) {
		if (isInsideDimensions(x,y) && isInsideDimensions(x2, y2)) {
			for (int i = x; i <= x2; i++) {
				for (int j = y; j <= y2; j++){
					int id = (i + j) % 3 + type;
					tiles[i][j] = id;
				}
			}
		}
		else {
			throw new IllegalArgumentException("Tried to place tile outside of landscape.");
		}
	}


	/**
	 * Place one Tile
	 * @param x the x coordinate of the Tile to be placed
	 * @param y the y coordinate of the Tile to be placed
	 * @param tileId the desired Tile type. One of the static values stored in TileFactory.
	 */
	public void place(int x, int y, int tileId) {
		if (isInsideDimensions(x,y)) {
		tiles[x][y] = tileId;
		}
		else {
			throw new IllegalArgumentException("Tried to place tile outside of landscape.");
		}
	}
	
	/**
	 * Access a specific Tile.
	 * @param x the x coordinate of the Tile
	 * @param y the y coordinate of the Tile
	 * @return the Tile at the specified coordinates of the Landscape
	 */
	public Tile getTile(int x, int y) {
		TileFactory tf = TileFactory.getInstance();
		return tf.get(tiles[x][y]);
	}
	
    /**
     * Check if a position is inside the Landscape.
     * @param x the x coordinate of the position
     * @param y the y coordinate of the position
     * @return true if the position is inside the dimensions of the Landscape
     */
	public boolean isInsideDimensions(int x, int y) {
		boolean xIsCorrect = x >= 0 && x < size.width;
		boolean yIsCorrect = y >= 0 && y < size.height;
		return xIsCorrect && yIsCorrect;
	}	

}
