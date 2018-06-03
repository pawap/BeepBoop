package beepBoop.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class RobotTerminal extends Thing {
	
	private BufferedImage img;
	
	public RobotTerminal() {
		super(TileFactory.TERMINAL);
		img = new BufferedImage(Tile.SIZE.width,Tile.SIZE.height,BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,Tile.SIZE.width, Tile.SIZE.height);
		g.setColor(Color.WHITE);
		g.drawRect(3,3,13,13);
		g.drawLine(5, 5, 15, 5);
		g.drawLine(5, 7, 10, 7);
	}
	@Override
	public Image getImage() {
		
		return img;
	}

	
}
