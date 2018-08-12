package beepBoop.model;

import java.awt.Point;

/**
 * PTP 2018 BeepBoop - the RobotGame
 * 
 * A sensor that checks if the Tile next to the Robot in a given direction
 * contains a Resource.
 * 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class ResourceSensor implements ISensor {

	@Override
	public boolean check(String[] params, Point p, Level level) {
		Point target = Command.getPointFromDirection(p, params[1]);
		return level.getThing(target.x, target.y) instanceof Resource;
	}

}
