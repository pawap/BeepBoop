package beepBoop.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Test;

import beepBoop.model.FreeSensor;
import beepBoop.model.Level;
import beepBoop.test.mock.MockLevel;

/**
 * PTP 2018 BeepBoop - the RobotGame
 * 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 */
public class FreeSensorTest {

	@Test
	public void check_freePosition_returnTrue() {
		// arrange
		FreeSensor freeSensor = new FreeSensor();
		String directionToCheck = "L";
		String[] params = new String[] { "someString", directionToCheck };
		Point currentPosition = new Point(1, 1);
		Level mockLevel = new MockLevel();
		// act
		boolean result = freeSensor.check(params, currentPosition, mockLevel);
		// assert
		assertTrue(result);
	}

	@Test
	public void check_blockedPosition_returnFalse() {
		// arrange
		FreeSensor freeSensor = new FreeSensor();
		String directionToCheck = "L";
		String[] params = new String[] { "someString", directionToCheck };
		Point currentPosition = new Point(1, 1);
		Point blockedPosition = new Point(0, 1);
		Level mockLevel = new MockLevel(blockedPosition);
		// act
		boolean result = freeSensor.check(params, currentPosition, mockLevel);
		// assert
		assertFalse(result);
	}

}
