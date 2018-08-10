package beepBoop.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * The model class for beepbop resources, Resources are Things in a level. The type of a resource is defined by its name.
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class Resource extends Thing {

	private static final long serialVersionUID = 7834668969195871400L;
	protected int amount;
	private String name;
	
	/**
	 * Constructor
	 * @param amount the amount of this Resource
	 * @param tileId one of the static values stored in TileFactory
	 * @param name the type of this Resource
	 */
	public Resource(int amount, int tileId, String name) {
		super(tileId);
		this.amount = amount;
		this.name = name;		
	}

	/**
	 * Decrease this Resource and return the amount it has been decreased by.
	 * @param quantity the desired quantity to take
	 * @return the actual amount taken
	 */
	public int takeAmount(int quantity) {
		int taken = Math.min(quantity, amount);
		amount -= taken;
		return taken;
	}

	/**
	 * @return the name of the Resource
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the amount of the Resource
	 */
	public int getAmount() {
		return this.amount;
	}
	
	/**
	 * Increase the amount of the Resource.
	 * @param increase the amount the Resource should be increased by
	 */
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

	/**
	 * Change the name of the Resource
	 * @param name the desired name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
