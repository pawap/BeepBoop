package beepBoop.controller;

import java.io.Serializable;
import java.util.PriorityQueue;
import java.util.concurrent.DelayQueue;

import beepBoop.model.Event;
import beepBoop.model.Thing;

public class EventQueue extends DelayQueue<Event> implements Serializable {

	private static final long serialVersionUID = 2665595300031997455L;

	public EventQueue() {
		super();
	}

	
}
