package beepBoop.model;

import java.awt.Image;
import java.awt.Point;
import java.io.Serializable;
import java.util.Observable;

abstract public class Thing extends Observable implements Serializable{

	private static final long serialVersionUID = 892751048713013543L;
	private Point position;
	private transient Tile tile;
	private int tileId;
	
	public Thing(int tileId) {
		TileFactory tf = TileFactory.getInstance();
		this.tileId = tileId;
		this.tile = tf.get(tileId);
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	public Image getImage() {
		return tile.getImage();
	}
	
	public boolean isWalkable() {
		return tile.isWalkable();
	}

	/**
	 * @return the tileId
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
