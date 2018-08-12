package beepBoop.service;

import java.util.HashMap;

import beepBoop.model.BmpTile;
import beepBoop.model.Tile;

/**
 * PTP 2018
 * BeepBoop - the RobotGame
 * 
 * Grants access to all Tiles used in BeepBoop. Use getInstance() to
 * get access to the singleton instance of TileFactory.
 * TileFactory holds each TileId as a public static int.
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class TileFactory {
	//ground tile ids
	public static final int GRASS_0 = 1;
	public static final int GRASS_1 = 2;
	public static final int GRASS_2 = 3;
	public static final int EARTH_0 = 4;
	public static final int EARTH_1 = 5;
	public static final int EARTH_2 = 6;
	public static final int ROCK_0 = 7;
	public static final int ROCK_1 = 8;
	public static final int ROCK_2 = 9;
	
	//ground tile type offsets
	public static final int GRASS_OFFSET = 1;
	public static final int EARTH_OFFSET = 4;
	public static final int ROCK_OFFSET = 7;
	
	//ressource tile ids
	public static final int COPPER = 10;
	public static final int GOLD = 11;
	public static final int IRON = 12;
	public static final int PLATINUM = 13;
	public static final int SILICON = 14;
	
	//player and terminal tile ids
	public static final int PLAYER = 15;
	public static final int TERMINAL = 16;
	
	//robot tile ids
	public static final int ROBOT_0 = 17;
	
	//imageless tile id
	public static final int NULL_TILE = 0;
		
	
	private static HashMap<Integer,Tile> tiles = new HashMap<Integer,Tile>();
	private static TileFactory tileFactory = null;

	//private constructor, prevents instantiation, loads ground tiles
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

	//preloads all tiles for later use
	private void loadTiles() {

		//#######GRASS######
		tiles.put(GRASS_0, new BmpTile("grass0", true));
		tiles.put(GRASS_1, new BmpTile("grass1", true));
		tiles.put(GRASS_2, new BmpTile("grass2", true));

		//#######EARTH######
		tiles.put(EARTH_0, new BmpTile("earth0", true));
		tiles.put(EARTH_1, new BmpTile("earth1", true));
		tiles.put(EARTH_2, new BmpTile("earth2", true));

		//#######ROCK######
		tiles.put(ROCK_0, new BmpTile("rock0", false));
		tiles.put(ROCK_1, new BmpTile("rock1", false));
		tiles.put(ROCK_2, new BmpTile("rock2", false));
		
		//######RESOURCES######
		tiles.put(COPPER, new BmpTile("copper", false));
		tiles.put(GOLD, new BmpTile("gold", false));
		tiles.put(IRON, new BmpTile("iron", false));
		tiles.put(PLATINUM, new BmpTile("platinum", false));
		tiles.put(SILICON, new BmpTile("silicon", false));
		
		//######misc######
		tiles.put(PLAYER, new BmpTile("player", false));
		tiles.put(ROBOT_0, new BmpTile("robot0", false));
		tiles.put(TERMINAL, new BmpTile("terminal", false));
		tiles.put(NULL_TILE, null);
	}

	/**
	 * Returns the tile with the specified Id. Tile Ids can be accessed via the static fields of TileFactory.
	 * @param tileId the Id of the desired tile
	 * @return the desired tile
	 */	
	public Tile get(int tileId) {
		return tiles.get(tileId);
	}

	/**
	 * Maps a Resource name to the corresponding Tile
	 * @param name an element of {"copper", "gold", "iron", "platinum", "silicon"}
	 * @return the Tile ID of the Resource, or the ID of the null Tile, if the argument was invalid
	 */
    public static int getTileIdForResource(String name)
    {
        int id = NULL_TILE; 
        switch (name.toLowerCase()) {
            case "copper": id = COPPER; break;
            case "gold": id = GOLD; break;
            case "iron": id = IRON; break;
            case "platinum": id = PLATINUM; break;
            case "silicon": id = SILICON; break;
        }
        return id;
    }
}
