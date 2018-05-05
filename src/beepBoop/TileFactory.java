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
		
		//#######GRASS######
		tiles.put(0, new DummyTile(Color.GREEN, true));
	
		//#######EARTH######
		tiles.put(1, new DummyTile(new Color(153,102,0), true));
		
		//#######ROCK######
		tiles.put(2, new DummyTile(Color.GRAY, false));
	
		loaded = true;
	}
	
		
	public Tile get(int tileId) {
		
		return tiles.get(tileId);
		
	}
	
}
