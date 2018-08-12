package beepBoop.model;

/**
 * PTP 2018 BeepBoop - the RobotGame
 * 
 * An Event class that issues a message.
 * 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class MsgEvent extends Event {

	private static final long serialVersionUID = -3645991823300567833L;
	private String msg;

	/**
	 * Constructs a MsgEvent with the given message
	 * 
	 * @param msg
	 *            the message as String
	 */
	public MsgEvent(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public void performChanges(Level level) {
		setChanged();
		notifyObservers();
	}

	/**
	 * Returns the message of this MsgEvent
	 * 
	 * @return message as String
	 */
	public String getMsg() {
		return msg;
	}

}
