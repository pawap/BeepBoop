package beepBoop.model;

import java.util.LinkedList;
import java.util.List;

import beepBoop.service.TileFactory;

/**
 * PTP 2018
 * BeepBoop - the RobotGame
 * 
 * The most basic robot. Can carry up to 1000 of one type of Resource.
 * It has two sensors, a ResourceSensor and a FreeSensor.
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class BasicRobot extends AbstractRobot{

	private static final long serialVersionUID = 2698956408529540093L;
	private static int robotCounter;
	
	static {
		robotCounter = 0;
	}
	
	/**
	 * Constructor
	 * gives the new robot a default name.
	 */
	public BasicRobot() {
		this("BasicBeepBot " + BasicRobot.robotCounter);
	}
	
	/**
	 * Constructor
	 * @param name the name for the new robot.
	 */
	public BasicRobot(String name) {
		super(TileFactory.ROBOT_0);
		this.memory = new LinkedList<String>();
		this.memory.add("GOTO 0");
		this.errorLog = new LinkedList<String>();
		this.sensors = new LinkedList<String>();
		this.name = name;
		this.maxCapacity = 10000;		
		sensors.add("FREE");
		sensors.add("RESOURCE");
		BasicRobot.robotCounter++;
	}
	
	/**
	 * Info about the Robot, including its cost.
	 * @return information for the user about this class of robot
	 */
	public static String getInfo() {
		return "The most basic robot type. \n 100 Iron \n 50 Silicon \n 25 Copper";
	}

	@Override
	public List<Resource> getCosts() {
		LinkedList<Resource> costs = new LinkedList<Resource>();
		costs.add(new Resource(100, TileFactory.IRON, "iron"));
		costs.add(new Resource(50, TileFactory.SILICON, "silicon"));
		costs.add(new Resource(25, TileFactory.COPPER, "copper"));
		return costs;
	}

	@Override
	public int getCriticalActivity() {
		return 20;
	}

}
