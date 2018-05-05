package beepBoop;

import java.awt.Color;
import java.util.HashMap;

public class TileFactory {

	final int GRASS = 0;
	final int EARTH = 1;
	final int ROCK = 2;
	static HashMap<Integer,Tile> tiles = new HashMap<Integer,Tile>(); ;
	private static TileFactory tileFactory = null;
	private static boolean loaded = false;

	private TileFactory(){

	}

	public static TileFactory getInstance() {
		if (TileFactory.tileFactory == null) {
			TileFactory.tileFactory = new TileFactory();
		}
		return TileFactory.tileFactory;
	}	

	public void loadTiles() {
		if (TileFactory.loaded) {
			return;
		}

		try {
			//#######GRASS######
			tiles.put(0, new BmpTile("grass", true));

			//#######EARTH######
			tiles.put(1, new BmpTile("earth", true));

			//#######ROCK######
			tiles.put(2, new BmpTile("rock", false));

		} catch (TileTypeException e) {
			e.printStackTrace();
		}

		loaded = true;
	}


	public Tile get(int tileId) {

		return tiles.get(tileId);

	}

}
