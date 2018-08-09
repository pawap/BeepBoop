package beepBoop.model;

/**
 * The Player is the Thing representing the user in BeepBoop. 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class Player extends Thing {

	private static final long serialVersionUID = -2007369164772325190L;
	private boolean terminalAccess;

	/**
	 * Constructor
	 */
	public Player() {
		super(TileFactory.PLAYER);
	}

	/**
	 * A Player with terminal access can use the terminal functions to construct and manage robots.
	 * @return true, if the Player has terminal access
	 */
	public boolean hasTerminalAccess() {
		return terminalAccess;
	}

	/**
	 * A Player with terminal access can use the terminal functions to construct and manage robots.
	 * @param terminalAccess the terminalAccess to set
	 */
	public void setTerminalAccess(boolean terminalAccess) {
		this.terminalAccess = terminalAccess;
	}

}
