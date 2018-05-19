package beepBoop.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Player extends Thing {

	private BufferedImage img;

	public Player() {
		super();
		img = new BufferedImage(Tile.SIZE.width,Tile.SIZE.height,BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.BLACK);
		g.fillOval(0,0,Tile.SIZE.width, Tile.SIZE.height);	
	}

	public Image getImage() {

		return img;
	}

}
