package beepBoop.service;

import java.io.Serializable;
import java.util.concurrent.DelayQueue;

import beepBoop.model.Event;

public class EventQueue extends DelayQueue<Event> implements Serializable {

	private static final long serialVersionUID = 2665595300031997455L;

	public EventQueue() {
		super();
	}

	
}
