package beepBoop.model;

import java.awt.Point;

import beepBoop.service.TileFactory;

/**
 * This event drops a certain number of each resource with random amounts (less than 100) in random places. 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class ResourceDropEvent extends Event {

	private int dropsPerType;
	
	/**
	 * Constructor
	 * 
	 * @param dropsPerType number of resources to drop per type
	 */
	public ResourceDropEvent(int dropsPerType) {
		super();
		this.dropsPerType = dropsPerType;
	}



	@Override
	public void performChanges(Level level) {
		for (String tileName: new String[]{"silicon","platinum","iron","gold","copper"}) {			
			for (int i = 0; i < dropsPerType; i++) {
				int tileId = 0;
				switch(tileName) {
					case "silicon" :  tileId = TileFactory.SILICON; break;
					case "platinum": tileId = TileFactory.PLATINUM; break;
					case "iron": tileId = TileFactory.IRON; break;
					case "gold": tileId = TileFactory.GOLD; break;
					case "copper": tileId = TileFactory.COPPER; break;
				}
				Resource resource = new Resource((int) Math.round(Math.random() * 100) + 1, tileId, tileName);
				resource.setPosition(new Point((int) Math.round(Math.random() * 50), (int) Math.round(Math.random() * 50)));
				if (!level.addThing(resource)) {
					i--;
				}		
			}				
		}	

	}

}
