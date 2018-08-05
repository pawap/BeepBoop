package beepBoop.model;

public class Player extends Thing {

	private static final long serialVersionUID = -2007369164772325190L;
	private boolean terminalAccess;

	public Player() {
		super(TileFactory.PLAYER);
	}

	public boolean hasTerminalAccess() {
		return terminalAccess;
	}

	/**
	 * @param terminalAccess the terminalAccess to set
	 */
	public void setTerminalAccess(boolean terminalAccess) {
		this.terminalAccess = terminalAccess;
	}

}
