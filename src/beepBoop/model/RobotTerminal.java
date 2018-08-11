package beepBoop.model;

import beepBoop.service.TileFactory;

/**
 * The in-Landscape representation of the Robot Terminal. 
 * The Player has to interact with this to access the  
 * RobotTerminal Menu and thus the Robot Terminal functions.
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class RobotTerminal extends Thing {
	
	private static final long serialVersionUID = 2497148692695664144L;

	/**
	 * Constructor
	 */
	public RobotTerminal() {
		super(TileFactory.TERMINAL);
	}
	
}
