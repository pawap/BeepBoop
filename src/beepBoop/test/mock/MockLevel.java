package beepBoop.test.mock;

import java.awt.Point;

import beepBoop.model.Level;
import beepBoop.model.Thing;

/**
 * a MockUp Class for a level to facilitate testing
 * 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 */
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
		this();
		this.blockedPosition = blockedPosition;
	}
	
	/**
	 * Constructor for a level that returns true when 
	 * {@link beepBoop.test.mock.MockLevel#isPositionFree(int,int)} is
	 * called with any parameter.
	 */
	public MockLevel() {
		super(null, null, null);
	}
	
	/**
	 * Constructor for a MockLevel with a Thing at a position.
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
