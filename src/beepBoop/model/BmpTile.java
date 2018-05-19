package beepBoop.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import beepBoop.MyBMPFile;

public class BmpTile extends Tile {
	//this number should be increased when more BMPs are added. each type needs to have the same number of BMPs 
	private static final int BMPsPerType = 3;
	private Image image;

	@Override
	public Image getImage() {
		return image;
	}

	public BmpTile(String tileType, int bmpNo, boolean walkable) throws TileTypeException{
		super();
		setWalkable(walkable);
		String type = tileType.toLowerCase();
		if (isValidType(type)) {
			if (bmpNo <= BMPsPerType && bmpNo >= 0) {
				String filename = "assets" + File.separator + type + String.valueOf(bmpNo) + ".bmp";
				System.out.println("image filename = " + filename);
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
			else {
				throw new IndexOutOfBoundsException("bmpNo should be between 0 and " + String.valueOf(BMPsPerType));
			}
		}
		else {
			throw new TileTypeException(type + " is not a valid tile type");
		}
	}
	
	public static int getBMPsPerType(){
		return BMPsPerType;
		
	}

	private boolean isValidType(String type) {
		return ("earth".equals(type) || "grass".equals(type) || "rock".equals(type));
	}

}
