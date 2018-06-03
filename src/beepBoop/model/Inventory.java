package beepBoop.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import beepBoop.model.resource.Resource;

public class Inventory extends Observable {
	private Set<Resource> ressources;
	
	public Inventory() {
		this.ressources = new HashSet<Resource>();
	}

	public Set<Resource> getRessources() {
		return ressources;
	}

	public void setRessources(Set<Resource> ressources) {
		this.ressources = ressources;
	}



	public void addRessource(Resource ressource) {
		for (Resource res: ressources) {
			if (res.getName().equals(ressource.getName())) {		
				res.increaseAmount(ressource.getAmount());
				return;
			}
		}
		ressources.add(ressource);
	}
	
	public boolean subtractRessource(Resource ressource) {
		for (Resource res: ressources) {
			if (res.getName().equals(ressource.getName()) && res.getAmount() >= ressource.getAmount()) {		
				res.takeAmount(ressource.getAmount());
				this.notifyObservers();
				return true;
				
			}
		}
		return false;
	}
	
}
