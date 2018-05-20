package beepBoop.controller;

import java.awt.Point;

import beepBoop.model.Level;
import beepBoop.model.Robot;

public class RobotController extends AbstractController {
	Level level;

	
	public RobotController(Level level) {
		super();
		this.level = level;
	}

	public void moveAction(Robot robot) {
		Point p = robot.calcNextPosition();
		if (level.isPositionFree(p.x, p.y)) {
			level.moveThing(robot.getPosition(),p);
			robot.move();
		} else {
			robot.setBlocked(true);
		}	
	}
}
