package beepBoop.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import beepBoop.model.BasicRobot;

public class RobotQueue implements Iterable<BasicRobot>, Serializable {

	private static final long serialVersionUID = 5512500479125148877L;
	private HashMap<String, Integer> nameToIndex;
	private LinkedList<BasicRobot> robots;

	public RobotQueue() {
		super();
		nameToIndex = new HashMap<String, Integer>();
		robots = new LinkedList<BasicRobot>();
	}

	public boolean add(BasicRobot robot) {
		String name = robot.getName();
		if(!nameToIndex.containsKey(name) && robots.add(robot)) {
			nameToIndex.put(name, robots.indexOf(robot));
			return true;
		}
		return false;
	}
	
	public boolean contains(BasicRobot robot) {
		return robots.contains(robot);
	}
	
	public BasicRobot getRobot(String name) {
		return robots.get(nameToIndex.get(name));
	}
	
	public BasicRobot peekFirst() {
		return robots.peekFirst();
	}
	
	@Override
	public Iterator<BasicRobot> iterator() {
		return robots.iterator();
	}
	
}
