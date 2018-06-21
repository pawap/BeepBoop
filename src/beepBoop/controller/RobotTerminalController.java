package beepBoop.controller;

import beepBoop.model.Robot;
import beepBoop.ui.RobotTerminalUI;

public class RobotTerminalController extends AbstractController {

    private RobotQueue robotQueue;
    private RobotTerminalUI robotTerminalUI;
    
	public RobotTerminalController(RobotTerminalUI robotTerminalUI, RobotQueue robotQueue)
    {
       this.robotQueue = robotQueue;
       this.robotTerminalUI = robotTerminalUI;
    }

    /**
	 * This method should be called when the player interacts with a terminal. Sets the terminalUI to active.
	 */
	public void openTerminal() {
		robotTerminalUI.setActive(true);
		robotTerminalUI.fillRobotsDropDown(robotQueue);
		    
		System.out.println("RobotTerminal Interaction");
		for (Robot robot: robotQueue) {
		    if (robot.getCargo() != null) {
		          System.out.println("Robot Cargo:");
		          System.out.println("    "+robot.getCargo().getName()+": "+robot.getCargo().getAmount()); 
		    }
		    System.out.println("Robot ErrorLog");
		    for (String str: robot.getErrorLog()) {
		        System.out.println("    "+str); 
		    }
		}
		
	}

}
