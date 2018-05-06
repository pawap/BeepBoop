package beepBoop;

import java.util.HashMap;

/**
 * TileFactory manages the ground tiles. To instantiate the TileFactory use getInstance(). 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class TileFactory {

	/**
	 * The id for grass type tiles
	 */
	public final int GRASS = 0;
	
	/**
	 * The id for earth type tiles
	 */
	public final int EARTH = 1;
	
	/**
	 * The id for rock type tiles
	 */
	public final int ROCK = 2;
	
	private static HashMap<Integer,Tile[]> tiles = new HashMap<Integer,Tile[]>(); ;
	private static TileFactory tileFactory = null;
	private static boolean loaded = false;

	/**
	 * The default constructor is private. To get a TileFactory instance use getInstance(). 
	 */
	private TileFactory(){

	}

	/**
	 * Use this method to get the instance of TileFactory. If the TileFactory has not been
	 * instantiated, getInstance() creates a new instance and loads the tiles first. 
	 * @return the TileFactory instance
	 */
	public static TileFactory getInstance() {
		if (TileFactory.tileFactory == null) {
			TileFactory.tileFactory = new TileFactory();
			TileFactory.tileFactory.loadTiles();
		}
		return TileFactory.tileFactory;
	}	

	/**
	 * preloads the different tiles.
	 */
	private void loadTiles() {
		if (TileFactory.loaded) {
			return;
		}
		Tile[] gArray, eArray, rArray;
		int BMPsPerType = BmpTile.BMPSPERTYPE;
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
		tiles.put(GRASS, gArray);

		//#######EARTH######
		tiles.put(EARTH, eArray);

		//#######ROCK######
		tiles.put(ROCK, rArray);

		loaded = true;
	}

	/**
	 * Returns a specific tile. The tileId references the type. To access the id corresponding to a type,
	 * use TileFactory.<TYPE> (e.g TileFactory.GRASS). The bmpNo should be an int between 0 and BmpTile.BMPSPERTYPE
	 * @param tileId The id corresponding to the desired tile type
	 * @param bmpNo the No of the desired bmp
	 * @return the desired tile
	 */
	public Tile get(int tileId, int bmpNo) {
		return tiles.get(tileId)[bmpNo];

	}

}
