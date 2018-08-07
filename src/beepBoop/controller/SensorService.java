package beepBoop.controller;

import java.util.HashMap;
import java.util.Map;

import beepBoop.model.FreeSensor;
import beepBoop.model.ResourceSensor;
import beepBoop.model.ISensor;

public class SensorService {

	private static SensorService sensorService = new SensorService();
	
	private Map<String,ISensor> sensors;
	
	private SensorService() {
		super();
		sensors = new HashMap<String,ISensor>();
		sensors.put("FREE", new FreeSensor());
		sensors.put("RESOURCE", new ResourceSensor());		
	}
	
	public static SensorService getInstance() {
		return sensorService;
	}

	public ISensor getSensor(String name) {
		return sensors.get(name);
	}
}
