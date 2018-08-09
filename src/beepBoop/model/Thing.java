package beepBoop.model;

import java.awt.Image;
import java.awt.Point;
import java.io.Serializable;
import java.util.Observable;

/**
 * Everything but Landscape Tiles in BeepBoop is a Thing.
 * Thing is the superclass for the Player, Resources, Terminals and Robots.
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
abstract public class Thing extends Observable implements Serializable{

	private static final long serialVersionUID = 892751048713013543L;
	private Point position;
	private transient Tile tile;
	private int tileId;
	
	/**
	 * Constructor
	 * @param tileId the ID for the Tile of the Thing, should be one of the static values stored in TileFactory 
	 */
	public Thing(int tileId) {
		TileFactory tf = TileFactory.getInstance();
		this.tileId = tileId;
		this.tile = tf.get(tileId);
	}

	/**
	 * @return the position of the Thing in the Landscape
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * Set the position of the Thing in the Landscape.
	 * @param position the desired position
	 */
	public void setPosition(Point position) {
		this.position = position;
	}
	
	/**
	 * @return the Image of the Thing's Tile
	 */
	public Image getImage() {
		return tile.getImage();
	}
	
	/**
	 * A Thing is considered walkable if its Tile is walkable.
	 * @return true if the Player and robots can be in the same position as the Thing
	 */
	public boolean isWalkable() {
		return tile.isWalkable();
	}

	/**
	 * @return the tileId of the Thing
	 */
	public int getTileId() {
		return tileId;
	}

	/**
	 * Sets the tile of a thing to the tile corresponding to tileId. Should be called after deserialization.
	 * @param tileId
	 */
	public void setTile(int tileId) {
		TileFactory tf = TileFactory.getInstance();
		this.tile = tf.get(tileId);
	}
}
