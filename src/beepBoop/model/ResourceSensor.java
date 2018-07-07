package beepBoop.model;

import java.awt.Point;

import beepBoop.model.resource.Resource;

public class ResourceSensor implements Sensor {

	@Override
	public boolean check(String[] params, Point p, Level level) {
		Point target = Command.getPointFromDirection(p, params[1]);
		return level.getThing(target.x, target.y) instanceof Resource;
	}

}
