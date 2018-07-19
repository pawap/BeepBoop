package beepBoop.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import beepBoop.model.Resource;

public class ResourceTest {
	private Resource copper, iron, gold, platinum, silicon;
	
	@Before
	public void setup() {
//		copper = new Copper(42);
//		iron = new Iron(42);
//		gold = new Gold(42);
//		platinum = new Platinum(42);
//		silicon = new Silicon(42);
	}

	@Test
	public void equalsTest() {
//		assertTrue("Resource is not equal to itself.", 
//				copper.equals(copper));
//		assertTrue("Resources of the same type should be equal",
//				iron.equals(new Iron(2))); 
//		assertFalse("Different resources should not be considered equal.",
//				iron.equals(copper));
	}
	
	/**
	 * Tests the methods fiddling with the amount of a resource:
	 * takeAmount(int), getAmount(), increaseAmount(int)
	 */
	@Test
	public void changeAmount() {
		assertTrue("Incorrect amount returned by getAmount()",
				platinum.getAmount() == 42);
		assertTrue("Amount taken is wrong", silicon.takeAmount(42) == 42);
		assertTrue("Wrong amount after takeAmount(int)",
				silicon.getAmount() == 0);
		assertTrue("If you try to take more than there is, you get should get all there is",
				platinum.takeAmount(43) == 42);
		assertTrue("Wrong amount after takeAmount(int)",
				platinum.getAmount() == 0);
		silicon.increaseAmount(17);
		assertTrue("Wrong amount after increaseAmount(int)",
				silicon.getAmount() == 17);
	}

}
