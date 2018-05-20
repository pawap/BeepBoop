package beepBoop.model;

import java.awt.Dimension;

public class TilePattern {
	private Dimension dimension;
	private Tile[] tiles;
	
	public TilePattern (int width, int height, String tileType) {
		this.dimension = new Dimension(width, height);
	}

	public Dimension getDimension() {
		return dimension;
	}

	public Tile[] getTiles() {
		return tiles;
	}

}
