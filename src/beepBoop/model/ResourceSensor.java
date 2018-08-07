package beepBoop.model;

import java.awt.Point;

public class ResourceSensor implements ISensor {

	@Override
	public boolean check(String[] params, Point p, Level level) {
		Point target = Command.getPointFromDirection(p, params[1]);
		return level.getThing(target.x, target.y) instanceof Resource;
	}

}
