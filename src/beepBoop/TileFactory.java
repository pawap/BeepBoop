package beepBoop;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class TileFactory {

	final int GRASS = 0;
	final int EARTH = 1;
	final int ROCK = 2;
	static HashMap<Integer,Tile[]> tiles = new HashMap<Integer,Tile[]>(); ;
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
		Tile[] gArray, eArray, rArray;
		int BMPsPerType = BmpTile.getBMPsPerType();
		gArray = new Tile [BMPsPerType];
		eArray = new Tile [BMPsPerType];
		rArray = new Tile [BMPsPerType];

		for (int i = 0; i < BMPsPerType; i++) {
			try {
				gArray[i] = new BmpTile("grass", i, true);
				eArray[i] = new BmpTile("earth", i, true);
				rArray[i] = new BmpTile("rock", i, false);
			} catch (TileTypeException e) {
				e.getMessage();
			}
		}
		//#######GRASS######
		tiles.put(0, gArray);

		//#######EARTH######
		tiles.put(1, eArray);

		//#######ROCK######
		tiles.put(2, rArray);

		loaded = true;
	}


	public Tile get(int tileId, int bmpNo) {
		return tiles.get(tileId)[bmpNo];

	}

}
