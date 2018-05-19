package beepBoop;

import java.util.Set;

public class Level {
	Landscape landscape;
	Set<Thing> things;
	Player player;
	
	public Level(Landscape landscape, Player player) {
		super();
		this.landscape = landscape;
		this.player = player;
	}

	public Landscape getLandscape() {
		return landscape;
	}

	public void setLandscape(Landscape landscape) {
		this.landscape = landscape;
	}
	
	
	
	
}
