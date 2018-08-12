package beepBoop.ui;

import java.util.EventListener;
import java.util.HashMap;

import javax.swing.JPanel;

/**
 * PTP 2018
 * BeepBoop - the RobotGame
 * 
 * The superclass of all Robot Terminal UI classes. 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public abstract class AbstractRobotTerminalUI extends JPanel{

	private static final long serialVersionUID = -5997394769933055164L;

	/**
	 * Add listeners to the GUI elements.
	 */
	public abstract void addListeners(HashMap<String, EventListener> listeners);

}
