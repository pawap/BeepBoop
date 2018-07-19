package beepBoop.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class DummyTile extends Tile {

	private Image img;	
	
	@Override
	public Image getImage() {
		return img;
	}

	public DummyTile(Color color, boolean walkable) {
		super();
		setWalkable(walkable);
		img = new BufferedImage(Tile.SIZE.width,Tile.SIZE.height,BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(color);
		g.fillRect(0,0,Tile.SIZE.width, Tile.SIZE.height);	
	}

}
