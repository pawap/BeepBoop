package beepBoop;

import java.awt.Dimension;

public class App {

	
	
	
	public App() {
		TileFactory tf = TileFactory.getInstance();
		tf.loadTiles();
		Landscape landscape = new Landscape(new Dimension(20,20));
		landscape.placeRect(0, 0, 19, 19, tf.GRASS);
		landscape.placeRect(0, 0, 0, 19, tf.ROCK);
		landscape.placeRect(0, 0, 19, 0, tf.ROCK);
		landscape.placeRect(0, 19, 19, 19, tf.ROCK);
		landscape.placeRect(19, 0, 19, 19, tf.ROCK);
		landscape.placeRect(8, 8, 12, 12, tf.EARTH);
		
		
		MainFrame gui = new MainFrame();
		gui.initLandscapeUI(landscape);
		gui.setSize(1000, 400);		 
		//gui.pack();
		gui.setVisible(true); // 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		App app = new App();
		
		
	}

}
