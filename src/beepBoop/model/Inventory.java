package beepBoop.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;

public class Inventory extends Observable implements Serializable {

	private static final long serialVersionUID = -4001842018972736252L;
	private HashSet<Resource> resources;
	
	public Inventory() {
		this.resources = new HashSet<Resource>();
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(HashSet<Resource> resources) {
		this.resources = resources;
	}



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
	 * If the inventory contains at least as many resources as needed, subtract those
	 * resources and return true.
	 * @param costs the needed resources as a List
	 * @return true if the cost could be paid
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
