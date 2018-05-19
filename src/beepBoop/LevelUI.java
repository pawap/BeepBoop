package beepBoop;

import java.awt.Graphics;

import javax.swing.JPanel;

public class LevelUI extends JPanel {
	Level level;
	
	
	public LevelUI(Level level) {
		super();
		this.level = level;
		this.setPreferredSize(level.getLandscape().getSize());
		this.setSize(level.getLandscape().getSize());
	}


	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		paintLandscape(g);
		paintPlayer(g);

	}
	
	private void paintPlayer(Graphics g) {
		g.drawImage(level.player.getImage(),level.player.getPosition().x*Tile.SIZE.width,
				level.player.getPosition().y*Tile.SIZE.height,null);
	}


	private void paintLandscape(Graphics g) {
		for (int i = 0; i < level.getLandscape().getSize().width; i++) {
			for (int j = 0; j < level.getLandscape().getSize().height; j++){
				g.drawImage(level.getLandscape().getTile(i, j).getImage(),i*Tile.SIZE.width,j*Tile.SIZE.height,null);			
			}
		}
	}
}
