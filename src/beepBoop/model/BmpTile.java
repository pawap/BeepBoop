package beepBoop.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * PTP 2018
 * BeepBoop - the RobotGame
 * 
 *  A Tile holding a bitmap graphic.
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class BmpTile extends Tile {
	private Image image;

	@Override
	public Image getImage() {
		return image;
	}
	
	/**
	 * Constructor
	 * The tile name should match a .bmp file located in the assets folder. If it doesn't or the file cannot be accessed,
	 * a red "error-tile" is created instead. 
	 * @param tileName
	 * @param walkable true, if the player and robots can step on this tile while it is used as a ground tile
	 */
	public BmpTile(String tileName, boolean walkable) {
		super();
		this.setWalkable(walkable);
		String filename = "assets" + File.separator + tileName.toLowerCase() + ".bmp";
        //try to load the image from the corresponding bmp-file, create a red errorTile if this fails.
		try {
			image = ImageIO.read(new File(filename));
		} catch (IOException e) {
			System.out.println("File " + filename + " could not be read.");
			System.out.println(e.getMessage());
			image = new BufferedImage(Tile.SIZE.width,Tile.SIZE.height,BufferedImage.TYPE_INT_ARGB);
			Graphics g = image.getGraphics();
			g.setColor(Color.red);
			g.fillRect(0,0,Tile.SIZE.width, Tile.SIZE.height);
		}		
	}

}
