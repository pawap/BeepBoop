package beepBoop.model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class TileFactory {
	//ground tile ids
	public static final int GRASS_0 = 0;
	public static final int GRASS_1 = 1;
	public static final int GRASS_2 = 2;
	public static final int EARTH_0 = 3;
	public static final int EARTH_1 = 4;
	public static final int EARTH_2 = 5;
	public static final int ROCK_0 = 6;
	public static final int ROCK_1 = 7;
	public static final int ROCK_2 = 8;
	
	//ground tile type offsets
	public static final int GRASS_OFFSET = 0;
	public static final int EARTH_OFFSET = 3;
	public static final int ROCK_OFFSET = 6;
	
	//ressource tile ids
	public static final int COPPER = 9;
	public static final int GOLD = 10;
	public static final int IRON = 11;
	public static final int PLATINUM = 12;
	public static final int SILICON = 13;
	
	//player and terminal tile ids
	public static final int PLAYER = 14;
	public static final int TERMINAL = 15;
	
	//robot tile ids
	public static final int ROBOT_0 = 16;
	
	//imageless tile id
	public static final int NULL_TILE = 100;
		
	
	private static HashMap<Integer,Tile> tiles = new HashMap<Integer,Tile>();
	private static TileFactory tileFactory = null;

	//prevents instantiation, loads ground tiles
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
}
