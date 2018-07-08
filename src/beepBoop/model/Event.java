package beepBoop.model;

import java.util.Observable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

abstract public class Event extends Observable implements Delayed {
	private long timeout;
	private long startTime;
		
	public abstract boolean performChanges(Level level);
	
	public void setTimeout(int beeps) {
		startTime = System.currentTimeMillis();
		timeout = System.currentTimeMillis() + (beeps * 1000);		
	}

	public boolean comeCloser() {
		timeout--;
		return timeout == 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(timeout- System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	@Override
	public int compareTo(Delayed o) {
		return (int) (timeout - ((Event) o).timeout);
	}
}
