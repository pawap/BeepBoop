package beepBoop.controller;

import java.util.Observable;
import java.util.Observer;

import beepBoop.model.Event;
import beepBoop.model.Level;
import beepBoop.model.MsgEvent;
import beepBoop.service.EventQueue;
import beepBoop.ui.MainFrame;

/**
 * Controller for BeepBoop Events
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class EventController {

	private Level level;
	private MainFrame gui;
	
	/**
	 * Constructor
	 * @param level the current Level
	 * @param gui the main gui
	 */
	public EventController(Level level, MainFrame gui) {
		super();
		this.level = level;
		this.gui = gui;
	}

	/**
	 * Have an Event happen.
	 * @param event the Event that should happen
	 */
	public void processAction(Event event) {
		event.performChanges(level);
	}
	
	/**
	 * Add an Event to the Level.
	 * @param event
	 */
	public void addAction(Event event) {
		level.addEvent(event);
		prepareEvent(event);
				
	}
	
	//prepares an Event based on its type, if it needs to be prepared
	private void prepareEvent(Event event) {
		if (event instanceof MsgEvent) {
			MsgEvent msgEvent = (MsgEvent) event;
			msgEvent.addObserver(new Observer(){

				@Override
				public void update(Observable arg0, Object arg1) {
					gui.showMessage(msgEvent.getMsg());
					
				}});
		}
	}
	
	/**
	 * Prepares all Events in a given EventQueue
	 * @param events the EventQueue containing the Events to be prepared
	 */
	public void initAction(EventQueue events) {
		for (Event event: events) {
			prepareEvent(event);
		}
	}
	
}
