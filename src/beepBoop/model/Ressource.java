package beepBoop.model;

import java.awt.Image;

abstract public class Ressource extends Thing {
	protected int amount;
	
	public Ressource(int amount) {
		super();
		this.amount = amount;
		
	}

	public int takeAmount(int quantity) {
		int taken = Math.min(quantity, amount);
		amount -= taken;
		return taken;
	}

	abstract public String getName();

	public int getAmount() {
		// TODO Auto-generated method stub
		return this.amount;
	}

}
