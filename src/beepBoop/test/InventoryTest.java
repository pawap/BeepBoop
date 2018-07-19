package beepBoop.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import beepBoop.model.Inventory;
import beepBoop.model.Resource;
import beepBoop.model.TileFactory;

public class InventoryTest {

	private Inventory inv;
	private HashSet<Resource> ressources;
	
	
	@Before
	public void setup() {
		inv = new Inventory();
		ressources = new HashSet<Resource>();
		ressources.add(new Resource(100, TileFactory.COPPER, "copper"));
		ressources.add(new Resource(40, TileFactory.IRON, "iron"));
		
	}
	
	/**
	 * Tests getRessources() and setRessources(Set<Ressource>)
	 */
	@Test
	public void getAndSetRessourcesTest() {
		HashSet<Resource> noRessources = new HashSet<Resource>();
		
		assertTrue(inv.getRessources().isEmpty());
		inv.setRessources(ressources);
		assertFalse(inv.getRessources().isEmpty());
		assertEquals(ressources, inv.getRessources());
		inv.setRessources(noRessources);
		assertTrue(inv.getRessources().isEmpty());
	}
	/**
	 * Test method for addRessource(Ressource) and substractRessource(Ressource)
	 */
	@Test
	public void addAndSubstractRessourceTest() {
//		inv.setRessources(ressources);
//		assertFalse(inv.subtractRessource(new Copper(101))); //don't substract too much
//		assertFalse(inv.subtractRessource(new Gold(100))); //don't substract what isn't there
//		assertTrue(inv.subtractRessource(new Copper(100)));
//		inv.addRessource(new Iron(2));
//		inv.addRessource(new Silicon(314159));
//		Set<Resource> currRess = inv.getRessources();
//		Boolean containsCu = false, containsFe = false, containsSi = false;
//		for (Resource r : currRess) {
//			String resType = r.getName().toLowerCase();
//			switch(resType) {
//			case("copper"):
//				assertTrue(r.getAmount() == 0);
//			    containsCu = true;
//			    break;
//			case("iron"):
//				assertTrue(r.getAmount() == 42);
//			    containsFe = true;
//			    break;
//			case("silicon"):
//				assertTrue(r.getAmount() == 314159);
//			    containsSi = true;
//			    break;
//			default:
//				fail("The Ressources should not contain " + resType + " at this point.");
//			}
//		}
//		assertTrue(containsCu);
//		assertTrue(containsFe);
//		assertTrue(containsSi);
	}

}
