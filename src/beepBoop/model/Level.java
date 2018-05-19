package beepBoop.model;

import java.util.Set;

public class Level {
	Landscape landscape;
	Set<Thing> things;
	private Player player;
	
	public Level(Landscape landscape, Player player) {
		super();
		this.landscape = landscape;
		this.setPlayer(player);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Landscape getLandscape() {
		return landscape;
	}

	public void setLandscape(Landscape landscape) {
		this.landscape = landscape;
	}
	
	
	
	
}
