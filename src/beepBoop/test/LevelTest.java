package beepBoop.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Set;

import org.junit.Test;

import beepBoop.model.Landscape;
import beepBoop.model.Level;
import beepBoop.model.Player;
import beepBoop.model.Resource;
import beepBoop.model.BasicRobot;
import beepBoop.model.Thing;
import beepBoop.model.TileFactory;

/**
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 */
public class LevelTest {
	
	@Test
	public void isPositionFree_positionIsFree_returnTrue(){
		//arrange
		Level level = createTenByTenLevelWithPlayerAtOrigin();
		Point freePosition = new Point(4,2);
		//act
		boolean result = level.isPositionFree(freePosition.x, freePosition.y);
		//assert
		assertTrue(result);
	}	
	
	@Test
	public void isPositionFree_blockedByNonwalkableTile_returnFalse(){
		//arrange
		Level level = createTenByTenLevelWithPlayerAtOrigin();
		Point nonwalkablePosition = new Point(4,2);
		level.getLandscape().place(nonwalkablePosition.x, 
				                   nonwalkablePosition.y,
				                   TileFactory.ROCK_0);
		//act
		boolean result = level.isPositionFree(nonwalkablePosition.x,
				                              nonwalkablePosition.y);
		//assert
		assertFalse(result);
	}
	
	@Test
	public void isPositionFree_blockedByThing_returnFalse(){
		//arrange
		Level level = createTenByTenLevelWithPlayerAtOrigin();
		Point positionOfThing = new Point(4,2);
		Thing someThing = createThingAt(positionOfThing);
		level.addThing(someThing);
		//act
		boolean result = level.isPositionFree(positionOfThing.x,
				                              positionOfThing.y);
		//assert
		assertFalse(result);
	}

	@Test
	public void isPositionFree_blockedByPlayer_returnFalse(){
		//arrange
		Level level = createTenByTenLevelWithPlayerAtOrigin();
		Point origin = new Point(0,0);
		//act
		boolean result = level.isPositionFree(origin.x,
				                              origin.y);
		//assert
		assertFalse(result);
	}
	
	@Test
	public void addThing_otherThingAlreadyAtSamePosition_returnFalse() {
		//arrange
		Level level = createTenByTenLevelWithPlayerAtOrigin();
		Point positionOfThing = new Point(4,4);
		Thing blockingThing = createThingAt(positionOfThing);
		level.addThing(blockingThing);
		Thing blockedThing = createThingAt(positionOfThing);
		//act
		boolean result = level.addThing(blockedThing);
		//assert
		assertFalse(result);		
		
	}
	
	@Test
	public void addThing_otherThingAlreadyAtSamePosition_newThingNotAdded() {
		//arrange
		Level level = createTenByTenLevelWithPlayerAtOrigin();
		Point positionOfThing = new Point(4,4);
		Thing blockingThing = createThingAt(positionOfThing);
		level.addThing(blockingThing);
		Thing blockedThing = createThingAt(positionOfThing);
		//act
		level.addThing(blockedThing);
		//assert
		Set<Thing> things = level.getThings();
		for (Thing thing : things) {
			assertFalse(thing.equals(blockedThing));
		}
	}
	
	@Test
	public void addThing_noOtherThingAtSamePosition_newThingAdded() {
		//arrange
		Level level = createTenByTenLevelWithPlayerAtOrigin();
		Point positionOfThing = new Point(4,4);
		Thing someThing = createThingAt(positionOfThing);
		//act
		level.addThing(someThing);
		//assert
		assertTrue(level.getThing(positionOfThing.x, 
				                  positionOfThing.y).equals(someThing));
	}
	
	@Test
	public void moveThing_movePossible_thingIsAtNewPosition() {
		//arrange
		Level level = createTenByTenLevelWithPlayerAtOrigin();
		Point oldPosition = new Point(4,4);
		Thing someThing = createThingAt(oldPosition);
		level.addThing(someThing);
		Point newPosition = new Point(3,1);
		//act
		level.moveThing(oldPosition, newPosition);
		//assert
		assertTrue(someThing.equals(level.getThing(newPosition.x, 
                                                    newPosition.y)));
	}
	
	@Test
	public void moveThing_movePossible_thingIsNotAtOldPosition() {
		//arrange
		Level level = createTenByTenLevelWithPlayerAtOrigin();
		Point oldPosition = new Point(4,4);
		Thing someThing = createThingAt(oldPosition);
		level.addThing(someThing);
		Point newPosition = new Point(3,1);
		//act
		level.moveThing(oldPosition, newPosition);
		//assert
		assertFalse(someThing.equals(level.getThing(oldPosition.x, 
				                                    oldPosition.y)));		
	}
	
	@Test
	public void addRobot_robotAlreadyPresent_returnFalse() {
		//arrange
		Level level = createTenByTenLevelWithPlayerAtOrigin();
		BasicRobot someRobot = new BasicRobot();
		Point positionInsideLandscape = new Point(1,2);
		someRobot.setPosition(positionInsideLandscape);
		level.addRobot(someRobot);
		//act
		boolean result = level.addRobot(someRobot);
		//assert
		assertFalse(result);	
	}
	
	@Test
	public void addRobot_robotAddable_robotIsInRobotQueue() {
		//arrange
		Level level = createTenByTenLevelWithPlayerAtOrigin();
		BasicRobot someRobot = new BasicRobot();
		Point positionInsideLandscape = new Point(1,2);
		someRobot.setPosition(positionInsideLandscape);
		//act
		level.addRobot(someRobot);
		//assert
		assertTrue(level.getRobotQueue().contains(someRobot));
	}
	
	@Test
	public void addRobot_robotAddable_robotIsInThings() {
		//arrange
		Level level = createTenByTenLevelWithPlayerAtOrigin();
		BasicRobot someRobot = new BasicRobot();
		Point positionInsideLandscape = new Point(1,2);
		someRobot.setPosition(positionInsideLandscape);
		//act
		level.addRobot(someRobot);
		//assert
		assertTrue(level.getThings().contains(someRobot));
	}
	
	//helpers
	private Landscape createTenByTenGrassLandscape() {
		Dimension landscapeSize = new Dimension(10,10);
		Landscape  someLandscape = new Landscape(landscapeSize);
		someLandscape.placeRect(0, 0, 
				                landscapeSize.width - 1, 
				                landscapeSize.height - 1,
				                TileFactory.GRASS_0);
		return someLandscape;
	}
	
	private Level createTenByTenLevelWithPlayerAtOrigin() {
		Landscape someLandscape = createTenByTenGrassLandscape();
		Point origin = new Point (0,0);
		Player player = new Player();
		player.setPosition(origin);
		Level level = new Level(someLandscape, player, null);
		return level;
	}

	private Thing createThingAt(Point positionOfThing) {
		int someAmount = 42;
		Thing thing = new Resource(someAmount, 
				                   TileFactory.NULL_TILE, 
				                   "someType");
		thing.setPosition(positionOfThing);
		return thing;
	}
}
