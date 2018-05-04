package beepBoop;

import java.awt.Dimension;
import java.awt.Image;

abstract public class Tile {
	
	final static Dimension SIZE = new Dimension(10,10);
	
	private boolean walkable;
	
	abstract public Image getTile();

	public boolean isWalkable() {
		return walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
}
