package beepBoop.controller;

import java.awt.Point;
import java.util.Arrays;

import beepBoop.model.Command;
import beepBoop.model.Level;
import beepBoop.model.Robot;
import beepBoop.model.Sensor;
import beepBoop.model.Thing;
import beepBoop.model.TileFactory;
import beepBoop.model.resource.Resource;

public class RobotController extends AbstractController {
	Level level;

	
	public RobotController(Level level) {
		super();
		this.level = level;
	}

	public void processAction(Robot robot) {
		Command command = robot.getCurrentCommand();
	    switch(command.getType()) {
	    case "L": 
        case "R":   
	    case "U": 
        case "D":	moveRobot(robot, command);
        			break;
	    case "DP": 
        case "LD": 	manageResource(robot, command);
	        		break;
	    case "IF":  decideCondition(robot, command);
	    			break;
	    case "END": robot.incrementPc(); 
	    			processAction(robot);
	    			break;
	    default: 	robot.incrementPc(); 
        }	
	}

    private void decideCondition(Robot robot, Command command) {
		String sensorStr = command.getArgs()[0];
		SensorService sensorService = SensorService.getInstance();
		Sensor sensor = sensorService.getSensor(sensorStr);
		robot.incrementPc();
		if (sensor == null) {
			return;
		} else {
			if (!sensor.check(command.getArgs(), robot.getPosition(), level)) {
				int i = 1;
				while (!robot.getCurrentCommand().getType().equals("END") && i < robot.getMemory().size()) {
					i++;
					robot.incrementPc();
				}
				robot.incrementPc();
			}
		}
		
	}

	private void moveRobot(Robot robot, Command command) {
    	Point p = Command.getPointFromDirection(robot.getPosition(), command.getType());
    	if (level.isPositionFree(p.x, p.y)) {
			level.moveThing(robot.getPosition(),p);
			robot.move(p);
    	} else {
			robot.setError("Blocked by "+level.getThing(p.x, p.y).toString());
		    robot.incrementPc();
		}
	}

	private void manageResource(Robot robot, Command command)
    {
        String direction = command.getArgs()[0];
        int amount = new Integer(command.getArgs()[1]);
        Point actOn = Command.getPointFromDirection(robot.getPosition(), direction);
        switch(command.getType()) {
            case "LD": robot.setError(loadRessource(robot,actOn,amount));
            break;
            case "DP": dumpRessource(robot,actOn,amount);
        }
        robot.incrementPc();
        
    }

    private void dumpRessource(Robot robot, Point actOn, int amount)
    {
        // TODO Auto-generated method stub
        
    }

    private String loadRessource(Robot robot, Point actOn, int amount)
    {
        Thing thing = level.getThing(actOn.x, actOn.y);
        Resource cargo = robot.getCargo();
        if (thing instanceof Resource) {
            Resource resource = (Resource) thing;
            if (resource.getAmount() == 0) {
            	return "Resource depleted";
            }
            if (cargo == null) {
                robot.setCargo(new Resource(resource.takeAmount(amount),
                							TileFactory.NULL_TILE,
                							resource.getName()){});
            } else if (cargo.getName().equals(resource.getName())) {
                int load = Math.min(Robot.MAX_CAPACITY - cargo.getAmount(), amount) ;
                robot.addCargo(resource.takeAmount(load));       
            } else {
                return "Incompatible Resource loaded";
            }
            return "NoError";
        } else {
            return "No Resource";
        }
    }
}
