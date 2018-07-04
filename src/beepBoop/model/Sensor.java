package beepBoop.model;

import java.awt.Point;

public abstract interface Sensor {
	public abstract boolean check(String[] params, Point p, Level level);	
}
