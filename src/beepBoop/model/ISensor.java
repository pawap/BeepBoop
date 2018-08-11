package beepBoop.model;

import java.awt.Point;

/**
 * BeepBoop Robot sensors must implement this interface.
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public abstract interface ISensor {
	
	/**
	 * Uses the params to check if the Sensor specific conditions are met.
	 * @param params the arguments of the Command that used the Sensor 
	 * @param p the current position from which the Sensor is being used
	 * @param level the Level the Sensor is being used in
	 * @return true, if the conditions are met
	 */
	public abstract boolean check(String[] params, Point p, Level level);	
}
