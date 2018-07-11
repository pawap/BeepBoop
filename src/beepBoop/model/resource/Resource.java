package beepBoop.model.resource;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import com.sun.xml.internal.ws.util.StringUtils;

import beepBoop.model.Thing;
import beepBoop.model.Tile;

public class Resource extends Thing {

	private static final long serialVersionUID = 7834668969195871400L;
	protected int amount;
	private String name;
	
	public Resource(int amount, int tileId, String name) {
		super(tileId);
		this.amount = amount;
		this.name = name;		
	}

	public int takeAmount(int quantity) {
		int taken = Math.min(quantity, amount);
		amount -= taken;
		return taken;
	}

	public String getName() {
		return name;
	}

	public int getAmount() {
		return this.amount;
	}
	
	public void increaseAmount(int increase) {
		this.amount += increase;
	}
	
	@Override
	public Image getImage() {
		Image reImg = new BufferedImage(Tile.SIZE.width,Tile.SIZE.height,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) reImg.getGraphics();
		g.drawImage(super.getImage(), 0, 0, null);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		g.setFont(new Font("Verdana", Font.BOLD, Tile.SIZE.width /2 -1));
		g.setColor(Color.WHITE);
		g.drawString(""+amount, 0, Tile.SIZE.width /2 -1);
		return reImg;
	}

	public void setName(String name) {
		this.name = name;
	}

}
