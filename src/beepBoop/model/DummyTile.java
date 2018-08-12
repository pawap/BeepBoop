package beepBoop.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * PTP 2018 BeepBoop - the RobotGame
 * 
 * Simple monocolored tiles intended for testing purposes.
 * 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class DummyTile extends Tile {

	private Image img;

	@Override
	public Image getImage() {
		return img;
	}

	/**
	 * Constructor
	 * 
	 * @param color
	 *            the desired color of the tile
	 * @param walkable
	 *            true, if the player and robots can step on this tile while it
	 *            is used as a ground tile
	 */
	public DummyTile(Color color, boolean walkable) {
		super();
		setWalkable(walkable);
		img = new BufferedImage(Tile.SIZE.width, Tile.SIZE.height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(color);
		g.fillRect(0, 0, Tile.SIZE.width, Tile.SIZE.height);
	}

}
