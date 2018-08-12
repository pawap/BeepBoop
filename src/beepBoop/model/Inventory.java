package beepBoop.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;

/**
 * PTP 2018
 * BeepBoop - the RobotGame
 * 
 * The Inventory holds the Resources the player and their robots have collected.
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class Inventory extends Observable implements Serializable {

	private static final long serialVersionUID = -4001842018972736252L;
	private HashSet<Resource> resources;
	
	/**
	 * Constructor
	 */
	public Inventory() {
		this.resources = new HashSet<Resource>();
	}

	/**
	 * @return the Resources stored in the Inventory
	 */
	public Set<Resource> getResources() {
		return resources;
	}

	/**
	 * Replaces the Resources in the Inventory.
	 * @param resources a new set of Resources
	 */
	public void setResources(HashSet<Resource> resources) {
		this.resources = resources;
	}

    /**
     * Add a Resource to the Inventory.
     * @param resource the Resource to be added
     */
	public void addResource(Resource resource) {
		for (Resource res: resources) {
			if (res.getName().equals(resource.getName())) {		
				res.increaseAmount(resource.getAmount());
				this.setChanged();
				this.notifyObservers();
				return;
			}
		}
		resources.add(resource);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Subtract an amount from a held Resource if at least as much is held as is supposed to be subtracted. 
	 * @param resource the type and amount of Resource that should be subtracted
	 * @return true, if the Inventory held enough of the Resource and the subtraction was successful
	 */
	public boolean subtractResource(Resource resource) {
		for (Resource res: resources) {
			if (res.getName().equals(resource.getName()) && res.getAmount() >= resource.getAmount()) {		
				res.takeAmount(resource.getAmount());
				this.setChanged();
				this.notifyObservers();
				return true;
				
			}
		}
		return false;
	}

	/**
	 * If the inventory contains at least as many Resources as needed, subtract
	 * the needed Resources and return true.
	 * @param costs the needed Resources as a List
	 * @return true, if the cost could be paid
	 */
	public boolean pay(List<Resource> costs) {	
		for (Resource cost : costs) {
			for (Resource stock: resources) {
				if (cost.getName().equals(stock.getName())) {
					if (cost.getAmount() > stock.getAmount()) {
						return false;
					}
				}
			}
		}
		for (Resource cost : costs) {
			this.subtractResource(cost);
		}
		return !resources.isEmpty();
	}
	
}
