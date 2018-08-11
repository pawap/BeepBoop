package beepBoop.model;

import java.awt.Point;

import beepBoop.service.TileFactory;

/**
 * This event drops a certain number of each resource with random amounts (less than 100) in random places
 * within the rectangle bounded by the two given Points 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class ResourceDropEvent extends Event {

	private int dropsPerType;
	private Point upperLeft;
	private Point lowerRight;
	
	/**
	 * Constructor
	 * 
	 * @param dropsPerType number of resources to drop per type
	 */
	public ResourceDropEvent(int dropsPerType, Point upperLeft, Point lowerRight) {
		super();
		this.dropsPerType = dropsPerType;
		this.upperLeft = upperLeft;
		this.lowerRight = lowerRight;
	}



	@Override
	public void performChanges(Level level) {
		int  width = lowerRight.x - upperLeft.x;
		int  height = lowerRight.y - upperLeft.y;
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
				//Find a random Point within the given rectangle
				int x = (int) Math.round(Math.random() * width) + upperLeft.x;
				int y = (int) Math.round(Math.random() * height) + upperLeft.y;
				resource.setPosition(new Point(x,y));
				//decrease counter if resource could not be placed, i.e.: try again
				if (!level.addThing(resource)) {
					i--;
				}		
			}				
		}	

	}

}
