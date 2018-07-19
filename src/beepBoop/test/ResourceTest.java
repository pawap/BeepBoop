package beepBoop.test;

import static org.junit.Assert.*;

import org.junit.Test;

import beepBoop.model.Resource;
import beepBoop.model.TileFactory;

public class ResourceTest{

	@Test
	public void takeAmount_takeAll_AmountIsZero() {
		int someAmount = 42;
		Resource someResource = new Resource(someAmount, TileFactory.GOLD, "someName");

		someResource.takeAmount(someAmount);
		
		assertTrue("Took everything. What was left was not 0", 
				someResource.getAmount() == 0);
	}
	
	@Test
	public void takeAmount_takeSome_AmountIsDifference() {
		int someAmount = 42;
		int someSmallerAmount = 23;
		Resource someResource = new Resource(someAmount, TileFactory.GOLD, "someName");

		someResource.takeAmount(someSmallerAmount);
		
		assertTrue("Took some. What was left was not equal to the difference.", 
				someResource.getAmount() == someAmount - someSmallerAmount);
	}

	@Test
	public void takeAmount_takeTooMuch_AmountIsZero() {
		int someAmount = 42;
		int someGreaterAmount = 555;
		Resource someResource = new Resource(someAmount, TileFactory.GOLD, "someName");

		someResource.takeAmount(someGreaterAmount);
		
		assertTrue("Took too much. What was left was not 0.", 
				someResource.getAmount() == 0);
	}
	
	@Test
	public void takeAmount_takeTooMuch_returnAllAvailable() {
		int someAmount = 42;
		int someGreaterAmount = 555;
		Resource someResource = new Resource(someAmount, TileFactory.GOLD, "someName");

		int amountTaken = someResource.takeAmount(someGreaterAmount);
		
		assertTrue("Took too much. Did not return as much as hab been there.", 
				amountTaken == someAmount);
	}
	
	@Test
	public void increaseAmount_increaseALittle_AmountEqualsSum() {
		int someAmount = 42;
		int someOtherAmount = 24;
		Resource someResource = new Resource(someAmount, TileFactory.GOLD, "someName");
	
		someResource.increaseAmount(someOtherAmount);
		
		assertTrue("Wrong amount after increase.",
				someResource.getAmount() == someAmount + someOtherAmount);
	}

}
