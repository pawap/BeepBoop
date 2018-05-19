package beepBoop.model;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Level {
	Landscape landscape;
	private Map<Point,Thing> things;
	private Player player;
	
	public Level(Landscape landscape, Player player) {
		super();
		this.landscape = landscape;
		this.setPlayer(player);
		this.things = new HashMap<Point,Thing>();
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

	public boolean isPositionFree(int x, int y) {
		return landscape.getTile(x,y).isWalkable() && !things.containsKey(new Point(x,y));
	}
	
	public boolean addThing(Thing thing) {
		if (things.containsKey(thing.getPosition())) {
			return false;
		}
		things.put(thing.getPosition(), thing);	
		return true;
	}

	public Set<Thing> getThings() {
		return new HashSet<Thing>(things.values());
	}

	public boolean isRessource(int x, int y) {
		return (things.get(new Point(x,y)) instanceof Ressource);
	}

	public Thing getThing(int x, int y) {
		return (Thing) things.get(new Point(x,y));
	}

	public boolean isActor(int x, int y) {
		return (things.get(new Point(x,y)) instanceof Actor);
	}

	public boolean isRobotTerminal(int x, int y) {
		return (things.get(new Point(x,y)) instanceof RobotTerminal);
	}
}
