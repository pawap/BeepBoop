package beepBoop.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import beepBoop.MyBMPFile;

public class BmpTile extends Tile {
	private Image image;

	@Override
	public Image getImage() {
		return image;
	}
	
	public BmpTile(String tileName, boolean walkable) {
		super();
		this.setWalkable(walkable);
		String type = tileName.substring(0, tileName.length() - 2)
				.toLowerCase();
		String bmpNo = tileName.substring(tileName.length() - 1);
        String filename = "assets" + File.separator + type + bmpNo + ".bmp";
        try {
			image = MyBMPFile.read(filename);
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
