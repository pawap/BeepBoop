package beepBoop.model;

import java.awt.Dimension;
import java.awt.Image;

/**
 * PTP 2018 BeepBoop - the RobotGame
 * 
 * Tiles hold the Images to be displayed in the GUI. Each Thing has a Tile. The
 * Landscape is basically a 2d array of Tile IDs, so that each grid is
 * associated with a Tile.
 * 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
abstract public class Tile {
	/**
	 * This square of pixels makes up the basic area unit of BeepBoop
	 */
	public final static Dimension SIZE = new Dimension(20, 20);

	private boolean walkable;

	/**
	 * @return the Image corresponding to the Tile
	 */
	abstract public Image getImage();

	/**
	 * A Tile is considered walkable if it or the Thing it belongs to not
	 * exclusively occupy one grid space.
	 * 
	 * @return true, if the Tile is walkable
	 */
	public boolean isWalkable() {
		return walkable;
	}

	/**
	 * A Tile is considered walkable if it or the Thing it belongs to not
	 * exclusively occupy one grid space.
	 * 
	 * @param walkable
	 */
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

}
