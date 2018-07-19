package beepBoop.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;

import beepBoop.model.Inventory;
import beepBoop.model.Resource;
import beepBoop.model.TileFactory;

public class InventoryTest {	
	
	@Test
	public void addResource_addNewResource_containsCorrectAmount() {
		Inventory inventory = new Inventory();
		String someResourceType = "iron";
		int someAmount = 40;
		Resource someResource = new Resource(someAmount, TileFactory.IRON, someResourceType);
				
		inventory.addRessource(someResource);
		
		assertTrue(containsCorrectAmountOf(inventory, someResourceType, someAmount));		
	
	}
	
	@Test
	public void addResource_addExistingResource_containsSum() {
		Inventory inventory = new Inventory();
		HashSet<Resource> resources = new HashSet<Resource>();
		String someResourceType = "iron";
		int someAmount = 40;
		Resource someResource = new Resource(someAmount, TileFactory.IRON, someResourceType);
		resources.add(someResource);
		int anotherAmount = 10;
		Resource moreOfTheSame = new Resource(anotherAmount, TileFactory.IRON, someResourceType);
		inventory.setRessources(resources);
		
		inventory.addRessource(moreOfTheSame);
		
		int correctAmount = someAmount + anotherAmount;
		assertTrue(containsCorrectAmountOf(inventory, someResourceType, correctAmount));		
	
	}
	
	@Test
	public void subtractResource_subtractSome_containsCorrectAmount() {
		Inventory inventory = new Inventory();
		HashSet<Resource> resources = new HashSet<Resource>();
		String someResourceType = "iron";
		int someAmount = 40;
		Resource someResource = new Resource(someAmount, TileFactory.IRON, someResourceType);
		resources.add(someResource);
		int lesserAmount = 10;
		Resource lessOfTheSame = new Resource(lesserAmount, TileFactory.IRON, someResourceType);
		inventory.setRessources(resources);
		
		inventory.subtractResource(lessOfTheSame);
		
		int correctAmount = someAmount - lesserAmount;
		assertTrue(containsCorrectAmountOf(inventory, someResourceType, correctAmount));		
	
	}
	
	@Test
	public void subtractResource_tryToSubtractTooMuch_returnFalse() {
		Inventory inventory = new Inventory();
		HashSet<Resource> resources = new HashSet<Resource>();
		String someResourceType = "iron";
		int someAmount = 41;
		Resource someResource = new Resource(someAmount, TileFactory.IRON, someResourceType);
		resources.add(someResource);
		int greaterAmount = 42;
		Resource moreOfTheSame = new Resource(greaterAmount, TileFactory.IRON, someResourceType);
		inventory.setRessources(resources);
		
		boolean subtractionSuccesful = inventory.subtractResource(moreOfTheSame);
		
		
		assertFalse(subtractionSuccesful);		
	
	}
	


	private boolean containsCorrectAmountOf(Inventory inventory, String resourceType, int correctAmount) {
		for (Resource resource : inventory.getRessources()) {
			if (resource.getName().equals(resourceType)) {
				return resource.getAmount() == correctAmount;
			}
		}
		System.out.println("Inventory test did not find the resource it was looking for.");
		return false;
	}

}
