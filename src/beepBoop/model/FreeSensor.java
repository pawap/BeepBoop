package beepBoop.model;

import java.awt.Point;

public class FreeSensor implements Sensor {
	@Override
	public boolean check(String[] params, Point p, Level level) {
		Point target = Command.getPointFromDirection(p, params[1]);
		return level.isPositionFree(target.x, target.y);
	}


}
