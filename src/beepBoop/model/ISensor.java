package beepBoop.model;

import java.awt.Point;

public abstract interface ISensor {
	public abstract boolean check(String[] params, Point p, Level level);	
}
