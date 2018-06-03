package beepBoop.ui;

import javax.swing.JPanel;

/**
 * The UI class for the robot terminal. The graphical interface that lets the 
 * player construct and then program robots 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class RobotTerminalUI extends JPanel {
	private Boolean active;

	/**
	 * Constructor
	 */
	public RobotTerminalUI() {
		super();
		this.setActive(false);
	}

	/**
	 * Returns whether the terminal is currently active
	 * @return true if the terminal is currently active
	 */
	public Boolean isActive() {
		return active;
	}

	/**
	 * Set the terminal to active or inactive
	 * @param active true if the terminal is supposed to be active, false otherwise
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}
	


}
