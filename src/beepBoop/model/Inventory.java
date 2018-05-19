package beepBoop.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

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
				res.amount += ressource.amount;
				return;
			}
		}
		ressources.add(ressource);
	}
	
	public boolean subtractRessource(Ressource ressource) {
		for (Ressource res: ressources) {
			if (res.getName().equals(ressource.getName()) && res.amount >= ressource.amount) {		
				res.takeAmount(ressource.amount);
				this.notifyObservers();
				return true;
				
			}
		}
		return false;
	}
	
}
