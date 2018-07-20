package beepBoop.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import beepBoop.model.Robot;

public class RobotQueue implements Iterable<Robot>, Serializable {

	private static final long serialVersionUID = 5512500479125148877L;
	private HashMap<String, Integer> nameToIndex;
	private LinkedList<Robot> robots;

	public RobotQueue() {
		super();
		nameToIndex = new HashMap<String, Integer>();
		robots = new LinkedList<Robot>();
	}

	public boolean add(Robot robot) {
		String name = robot.getName();
		if(!nameToIndex.containsKey(name) && robots.add(robot)) {
			nameToIndex.put(name, robots.indexOf(robot));
			return true;
		}
		return false;
	}
	
	public boolean contains(Robot robot) {
		return robots.contains(robot);
	}
	
	public Robot getRobot(String name) {
		return robots.get(nameToIndex.get(name));
	}
	
	public Robot peekFirst() {
		return robots.peekFirst();
	}
	
	@Override
	public Iterator<Robot> iterator() {
		return robots.iterator();
	}
	
}
