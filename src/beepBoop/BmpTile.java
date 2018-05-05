package beepBoop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class BmpTile extends Tile {
	 //this number should be increased when more BMPs are added. each type needs to have the same number of BMPs 
	private static final int BMPsPerType = 3;
	Image[] images = new Image[BMPsPerType];
	
	@Override
	public Image getImage() {
		int picker = ThreadLocalRandom.current().nextInt(0, BMPsPerType);
		return images[picker];
	}
	
	public BmpTile(String tileType, boolean walkable) throws TileTypeException{
		super();
		setWalkable(walkable);
		String type = tileType.toLowerCase();
		if (isValidType(type)) {
			for (int i = 0; i < BMPsPerType; i++) {
				String filename = "assets" + File.separator + type + String.valueOf(i) + ".bmp";
				System.out.println("image filename = " + filename);
				try {
					images[i] = MyBMPFile.read(filename);
				} catch (IOException e) {
					System.out.println("File " + filename + " could not be read.");
					System.out.println(e.getMessage());
					images[i] = new BufferedImage(Tile.SIZE.width,Tile.SIZE.height,BufferedImage.TYPE_INT_ARGB);
					Graphics g = images[i].getGraphics();
					g.setColor(Color.red);
					g.fillRect(0,0,Tile.SIZE.width, Tile.SIZE.height);
				}
			}
		}
		else {
			throw new TileTypeException(type + " is not a valid tile type");
		}
	}

	private boolean isValidType(String type) {
		return ("earth".equals(type) || "grass".equals(type) || "rock".equals(type));
	}

}
