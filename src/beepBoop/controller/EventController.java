package beepBoop.controller;

import java.util.Observable;
import java.util.Observer;

import beepBoop.model.Event;
import beepBoop.model.Level;
import beepBoop.model.MsgEvent;
import beepBoop.ui.MainFrame;

public class EventController extends AbstractController {

	private Level level;
	private MainFrame gui;
	public void processAction(Event event) {
		event.performChanges(level);
	}
	
	public void addAction(Event event) {
		level.addEvent(event);
		prepareEvent(event);
				
	}
	
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
	
	public void initAction(EventQueue events) {
		for (Event event: events) {
			prepareEvent(event);
		}
	}
	public EventController(Level level, MainFrame gui) {
		super();
		this.level = level;
		this.gui = gui;
	}

	
}
