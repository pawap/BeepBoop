package beepBoop.service;

import java.util.HashMap;
import java.util.Map;

import beepBoop.model.FreeSensor;
import beepBoop.model.ResourceSensor;
import beepBoop.model.ISensor;

/**
 * PTP 2018 BeepBoop - the RobotGame
 * 
 * Grants access to the robot sensors of BeepBoop.
 * 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class SensorService {

	private static SensorService sensorService;
	private Map<String, ISensor> sensors;

	// private constructor defeats instantiation, sets up sensors for later
	// access
	private SensorService() {
		super();
		sensors = new HashMap<String, ISensor>();
		sensors.put("FREE", new FreeSensor());
		sensors.put("RESOURCE", new ResourceSensor());
	}

	/**
	 * @return the singleton instance of the SensorService
	 */
	public static SensorService getInstance() {
		if (sensorService == null) {
			sensorService = new SensorService();
		}
		return sensorService;
	}

	/**
	 * @param type
	 *            element of {"FREE", "RESOURCE"}
	 * @return a sensor of the requested type
	 */
	public ISensor getSensor(String type) {
		return sensors.get(type);
	}
}
