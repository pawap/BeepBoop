package beepBoop.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Point;

import org.junit.Test;

import beepBoop.model.Command;

/**
 * PTP 2018 BeepBoop - the RobotGame
 * 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 */
public class CommandTest {

	@SuppressWarnings("unused")
	@Test
	public void constructor_callWithEmptyString_throwsIllegalArgumentException() {
		// arrange
		String emptyString = "";
		// act & assert
		try {
			Command command = new Command(emptyString);
			fail("Should have thrown IllegalArgumentException. Didn't throw anything.");
		} catch (IllegalArgumentException e) {

		} catch (Exception e) {
			fail("Should have thrown IllegalArgumentException. Wrong exception thrown.");
		}
	}

	@Test
	public void constructor_validCall_typeIsFirstPartOfString() {
		// arrange
		String validCommandString = "CommandType Arg1 Arg2";
		// act
		Command someCommand = new Command(validCommandString);
		String type = someCommand.getType();
		// assert
		assertEquals("Wrong command type: " + type + ". Should have been CommandType.", type, "CommandType");
	}

	@Test
	public void constructor_validCall_LastArgsIsLastPartOfString() {
		// arrange
		String validCommandString = "CommandType Arg1 Arg2";
		String target = "Arg2";
		// act
		Command someCommand = new Command(validCommandString);
		// assert
		String[] args = someCommand.getArgs();
		String lastArg = args[args.length - 1];

		assertEquals("Last command arg was: " + args, lastArg, target);
	}

	@Test
	public void getPointFromDirection_dirL_getNextPointToLeft() {
		// arrange
		Point somePosition = new Point(1, 1);
		Point targetPosition = new Point(0, 1);
		String direction = "L";
		// act
		Point newPosition = Command.getPointFromDirection(somePosition, direction);
		// assert
		assertEquals("New position should have been " + targetPosition + ", was " + newPosition, newPosition,
				targetPosition);
	}

	@Test
	public void getPointFromDirection_dirR_getNextPointToLeft() {
		// arrange
		Point somePosition = new Point(1, 1);
		Point targetPosition = new Point(2, 1);
		String direction = "R";
		// act
		Point newPosition = Command.getPointFromDirection(somePosition, direction);
		// assert
		assertEquals("New position should have been " + targetPosition + ", was " + newPosition, newPosition,
				targetPosition);
	}

	@Test
	public void getPointFromDirection_dirU_getNextPointToLeft() {
		// arrange
		Point somePosition = new Point(1, 1);
		Point targetPosition = new Point(1, 0);
		String direction = "U";
		// act
		Point newPosition = Command.getPointFromDirection(somePosition, direction);
		// assert
		assertEquals("New position should have been " + targetPosition + ", was " + newPosition, newPosition,
				targetPosition);
	}

	@Test
	public void getPointFromDirection_dirD_getNextPointToLeft() {
		// arrange
		Point somePosition = new Point(1, 1);
		Point targetPosition = new Point(1, 2);
		String direction = "D";
		// act
		Point newPosition = Command.getPointFromDirection(somePosition, direction);
		// assert
		assertEquals("New position should have been " + targetPosition + ", was " + newPosition, newPosition,
				targetPosition);
	}
}
