package beepBoop;

import java.awt.Graphics;

import javax.swing.JPanel;

public class LandscapeUI extends JPanel {
	Landscape landscape;
	
	
	public LandscapeUI(Landscape landscape) {
		super();
		this.landscape = landscape;
		this.setPreferredSize(landscape.getSize());
		this.setSize(landscape.getSize());
	}


	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int i = 0; i < landscape.getSize().width; i++) {
			for (int j = 0; j < landscape.getSize().height; j++){
				g.drawImage(landscape.getTile(i, j).getImage(),i*Tile.SIZE.width,j*Tile.SIZE.height,null);			
			}
		}
	}
}
