package beepBoop.test;

import static org.junit.Assert.*;

import org.junit.Test;

import beepBoop.model.Resource;
import beepBoop.service.TileFactory;

/**
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 */
public class ResourceTest{

	@Test
	public void takeAmount_takeAll_AmountIsZero() {
		//arrange
		int someAmount = 42;
		Resource someResource = new Resource(someAmount, TileFactory.GOLD, "someName");
		//act
		someResource.takeAmount(someAmount);
		//assert
		assertTrue("Took everything. What was left was not 0", 
				someResource.getAmount() == 0);
	}
	
	@Test
	public void takeAmount_takeSome_AmountIsDifference() {
		//arrange
		int someAmount = 42;
		int someSmallerAmount = 23;
		Resource someResource = new Resource(someAmount, TileFactory.GOLD, "someName");
		//act
		someResource.takeAmount(someSmallerAmount);
		//assert
		assertTrue("Took some. What was left was not equal to the difference.", 
				someResource.getAmount() == someAmount - someSmallerAmount);
	}

	@Test
	public void takeAmount_takeTooMuch_AmountIsZero() {
		//arrange
		int someAmount = 42;
		int someGreaterAmount = 555;
		Resource someResource = new Resource(someAmount, TileFactory.GOLD, "someName");
        //act
		someResource.takeAmount(someGreaterAmount);
		//assert
		assertTrue("Took too much. What was left was not 0.", 
				someResource.getAmount() == 0);
	}
	
	@Test
	public void takeAmount_takeTooMuch_returnAllAvailable() {
		//arrange
		int someAmount = 42;
		int someGreaterAmount = 555;
		Resource someResource = new Resource(someAmount, TileFactory.GOLD, "someName");
		//act
		int amountTaken = someResource.takeAmount(someGreaterAmount);
		//assert
		assertTrue("Took too much. Did not return as much as had been there.", 
				amountTaken == someAmount);
	}
	
	@Test
	public void increaseAmount_increaseALittle_AmountEqualsSum() {
		//arrange
		int someAmount = 42;
		int someOtherAmount = 24;
		Resource someResource = new Resource(someAmount, TileFactory.GOLD, "someName");
		//act
		someResource.increaseAmount(someOtherAmount);
		//assert
		assertTrue("Wrong amount after increase.",
				someResource.getAmount() == someAmount + someOtherAmount);
	}

}
