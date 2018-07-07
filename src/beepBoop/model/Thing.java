package beepBoop.model;

import java.awt.Image;
import java.awt.Point;
import java.util.Observable;

abstract public class Thing extends Observable {
	private Point position;
	private Tile tile;
	
	public Thing(int tileId) {
		TileFactory tf = TileFactory.getInstance();
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
}
