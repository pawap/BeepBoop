package beepBoop.model.resource;

import beepBoop.model.TileFactory;

public class Gold extends Resource {

	public Gold(int amount) {
		super(amount, TileFactory.GOLD, "Gold");
	}
}
