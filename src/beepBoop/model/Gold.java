package beepBoop.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Gold extends Ressource {

	private BufferedImage img;

	public Gold(int amount) {
		super(amount);
		img = new BufferedImage(Tile.SIZE.width,Tile.SIZE.height,BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(new Color(212,175,55));
		g.fillOval(0,0,Tile.SIZE.width / 2, Tile.SIZE.height / 2);
		g.setColor(Color.BLACK);
		g.drawOval(0,0,Tile.SIZE.width / 2, Tile.SIZE.height / 2);		
		g.setColor(new Color(217,170,45));
		g.fillOval(Tile.SIZE.width / 4,Tile.SIZE.width / 4,Tile.SIZE.width / 2, Tile.SIZE.height / 2);
		g.setColor(Color.BLACK);
		g.drawOval(Tile.SIZE.width / 4,Tile.SIZE.width / 4,Tile.SIZE.width / 2, Tile.SIZE.height / 2);
		g.setColor(new Color(222,165,35));
		g.fillOval(Tile.SIZE.width / 2,Tile.SIZE.width / 2,Tile.SIZE.width / 2, Tile.SIZE.height / 2);	
		g.setColor(Color.BLACK);
		g.drawOval(Tile.SIZE.width / 2,Tile.SIZE.width / 2,Tile.SIZE.width / 2, Tile.SIZE.height / 2);	
	}

	@Override
	public Image getImage() {
		Image image = new BufferedImage(Tile.SIZE.width,Tile.SIZE.height,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.drawImage(img, 0, 0, null);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		g.setFont(new Font("Verdana", Font.BOLD, Tile.SIZE.width /2 -1));
		g.setColor(Color.WHITE);
		g.drawString(""+amount, 0, Tile.SIZE.width /2 -1);
		return image;
	}

	@Override
	public String getName() {
		return "Gold";
	}

}
