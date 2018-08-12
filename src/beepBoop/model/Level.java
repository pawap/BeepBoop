package beepBoop.model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import beepBoop.service.EventQueue;
import beepBoop.service.RobotQueue;

/**
 * PTP 2018 BeepBoop - the RobotGame
 * 
 * A Level holds a Landscape, a Player, Robot- and EventQueue, an Inventory and
 * a collection of Things. The player and his Robot act in a Level.
 * 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class Level implements Serializable {

	private static final long serialVersionUID = -4156377316804061536L;
	private Landscape landscape;
	protected ConcurrentHashMap<Point, Thing> things;
	private Player player;
	private RobotQueue robotQueue;
	private Inventory inventory;
	private EventQueue eventQueue;

	/**
	 * Constructor
	 * 
	 * @param landscape
	 *            The Landscape for the Level
	 * @param player
	 *            the Player for the Level
	 * @param inventory
	 *            the Inventory for the Level
	 */
	public Level(Landscape landscape, Player player, Inventory inventory) {
		super();
		this.landscape = landscape;
		this.inventory = inventory;
		this.setPlayer(player);
		this.things = new ConcurrentHashMap<Point, Thing>();
		this.robotQueue = new RobotQueue();
		this.eventQueue = new EventQueue();
	}

	/**
	 * @return the EventQueue of the Level
	 */
	public EventQueue getEventQueue() {
		return eventQueue;
	}

	/**
	 * @return the Inventory of the Level
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * Replace the Inventory of the Level.
	 * 
	 * @param inventory
	 *            the new Inventory
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	/**
	 * @return the Player of the Level.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Set the Player of the Level.
	 * 
	 * @param player
	 *            the new Player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the Landscape of the Level
	 */
	public Landscape getLandscape() {
		return landscape;
	}

	/**
	 * Replace the Landscape of the Level.
	 * 
	 * @param landscape
	 *            the new Landscape
	 */
	public void setLandscape(Landscape landscape) {
		this.landscape = landscape;
	}

	/**
	 * @return the RobotQueue of the Level
	 */
	public RobotQueue getRobotQueue() {
		return robotQueue;
	}

	/**
	 * A position in the Level is considered free if its Tile is walkable and
	 * there is neither the Player nor another Thing in the same position.
	 * 
	 * @param x
	 *            the x coordinate of the position to check
	 * @param y
	 *            the y coordinate of the position to check
	 * @return true if the position is free
	 */
	public boolean isPositionFree(int x, int y) {
		Point p = new Point(x, y);
		return landscape.getTile(x, y).isWalkable() 
			   && !things.containsKey(p) 
			   && !player.getPosition().equals(p);
	}

	/**
	 * Adds a Thing to the Level if its position corresponds to a free, walkable
	 * tile in the Landscape
	 * 
	 * @param thing
	 *            the Thing to be added
	 * @return true if the Thing has been added
	 */
	public boolean addThing(Thing thing) {
		Point pos = thing.getPosition();
		if (!(this.landscape.isInsideDimensions(pos.x, pos.y) 
			&& isPositionFree(pos.x, pos.y))) {
			return false;
		}
		things.put(thing.getPosition(), thing);
		return true;
	}

	/**
	 * If there is a thing at oldPoint it will be moved to newPoint
	 * 
	 * @param oldPoint
	 *            the position of the Thing to be moved
	 * @param newPoint
	 *            the target position
	 */
	public void moveThing(Point oldPoint, Point newPoint) {
		if (things.containsKey(oldPoint)) {
			things.put(newPoint, things.remove(oldPoint));
		}
	}

	/**
	 * Adds a Robot if its name is not taken and its position is free.
	 * 
	 * @param robot
	 *            the Robot to be added
	 * @return true if the Robot has been added
	 */
	public boolean addRobot(AbstractRobot robot) {
		if (robotQueue.add(robot)) {
			return addThing(robot);
		}
		return false;
	}

	/**
	 * Adds an Event to the EventQueue if it is not already enqueued
	 * 
	 * @param event
	 *            the Event to be added
	 * @return true if the Event has been added
	 */
	public boolean addEvent(Event event) {
		if (eventQueue.contains(event)) {
			return false;
		}

		eventQueue.add(event);
		return true;
	}

	/**
	 * @return a Set containing the Things contained within the level
	 */
	public Set<Thing> getThings() {
		HashSet<Thing> thingsReturned = new HashSet<Thing>();
		try {
			thingsReturned.addAll(things.values());
		} catch (ConcurrentModificationException e) {
			System.out.println("Caught ConcurrentModificationException! Trying again...");
			return getThings();
		}
		return thingsReturned;
	}

	/**
	 * Checks if a position in the Level contains a Resource.
	 * 
	 * @param x
	 *            the x coordinate of the position to check
	 * @param y
	 *            the y coordinate of the position to check
	 * @return true if there is a Resource at the position
	 */
	public boolean isRessource(int x, int y) {
		return (things.get(new Point(x, y)) instanceof Resource);
	}

	/**
	 * Returns the Thing at the given coordinates
	 * 
	 * @param x
	 *            the x coordinate of the Thing to be returned
	 * @param y
	 *            the y coordinate of the Thing to be returned
	 * @return the Thing at the given position
	 */
	public Thing getThing(int x, int y) {
		if (player != null) {
			if (player.getPosition().x == x 
				&& player.getPosition().y == y) {
				return player;
			}
		}
		return (Thing) things.get(new Point(x, y));
	}

	/**
	 * Checks if a position in the Level contains a RobotTerminal.
	 * 
	 * @param x
	 *            the x coordinate of the position to check
	 * @param y
	 *            the y coordinate of the position to check
	 * @return true if there is a RobotTerminal at the position
	 */
	public boolean isRobotTerminal(int x, int y) {
		return (things.get(new Point(x, y)) instanceof RobotTerminal);
	}

	/**
	 * Removes a Thing from the Level.
	 * 
	 * @param thing
	 *            the Thing to be removed
	 */
	public void removeThing(Thing thing) {
		this.things.remove(thing.getPosition());
	}
}
