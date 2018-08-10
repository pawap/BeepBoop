package beepBoop.service;

import java.io.Serializable;
import java.util.concurrent.DelayQueue;

import beepBoop.model.Event;

/**
 * A DelayedQueue holding events
 * 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 */
public class EventQueue extends DelayQueue<Event> implements Serializable {

	private static final long serialVersionUID = 2665595300031997455L;

}
