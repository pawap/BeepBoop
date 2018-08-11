package beepBoop.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Set;

import javax.swing.JPanel;

import beepBoop.model.Level;
import beepBoop.model.Player;
import beepBoop.model.Thing;
import beepBoop.model.Tile;

/**
 * Shows the visible part of the level.
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class LevelUI extends JPanel {

	private static final long serialVersionUID = 6263785454581945653L;
	private Level level;
	private Dimension viewSize = new Dimension(20,20);
	private Point viewOrigin;
	private int maxViewOrgX;
	private int maxViewOrgY;

	/**
	 * Constructor
	 * Constructs a GUI showing hte part of the level surrounding the player.
	 * @param level
	 */
	public LevelUI(Level level) {
		super();
		this.setLevel(level);
		this.setBackground(Color.DARK_GRAY);
	}
	
	//calculate the origin point for the visible part of the map
	private void updateViewOrg() {
		Point playerPos = this.getLevel().getPlayer().getPosition();
		int viewX = (int) Math.max(0, playerPos.getX()  - (viewSize.getWidth() / 2));
		int viewY = (int) Math.max(0, playerPos.getY()  - (viewSize.getHeight() / 2));
		viewX = Math.min(maxViewOrgX, viewX);
		viewY = Math.min(maxViewOrgY, viewY);
		this.viewOrigin = new Point(viewX, viewY);
	}
	
	//maps a point from the level coordinates to the view coordinates
	private Point mapToView(Point PosInLvl) {
		int xPosInView = PosInLvl.x - viewOrigin.x;
		int yPosInView = PosInLvl.y - viewOrigin.y;
		return new Point (xPosInView, yPosInView);
	}


	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		updateViewOrg();
		paintLandscape(g);
		paintThings(g);
		paintPlayer(g);

	}

	//paints all Things of the level that are inside the view
	private void paintThings(Graphics g) {
		Set<Thing> things = getLevel().getThings();
		
		for (Thing thing : things) {
			if (thing != null) {
			    Point position = thing.getPosition(); 
			    if (inView(position)) {
				    position = mapToView(position);
			        g.drawImage(thing.getImage(),(int) position.getX() * Tile.SIZE.width, 
			    	    	                     (int) position.getY() * Tile.SIZE.height, null);
			    }
			}
		}	
	}

	//returns true iff the given point is visible on the current viewport
	private boolean inView(Point position) {
		double x = position.getX();
		double y = position.getY();
		double minX = viewOrigin.getX();
		double maxX = minX + viewSize.getWidth();
		double minY = viewOrigin.getY();
		double maxY = minY + viewSize.getHeight();
		return x >= minX && x < maxX && y >= minY && y < maxY;
	}
	
	//paints the player at the right position
	private void paintPlayer(Graphics g) {
		Player p = getLevel().getPlayer();
		Point viewPos = mapToView(p.getPosition());
				
		g.drawImage(p.getImage(), viewPos.x * Tile.SIZE.width,
				viewPos.y * Tile.SIZE.height,null);
	}
	
	//paints the visible part of the level-landscape
	private void paintLandscape(Graphics g) {
		for (int x = 0; x < viewSize.getWidth(); x++) {
			for (int y = 0; y < viewSize.getHeight(); y++){
				g.drawImage(getLevel().getLandscape().getTile(x + viewOrigin.x, y + viewOrigin.y).getImage(),
						x * Tile.SIZE.width, y * Tile.SIZE.height,null);			
			}
		}
	}

    /**
     * @return the level belonging to the LevelUI
     */
	public Level getLevel() {
		return level;
	}

    /**
     * Change the level that the LevelUI displays.
     * @param level
     */
	public void setLevel(Level level) {
		this.level = level;
		Dimension lvlSize = level.getLandscape().getSize();
		Dimension viewPort = new Dimension(viewSize.width * Tile.SIZE.width, viewSize.height * Tile.SIZE.height);
		this.setPreferredSize(viewPort);
		this.setSize(viewPort);
		this.setMinimumSize(viewPort);
		this.maxViewOrgX = (int) Math.max(0, lvlSize.getWidth() - viewSize.getWidth());
		this.maxViewOrgY = (int) Math.max(0, lvlSize.getHeight() - viewSize.getHeight());
	}
}
