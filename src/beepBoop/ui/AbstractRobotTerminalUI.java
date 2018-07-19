package beepBoop.ui;

import java.util.EventListener;
import java.util.HashMap;

import javax.swing.JPanel;

public abstract class AbstractRobotTerminalUI extends JPanel{

	private static final long serialVersionUID = -5997394769933055164L;

	/**
	 * Add listeners to the GUI elements.
	 */
	public abstract void addListeners(HashMap<String, EventListener> listeners);

}
