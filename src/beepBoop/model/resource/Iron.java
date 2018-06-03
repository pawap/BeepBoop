package beepBoop.model.resource;

import beepBoop.model.TileFactory;

public class Iron extends Resource {

	public Iron(int amount) {
		super(amount, TileFactory.IRON, "Iron");
	}
}