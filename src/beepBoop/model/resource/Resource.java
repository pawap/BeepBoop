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

abstract public class Resource extends Thing {
	protected int amount;
	private String name;
	
	public Resource(int amount, int tileId, String type) {
		super(tileId);
		this.amount = amount;
		this.name = StringUtils.capitalize(type);		
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
	
	@Override
	public boolean equals (Object o) {
		return o.hashCode() == this.hashCode();
	}

	@Override
	public int hashCode() {
		return this.name.hashCode() * 42;
	}
}
