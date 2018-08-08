package beepBoop.test.mock;

import java.awt.Point;

import beepBoop.model.Inventory;
import beepBoop.model.Landscape;
import beepBoop.model.Level;
import beepBoop.model.Player;
import beepBoop.model.Thing;

@SuppressWarnings("serial")
public class MockLevel extends Level{
	private Point blockedPosition;
	

	/**
	 * Constructor for a level that returns false when 
	 * {@link beepBoop.test.mock.MockLevel#isPositionFree(int,int)} is called
	 * with the specified Position.
	 * @param blockedPosition the position to be handled as if it was blocked
	 */
	public MockLevel(Point blockedPosition) {
		this(null, null, null);
		this.blockedPosition = blockedPosition;
	}
	
	/**
	 * Constructor for a level that returns true when 
	 * {@link beepBoop.test.mock.MockLevel#isPositionFree(int,int)} is
	 * called with any parameter.
	 */
	public MockLevel() {
		this(null, null, null);
	}

	public MockLevel(Landscape landscape, Player player, Inventory inventory) {
		super(landscape, player, inventory);
	}
	
	/**
	 * Constructor for a MockLevel with a thing at a position.
	 * @param thing 
	 * @param positionOfThing
	 */
	public MockLevel(Thing thing, Point positionOfThing) {
		super(null, null, null);
		thing.setPosition(positionOfThing);
		this.addThing(thing);
	}

	@Override
	public boolean isPositionFree(int x, int y) {
		if (blockedPosition != null) { 
			return !(x == blockedPosition.x && y == blockedPosition.y);
		}
		return true;
	}
	
	@Override
	public boolean addThing(Thing thing) {
		things.put(thing.getPosition(), thing);
		return true;
	}
}
