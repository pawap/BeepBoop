package beepBoop.model;

import java.util.Observable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class MsgEvent extends Event {

	private static final long serialVersionUID = -3645991823300567833L;
	private String msg;
	private int timeout;
	private Observable notifier;
	
	public MsgEvent(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public boolean performChanges(Level level) {
		setChanged();
		notifyObservers();
		return true;
	}

	public String getMsg() {
		return msg;
	}




	

}
