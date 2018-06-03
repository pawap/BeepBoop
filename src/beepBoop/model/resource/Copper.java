package beepBoop.model.resource;

import beepBoop.model.TileFactory;

public class Copper extends Resource {

	public Copper(int amount) {
		super(amount, TileFactory.COPPER, "Copper");
	}
}