package beepBoop.model;

import java.awt.Point;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import beepBoop.controller.EventQueue;
import beepBoop.controller.RobotQueue;
import beepBoop.model.resource.Resource;

public class Level implements Serializable{

	private static final long serialVersionUID = -4156377316804061536L;
	private Landscape landscape;
	private HashMap<Point,Thing> things;
	private Player player;
	private RobotQueue robotQueue;
	private Inventory inventory;
	private EventQueue eventQueue;

    public EventQueue getEventQueue() {
		return eventQueue;
	}

	public Level(Landscape landscape, Player player, Inventory inventory) {
		super();
		this.landscape = landscape;
		this.inventory = inventory;
		this.setPlayer(player);
		this.things = new HashMap<Point,Thing>();
		this.robotQueue = new RobotQueue();
		this.eventQueue = new EventQueue();
	}
    
	public Inventory getInventory()
    {
        return inventory;
    }

    public void setInventory(Inventory inventory)
    {
        this.inventory = inventory;
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

	public RobotQueue getRobotQueue() {
		return robotQueue;
	}
	
	public boolean isPositionFree(int x, int y) {
		Point p = new Point(x,y);
		return landscape.getTile(x,y).isWalkable() 
				&& !things.containsKey(p) 
				&& !player.getPosition().equals(p);
	}
	
	public boolean addThing(Thing thing) {
		if (things.containsKey(thing.getPosition())) {
			return false;
		}
		things.put(thing.getPosition(), thing);	
		return true;
	}
	
	public void moveThing(Point oldPoint, Point newPoint) {
		things.put(newPoint, things.remove(oldPoint));
	}
	
	public boolean addRobot(Robot robot) {
		if (robotQueue.contains(robot)) {
			return false;
		}
		this.addThing(robot);
		robotQueue.add(robot);	
		return true;
	}
	public boolean addEvent(Event event) {
		if (eventQueue.contains(event)) {
			return false;
		}
		
		eventQueue.add(event);	
		return true;
	}
	public Set<Thing> getThings() {
		return new HashSet(things.values());
	}

	public boolean isRessource(int x, int y) {
		return (things.get(new Point(x,y)) instanceof Resource);
	}

	public Thing getThing(int x, int y) {
	    if (player.getPosition().x == x && player.getPosition().y == y) {
	        return player;
	    }
		return (Thing) things.get(new Point(x,y));
	}

	public boolean isRobotTerminal(int x, int y) {
		return (things.get(new Point(x,y)) instanceof RobotTerminal);
	}

	public void removeThing(Thing thing) {
		this.things.remove(thing.getPosition());
		
	}
}
