package beepBoop.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import beepBoop.model.AbstractRobot;

/**
 * Contains all Robots of a Level
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class RobotQueue implements Iterable<AbstractRobot>, Serializable {

	private static final long serialVersionUID = 5512500479125148877L;
	private HashMap<String, Integer> nameToIndex;
	private LinkedList<AbstractRobot> robots;

	/**
	 * Constructor
	 */
	public RobotQueue() {
		super();
		nameToIndex = new HashMap<String, Integer>();
		robots = new LinkedList<AbstractRobot>();
	}

	/**
	 * Adds a robot to the RobotQueue if it does not contain a robot with the same name
	 * @param robot the robot to be added
	 * @return true, if the robot was added
	 */
	public boolean add(AbstractRobot robot) {
		String name = robot.getName();
		if(!nameToIndex.containsKey(name) && robots.add(robot)) {
			nameToIndex.put(name, robots.indexOf(robot));
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if a robot is enqueued
	 * @param robot
	 * @return true if the RobotQueue contains the given robot
	 */
	public boolean contains(AbstractRobot robot) {
		return robots.contains(robot);
	}
	
	/**
	 * Maps the name to the corresponding robot
	 * @param name the name of the robot
	 * @return the robot with the given name or null if no robot with the given name is enqueued
	 */
	public AbstractRobot getRobot(String name) {
		return robots.get(nameToIndex.get(name));
	}
	
	/**
	 * @return the first robot of the queue
	 */
	public AbstractRobot peekFirst() {
		return robots.peekFirst();
	}
	
	@Override
	public Iterator<AbstractRobot> iterator() {
		return robots.iterator();
	}
	
}
