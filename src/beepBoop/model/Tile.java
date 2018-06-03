package beepBoop.model;

import java.awt.Dimension;
import java.awt.Image;

abstract public class Tile {
	
	public final static Dimension SIZE = new Dimension(20,20);
	
	private boolean walkable;
	
	abstract public Image getImage();

	public boolean isWalkable() {
		return walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

}
