package beepBoop.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Test;
 
import beepBoop.model.Level;
import beepBoop.model.Resource;
import beepBoop.model.ResourceSensor;
import beepBoop.service.TileFactory;
import beepBoop.test.mock.MockLevel;

/**
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 */
public class ResourceSensorTest {
	
	@Test
	public void check_resourceAtPosition_returnTrue(){
		//arrange
		ResourceSensor resourceSensor = new ResourceSensor();
		String directionToCheck = "L";
		String[] params = new String[] {"someString", directionToCheck};
		Point currentPosition = new Point(1,1);
		Point positionToTheLeft = new Point(0,1);
		int someAmount = 10;
		Resource someResource = new Resource(someAmount,
				                             TileFactory.COPPER,
				                             "copper"); 
		Level mockLevel = new MockLevel(someResource, positionToTheLeft);
		//act
		boolean result = resourceSensor.check(params, currentPosition, mockLevel);
		//assert
		assertTrue(result);
	}
	
	@Test
	public void check_noResourceAtPosition_returnFalse(){
		//arrange
		ResourceSensor resourceSensor = new ResourceSensor();
		String directionToCheck = "L";
		String[] params = new String[] {"someString", directionToCheck};
		Point currentPosition = new Point(1,1);
		Level mockLevel = new MockLevel();
		//act
		boolean result = resourceSensor.check(params, currentPosition, mockLevel);
		//assert
		assertFalse(result);
	}

}
