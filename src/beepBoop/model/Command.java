package beepBoop.model;

import java.awt.Point;

/**
 * PTP 2018 BeepBoop - the RobotGame
 * 
 * Code in a Robot's memory is turned into Commands. Commands are processed by
 * the RobotController.
 * 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class Command {

	private String type;
	private String[] args;

	/**
	 * Constructor
	 * 
	 * @param commandStr
	 *            first the type, then the arguments; separated by spaces
	 */
	public Command(String commandStr) {
		if (commandStr.isEmpty()) {
			throw new IllegalArgumentException("empty string");
		}
		String[] strArray = commandStr.split(" ");
		this.type = strArray[0];
		this.args = new String[strArray.length - 1];
		if (strArray.length > 1) {
			for (int i = 1; i < strArray.length; i++) {
				this.args[i - 1] = strArray[i];
			}
		}
	}

	/**
	 * @return the type of the Command
	 */
	public String getType() {
		return type;
	}

	/**
	 * Set the type of the Command.
	 * 
	 * @param type
	 *            the desired type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the arguments of the Command
	 */
	public String[] getArgs() {
		return args;
	}

	/**
	 * set the arguments of a Command
	 * 
	 * @param args
	 */
	public void setArgs(String[] args) {
		this.args = args;
	}

	/**
	 * Translates a given position into a new position, 1 tile away, based on a
	 * given direction
	 * 
	 * @param position
	 *            the origin position
	 * @param direction
	 *            element in {"L", "R", "U", "D"}
	 * @return the new position
	 */
	public static Point getPointFromDirection(Point position, String direction) {
		switch (direction) {
		case "L":
			return new Point(position.x - 1, position.y);
		case "R":
			return new Point(position.x + 1, position.y);
		case "U":
			return new Point(position.x, position.y - 1);
		case "D":
			return new Point(position.x, position.y + 1);
		default:
			return position;
		}
	}

}
