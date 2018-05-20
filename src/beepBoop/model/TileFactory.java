package beepBoop.model;

import java.util.HashMap;

public class TileFactory {
	//tile ids
	public static final int GRASS_0 = 0;
	public static final int GRASS_1 = 1;
	public static final int GRASS_2 = 2;
	public static final int EARTH_0 = 3;
	public static final int EARTH_1 = 4;
	public static final int EARTH_2 = 5;
	public static final int ROCK_0 = 6;
	public static final int ROCK_1 = 7;
	public static final int ROCK_2 = 8;
	
	//tile type offsets
	public static final int GRASS = 0;
	public static final int EARTH = 3;
	public static final int ROCK = 6;
	
	private static HashMap<Integer,Tile> tiles = new HashMap<Integer,Tile>();
	private static TileFactory tileFactory = null;

	//prevents instantiation, loads tiles
	private TileFactory(){
		loadTiles();		
	}

	/**
	 * Returns the instance of the TileFactory singleton, creates it if necessary.
	 * @return the singleton TileFactory instance
	 */
	public static TileFactory getInstance() {
		if (TileFactory.tileFactory == null) {
			TileFactory.tileFactory = new TileFactory();
		}
		return TileFactory.tileFactory;
	}	

	private void loadTiles() {

		//#######GRASS######
		tiles.put(GRASS_0, new BmpTile("grass 0", true));
		tiles.put(GRASS_1, new BmpTile("grass 1", true));
		tiles.put(GRASS_2, new BmpTile("grass 2", true));

		//#######EARTH######
		tiles.put(EARTH_0, new BmpTile("earth 0", true));
		tiles.put(EARTH_1, new BmpTile("earth 1", true));
		tiles.put(EARTH_2, new BmpTile("earth 2", true));

		//#######ROCK######
		tiles.put(ROCK_0, new BmpTile("rock 0", true));
		tiles.put(ROCK_1, new BmpTile("rock 1", true));
		tiles.put(ROCK_2, new BmpTile("rock 2", true));

	}

	/**
	 * Returns the tile with the specified Id. Tile Ids can be accessed via the static fields of TileFactory.
	 * @param tileId the Id of the desired tile
	 * @return the desired tile
	 */	
	public Tile get(int tileId) {
		return tiles.get(tileId);
	}

}
