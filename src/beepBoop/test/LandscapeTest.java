package beepBoop.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Dimension;

import org.junit.Test;

import beepBoop.model.Landscape;
import beepBoop.model.Tile;
import beepBoop.service.TileFactory;

/**
 * PTP 2018 BeepBoop - the RobotGame
 * 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 */
public class LandscapeTest {

	@Test
	public void placeRect_placeLegalRect_findCorrectTileInNewlyPlacedRect() {
		// arrange
		int someWidth = 100;
		int someHeight = 100;
		int topLeftRectCornerX = 0;
		int topLeftRectCornerY = 0;
		int bottomRightRectCornerX = 10;
		int bottomRightRectCornerY = 10;
		Dimension landscapeDimensions = new Dimension(someWidth, someHeight);
		Landscape landscape = new Landscape(landscapeDimensions);
		// act
		landscape.placeRect(topLeftRectCornerX, 
				            topLeftRectCornerY, 
				            bottomRightRectCornerX, 
				            bottomRightRectCornerY,
				            TileFactory.EARTH_0);
		// assert
		Tile justPlaced = landscape.getTile(topLeftRectCornerX, topLeftRectCornerY);
		TileFactory tf = TileFactory.getInstance();
		assertEquals("wrong tile at " + topLeftRectCornerX + ", " + topLeftRectCornerY, 
				     justPlaced, tf.get(TileFactory.EARTH_0));
	}

	@Test
	public void placeRect_outsideOfLandscape_throwIllegalArgumentException() {
		// arrange
		int someWidth = 100;
		int someHeight = 100;
		int topLeftRectCornerX = 0;
		int topLeftRectCornerY = 0;
		int illegalBottomRightRectCornerX = someWidth + 1;
		int bottomRightRectCornerY = 10;
		Dimension landscapeDimensions = new Dimension(someWidth, someHeight);
		Landscape landscape = new Landscape(landscapeDimensions);
		// act & assert
		try {
			landscape.placeRect(topLeftRectCornerX, 
					            topLeftRectCornerY, 
					            illegalBottomRightRectCornerX,
					            bottomRightRectCornerY, 
					            TileFactory.EARTH_0);
			fail("No Exception thrown.");
		} catch (IllegalArgumentException e) {

		} catch (Exception e) {
			fail("Wrong Exception thrown.");
		}

	}

	@Test
	public void place_insideOfLevel_correctTilePlaced() {
		// arrange
		int someWidth = 100;
		int someHeight = 100;
		int someX = 0;
		int someY = 0;
		Dimension landscapeDimensions = new Dimension(someWidth, someHeight);
		Landscape landscape = new Landscape(landscapeDimensions);
		// act
		landscape.place(someX, someY, TileFactory.EARTH_0);
		// assert
		Tile justPlaced = landscape.getTile(someX, someY);
		TileFactory tf = TileFactory.getInstance();
		assertEquals("wrong tile at " + someX + ", " + someY, 
				     justPlaced, tf.get(TileFactory.EARTH_0));
	}

	@Test
	public void place_outsideOfLandscape_throwIllegalArgumentException() {
		// arrange
		int someWidth = 100;
		int someHeight = 100;
		int someX = 0;
		int illegalY = someHeight + 1;
		Dimension landscapeDimensions = new Dimension(someWidth, someHeight);
		Landscape landscape = new Landscape(landscapeDimensions);
		// act & assert
		try {
			landscape.place(someX, illegalY, TileFactory.EARTH_0);
			fail("No Exception thrown.");
		} catch (IllegalArgumentException e) {

		} catch (Exception e) {
			fail("Wrong Exception thrown.");
		}

	}

	@Test
	public void setSize_shrink_tileKeepsPosition() {
		// arrange
		int someWidth = 100;
		int someHeight = 100;
		int smallerWidth = 89;
		int smallerHeight = 87;
		int someX = 50;
		int someY = 25;
		Dimension originalDimensions = new Dimension(someWidth, someHeight);
		Dimension shrunkDimensions = new Dimension(smallerWidth, smallerHeight);
		Landscape landscape = new Landscape(originalDimensions);
		landscape.place(someX, someY, TileFactory.GRASS_1);
		// act
		landscape.setSize(shrunkDimensions);
		// assert
		Tile tileInQuestion = landscape.getTile(someX, someY);
		TileFactory tf = TileFactory.getInstance();
		assertEquals("wrong tile at " + someX + ", " + someY, 
				     tileInQuestion, 
				     tf.get(TileFactory.GRASS_1));

	}

}
