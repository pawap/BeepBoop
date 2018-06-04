package beepBoop.model.resource;

import beepBoop.model.TileFactory;

public class Silicon extends Resource {

	public Silicon(int amount) {
		super(amount, TileFactory.SILICON, "silicon");
	}
}