package beepBoop.controller;

import java.awt.Point;
import beepBoop.model.Command;
import beepBoop.model.Event;
import beepBoop.model.Level;
import beepBoop.model.MsgEvent;
import beepBoop.model.Resource;
import beepBoop.model.AbstractRobot;
import beepBoop.model.BasicRobot;
import beepBoop.model.RobotTerminal;
import beepBoop.model.ISensor;
import beepBoop.model.Thing;
import beepBoop.service.SensorService;
import beepBoop.service.TileFactory;

/**
 * PTP 2018 BeepBoop - the RobotGame
 * 
 * Controller for the BeepBoop robots
 * 
 * Used to modify the state of robots within the level and process robots'
 * programs.
 * 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class RobotController {
	private Level level;
	private boolean firstTerminalDump;
	private EventController eventController;

	/**
	 * Constructor
	 * 
	 * @param level
	 *            The level on which this controller should work.
	 * @param eventController
	 *            EventController controlling the events of the level
	 */
	public RobotController(Level level, EventController eventController) {
		super();
		this.level = level;
		firstTerminalDump = true;
		this.eventController = eventController;
	}

	/**
	 * 
	 * Adds new Robot of the requested type at the specified position to the
	 * level and returns a reference to the new robot.
	 * 
	 * @param type
	 *            element of {"r1"}
	 * @param position
	 *            the position in the level for the new robot
	 * @return the newly placed robot
	 */
	public AbstractRobot newAction(String type, Point position) {
		AbstractRobot robot;
		switch (type) {
		case "r1":
			robot = new BasicRobot();
			break;
		default:
			return null;
		}
		robot.setPosition(position);
		level.addRobot(robot);
		return robot;
	}

	/**
	 * Takes care of processing a robot's program by dispatching the robot and
	 * it's current command to helper methods, if the critical activity level of
	 * the robot is reached.
	 * 
	 * @param robot
	 */
	public void processAction(AbstractRobot robot) {
		robot.incrementAvtivityCounter();
		if (robot.getActivityCounter() % robot.getCriticalActivity() != 0) {
			return;
		}
		Command command = robot.getCurrentCommand();
		switch (command.getType()) {
		case "L":
		case "R":
		case "U":
		case "D":
			moveRobot(robot, command);
			break;
		case "DP":
		case "LD":
			manageResource(robot, command);
			break;
		case "IF":
			decideCondition(robot, command);
			break;
		case "END":
			robot.incrementPc();
			processAction(robot);
			break;
		case "GOTO":
			jumpTo(robot, command);
			break;
		default:
			robot.incrementPc();
		}
	}

	// helper method for goto commands
	private void jumpTo(AbstractRobot robot, Command command) {
		int target = robot.getPc();
		try {
			target = Integer.parseInt(command.getArgs()[0]);
		} catch (Exception e) {
			robot.setError("Wrong target for GOTO");
			robot.incrementPc();
			return;
		}
		robot.setPc(target);
	}

	// helper method for if commands, makes use of the robots sensors
	private void decideCondition(AbstractRobot robot, Command command) {
		String sensorStr = command.getArgs()[0];
		SensorService sensorService = SensorService.getInstance();
		ISensor sensor = sensorService.getSensor(sensorStr);
		robot.incrementPc();
		if (sensor == null || !robot.hasSensor(sensorStr)) {
			return;
		} else {
			if (!sensor.check(command.getArgs(), robot.getPosition(), level)) {
				int i = robot.getPc();
				while (!robot.getCurrentCommand().getType().equals("END") 
					   && i < robot.getMemory().size()) {
					i++;
					robot.incrementPc();
				}
				robot.incrementPc();
			}
		}

	}

	// helper method for move commands
	private void moveRobot(AbstractRobot robot, Command command) {
		Point p = Command.getPointFromDirection(robot.getPosition(), command.getType());
		if (level.isPositionFree(p.x, p.y)) {
			level.moveThing(robot.getPosition(), p);
			robot.move(p);
		} else {
			Thing thing = level.getThing(p.x, p.y);
			if (thing == null) {
				robot.setError("Blocked by " + level.getLandscape().getTile(p.x, p.y).toString());
			} else {
				robot.setError("Blocked by " + level.getThing(p.x, p.y).toString());
			}
			robot.incrementPc();
		}
	}

	// helper method for managing resources
	private void manageResource(AbstractRobot robot, Command command) {
		String direction = command.getArgs()[0];
		int amount = new Integer(command.getArgs()[1]);
		Point actOn = Command.getPointFromDirection(robot.getPosition(), direction);
		switch (command.getType()) {
		case "LD":
			robot.setError(loadRessource(robot, actOn, amount));
			break;
		case "DP":
			dumpRessource(robot, actOn, amount);
		}
		robot.incrementPc();

	}

	// helper method for dumping resources
	private void dumpRessource(AbstractRobot robot, Point actOn, int amount) {

		Thing thing = level.getThing(actOn.x, actOn.y);
		Resource cargo = robot.getCargo();
		if (cargo == null || cargo.getAmount() <= 0) {
			robot.setError("No Cargo to DUMP");

			return;
		}
		int dump = Math.min(cargo.getAmount(), amount);
		if (thing == null) {
			if (!level.isPositionFree(actOn.x, actOn.y)) {
				robot.setError("Can't drop on non-walkable spot");
				return;
			}
			Resource resource = new Resource(robot.removeCargo(dump), 
					                         TileFactory.getTileIdForResource(cargo.getName()),
					                         cargo.getName());

			resource.setPosition(actOn);

			level.addThing(resource);

			return;
		}

		if (thing instanceof Resource) {
			Resource resource = (Resource) thing;

			if (!resource.getName().equals(cargo.getName())) {
				robot.setError("Resource mismatch");
			} else {
				resource.increaseAmount(robot.removeCargo(dump));
			}

			return;
		}

		if (thing instanceof RobotTerminal) {
			level.getInventory().addResource(new Resource(robot.removeCargo(dump),
					                         TileFactory.getTileIdForResource(cargo.getName()),
					                         cargo.getName()));
			// show a message if this is the first dump
			if (firstTerminalDump) {
				Event event = new MsgEvent(
						"A Robot just dumped some cargo on the terminal. It was added to your inventory. Cool...");
				event.setTimeout(1);
				eventController.addAction(event);
				firstTerminalDump = false;
			}

		}

	}

	// helper method for loading resources
	private String loadRessource(AbstractRobot robot, Point actOn, int amount) {
		Thing thing = level.getThing(actOn.x, actOn.y);
		Resource cargo = robot.getCargo();

		if (thing instanceof Resource) {
			Resource resource = (Resource) thing;

			if (resource.getAmount() == 0) {
				return "Resource depleted";
			}

			if (cargo == null) {
				robot.setCargo(new Resource(resource.takeAmount(amount),
						TileFactory.getTileIdForResource(resource.getName()), resource.getName()));
			} else if (cargo.getName().equals(resource.getName())) {
				int load = Math.min(robot.getMaxCapacity() - cargo.getAmount(), amount);
				robot.addCargo(resource.takeAmount(load));
			} else {
				return "Incompatible Resource loaded";
			}

			if (resource.getAmount() == 0) {
				level.removeThing(resource);
			}

			return "NoError";
		} else {
			return "No Resource";
		}
	}
}
