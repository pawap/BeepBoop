package beepBoop.ui;

import java.awt.Graphics;
import java.util.Set;

import javax.swing.JPanel;

import beepBoop.model.Inventory;
import beepBoop.model.Level;
import beepBoop.model.Thing;
import beepBoop.model.Tile;

public class LevelUI extends JPanel {
	private Level level;
	
	public LevelUI(Level level) {
		super();
		this.setLevel(level);
		this.setPreferredSize(level.getLandscape().getSize());
		this.setSize(level.getLandscape().getSize());
	}


	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		paintLandscape(g);
		paintThings(g);
		paintPlayer(g);

	}
	
	private void paintThings(Graphics g) {
		Set<Thing> things = getLevel().getThings();
		for (Thing thing : things) {
			g.drawImage(thing.getImage(),thing.getPosition().x*Tile.SIZE.width,
					thing.getPosition().y*Tile.SIZE.height,null);
		}
		
		
	}


	private void paintPlayer(Graphics g) {
		g.drawImage(getLevel().getPlayer().getImage(),getLevel().getPlayer().getPosition().x*Tile.SIZE.width,
				getLevel().getPlayer().getPosition().y*Tile.SIZE.height,null);
	}


	private void paintLandscape(Graphics g) {
		for (int i = 0; i < getLevel().getLandscape().getSize().width; i++) {
			for (int j = 0; j < getLevel().getLandscape().getSize().height; j++){
				g.drawImage(getLevel().getLandscape().getTile(i, j).getImage(),i*Tile.SIZE.width,j*Tile.SIZE.height,null);			
			}
		}
	}


	public Level getLevel() {
		return level;
	}


	public void setLevel(Level level) {
		this.level = level;
	}
}
