package beepBoop.controller;

import beepBoop.model.Robot;

public class RobotTerminalController extends AbstractController {

    private RobotQueue robotQueue;
    
	public RobotTerminalController(RobotQueue robotQueue)
    {
       this.robotQueue = robotQueue;
    }

    /**
	 * This method should be called when the player interacts with a terminal. Sets the terminalUI to active.
	 */
	public void openTerminal() {
		// TODO Set the TerminalUI to active
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
