package beepBoop.test;

import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Test;
 
import beepBoop.model.Level;
import beepBoop.model.Resource;
import beepBoop.model.ResourceSensor;
import beepBoop.model.Thing;
import beepBoop.model.TileFactory;

public class ResourceSensorTest {
	
	@Test
	public void check_resourceAtPosition_returnTrue(){
		//arrange
		ResourceSensor resourceSensor = new ResourceSensor();
		String directionToCheck = "L";
		String[] params = new String[] {"someString", directionToCheck};
		Point currentPosition = new Point(1,1);
		Point positionToCheck = new Point(0,1);
		int someAmount = 10;
		Resource someResource = new Resource(someAmount,
				                             TileFactory.COPPER,
				                             "copper"); 
		someResource.setPosition(positionToCheck);
		@SuppressWarnings("serial")
		Level mockLevel = new Level(null, null, null) {
			@Override
			public Thing getThing(int x, int y) {
				if (x == positionToCheck.x && y == positionToCheck.y) {
					return someResource;
				}
				return null;
			}
		};
		//act
		boolean result = resourceSensor.check(params, currentPosition, mockLevel);
		//assert
		assertTrue(result);
	}

}
