package beepBoop.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import beepBoop.model.BasicRobot;
import beepBoop.model.AbstractRobot;

public class RobotQueue implements Iterable<AbstractRobot>, Serializable {

	private static final long serialVersionUID = 5512500479125148877L;
	private HashMap<String, Integer> nameToIndex;
	private LinkedList<AbstractRobot> robots;

	public RobotQueue() {
		super();
		nameToIndex = new HashMap<String, Integer>();
		robots = new LinkedList<AbstractRobot>();
	}

	public boolean add(AbstractRobot robot) {
		String name = robot.getName();
		if(!nameToIndex.containsKey(name) && robots.add(robot)) {
			nameToIndex.put(name, robots.indexOf(robot));
			return true;
		}
		return false;
	}
	
	public boolean contains(AbstractRobot robot) {
		return robots.contains(robot);
	}
	
	public AbstractRobot getRobot(String name) {
		return robots.get(nameToIndex.get(name));
	}
	
	public AbstractRobot peekFirst() {
		return robots.peekFirst();
	}
	
	@Override
	public Iterator<AbstractRobot> iterator() {
		return robots.iterator();
	}
	
}
