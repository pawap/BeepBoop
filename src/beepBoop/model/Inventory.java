package beepBoop.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import beepBoop.model.ressource.Ressource;

public class Inventory extends Observable {
	private Set<Ressource> ressources;
	
	public Inventory() {
		this.ressources = new HashSet<Ressource>();
	}

	public Set<Ressource> getRessources() {
		return ressources;
	}

	public void setRessources(Set<Ressource> ressources) {
		this.ressources = ressources;
	}



	public void addRessource(Ressource ressource) {
		for (Ressource res: ressources) {
			if (res.getName().equals(ressource.getName())) {		
				res.increaseAmount(ressource.getAmount());
				return;
			}
		}
		ressources.add(ressource);
	}
	
	public boolean subtractRessource(Ressource ressource) {
		for (Ressource res: ressources) {
			if (res.getName().equals(ressource.getName()) && res.getAmount() >= ressource.getAmount()) {		
				res.takeAmount(ressource.getAmount());
				this.notifyObservers();
				return true;
				
			}
		}
		return false;
	}
	
}
