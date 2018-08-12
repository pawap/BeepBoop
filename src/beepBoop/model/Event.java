package beepBoop.model;

import java.io.Serializable;
import java.util.Observable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * PTP 2018 BeepBoop - the RobotGame
 * 
 * Abstract parent class for all beepboop events. Implements the
 * java.util.concurrent.Delayed interface, so that an event can be an element in
 * a DelayedQueue. This allows issuing timed events, based on the current system
 * time. Fully functional persistence is not yet realized as of version 1.0.
 * 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
abstract public class Event extends Observable implements Delayed, Serializable {

	private static final long serialVersionUID = 7015862117632560347L;
	private long timeout;

	/**
	 * Method called by the controller, when an event happens. All Changes made
	 * to the level or other Actions taken during the event are performed in
	 * this method
	 * 
	 * @param level
	 *            The current level is made available in this param by the
	 *            controller
	 */
	public abstract void performChanges(Level level);

	/**
	 * Sets the Timeout for the event
	 * 
	 * @param beeps
	 *            desired delay in seconds
	 */
	public void setTimeout(int beeps) {
		timeout = System.currentTimeMillis() + (beeps * 1000);
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(timeout - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	@Override
	public int compareTo(Delayed o) {
		return (int) (timeout - ((Event) o).timeout);
	}
}
