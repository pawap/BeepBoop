package beepBoop.controller;

import java.util.HashMap;
import java.util.Map;

import beepBoop.model.FreeSensor;
import beepBoop.model.ResourceSensor;
import beepBoop.model.Sensor;

public class SensorService {

	private static SensorService sensorService = new SensorService();
	
	private Map<String,Sensor> sensors;
	
	private SensorService() {
		super();
		sensors = new HashMap<String,Sensor>();
		sensors.put("FREE", new FreeSensor());
		sensors.put("RESOURCE", new ResourceSensor());		
	}
	
	public static SensorService getInstance() {
		return sensorService;
	}

	public Sensor getSensor(String name) {
		return sensors.get(name);
	}
}
