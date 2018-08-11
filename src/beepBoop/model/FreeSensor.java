package beepBoop.model;

import java.awt.Point;
/**
 * A sensor that checks if the Tile next to the Robot in a given direction is free. 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class FreeSensor implements ISensor {
	
	@Override
	public boolean check(String[] params, Point p, Level level) {
		Point target = Command.getPointFromDirection(p, params[1]);
		return level.isPositionFree(target.x, target.y);
	}

}
