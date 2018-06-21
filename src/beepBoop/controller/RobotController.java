package beepBoop.controller;

import java.awt.Point;
import java.util.Arrays;

import beepBoop.model.Command;
import beepBoop.model.Level;
import beepBoop.model.Robot;
import beepBoop.model.Thing;
import beepBoop.model.TileFactory;
import beepBoop.model.resource.Resource;

public class RobotController extends AbstractController {
	Level level;

	
	public RobotController(Level level) {
		super();
		this.level = level;
	}

	public void moveAction(Robot robot) {
		Point p = robot.calcNextPosition();
		if (!robot.getPosition().equals(p)) {
			if (level.isPositionFree(p.x, p.y)) {
				level.moveThing(robot.getPosition(),p);
				robot.move();
			} else {
				robot.setBlocked(true);
				robot.setError("Blocked by "+level.getThing(p.x, p.y).toString());
				if (level.getThing(p.x, p.y) instanceof Resource) {
				    robot.act();
				}
			}
		} else {
		    Command command = robot.act();
		    switch(command.getType()) {
		        case "DP": 
		        case "LD": manageResource(robot, command);
		    }
		    
		}
		
	}

    private void manageResource(Robot robot, Command command)
    {
        String direction = command.getArgs()[0];
        int amount = new Integer(command.getArgs()[1]);
        Point actOn = Command.getPointFromDirection(robot.getPosition(), direction);
        switch(command.getType()) {
            case "LD": robot.setError(loadRessource(robot,actOn,amount)); 
            case "DP": dumpRessource(robot,actOn,amount);
        }
        
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
            if (cargo == null) {
                robot.setCargo(new Resource(resource.takeAmount(amount),TileFactory.NULL_TILE,resource.getName()) {
                    
                });
                
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
