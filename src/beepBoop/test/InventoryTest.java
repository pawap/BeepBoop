package beepBoop.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import beepBoop.model.Inventory;
import beepBoop.model.ressource.Copper;
import beepBoop.model.ressource.Gold;
import beepBoop.model.ressource.Iron;
import beepBoop.model.ressource.Platinum;
import beepBoop.model.ressource.Ressource;
import beepBoop.model.ressource.Silicon;

class InventoryTest {

	private Inventory inv;
	private HashSet<Ressource> ressources;
	
	
	@BeforeEach
	void setup() {
		inv = new Inventory();
		ressources = new HashSet<Ressource>();
		ressources.add(new Copper(100));
		ressources.add(new Iron(40));
		
	}
	
	/**
	 * Tests getRessources() and setRessources(Set<Ressource>)
	 */
	@Test
	void getAndSetRessourcesTest() {
		HashSet<Ressource> noRessources = new HashSet<Ressource>();
		
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
	void addAndSubstractRessourceTest() {
		inv.setRessources(ressources);
		assertFalse(inv.subtractRessource(new Copper(101)));
		assertFalse(inv.subtractRessource(new Gold(100)));
		assertTrue(inv.subtractRessource(new Copper(100)));
		inv.addRessource(new Iron(2));
		inv.addRessource(new Silicon(314159));
		Set<Ressource> currRess = inv.getRessources();
		Boolean containsCu = false, containsFe = false, containsSi = false;
		for (Ressource r : currRess) {
			String resType = r.getName().toLowerCase();
			switch(resType) {
			case("copper"):
				assertTrue(r.getAmount() == 0);
			    containsCu = true;
			    break;
			case("iron"):
				assertTrue(r.getAmount() == 42);
			    containsFe = true;
			    break;
			case("silicon"):
				assertTrue(r.getAmount() == 314159);
			    containsSi = true;
			    break;
			default:
				fail("The Ressources should not contain " + resType + " at this point.");
			}
		}
		assertTrue(containsCu);
		assertTrue(containsFe);
		assertTrue(containsSi);
	}

}
