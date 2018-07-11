package beepBoop.ui;

import java.util.EventListener;
import java.util.HashMap;

import javax.swing.JPanel;

public abstract class AbstractRobotTerminalUI extends JPanel{
	
	/**
	 * Add listeners to the GUI elements.
	 */
	public abstract void addListeners(HashMap<String, EventListener> listeners);

}
