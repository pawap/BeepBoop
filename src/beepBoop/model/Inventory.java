package beepBoop.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public class Inventory extends Observable implements Serializable {

	private static final long serialVersionUID = -4001842018972736252L;
	private HashSet<Resource> resources;
	
	public Inventory() {
		this.resources = new HashSet<Resource>();
	}

	public Set<Resource> getRessources() {
		return resources;
	}

	public void setRessources(HashSet<Resource> resources) {
		this.resources = resources;
	}



	public void addRessource(Resource resource) {
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
	
}
