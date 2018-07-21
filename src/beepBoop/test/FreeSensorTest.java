package beepBoop.test;

import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Test;

import beepBoop.model.FreeSensor;
import beepBoop.model.Level;

public class FreeSensorTest {
	
	@Test
	public void check_freePosition_returnTrue(){
		//arrange
		FreeSensor freeSensor = new FreeSensor();
		String directionToCheck = "L";
		String[] params = new String[] {"someString", directionToCheck};
		Point currentPosition = new Point(1,1);
		Point freePositionToTheLeft = new Point(0,1);
		@SuppressWarnings("serial")
		Level mockLevel = new Level(null, null, null) {
			@Override
			public boolean isPositionFree(int x, int y) {
				return x == freePositionToTheLeft.x && y == freePositionToTheLeft.y;
			}
		};
		//act
		boolean result = freeSensor.check(params, currentPosition, mockLevel);
		//assert
		assertTrue(result);
	}

}
