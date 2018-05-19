package beepBoop;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import beepBoop.controller.MainController;
import beepBoop.model.Landscape;
import beepBoop.model.Level;
import beepBoop.model.Player;
import beepBoop.model.TileFactory;
import beepBoop.ui.MainFrame;

public class App {

	
	
	
	private MainFrame gui;
	private boolean exit;
	private MainController mainContr;

	public App() {
		TileFactory tf = TileFactory.getInstance();
		tf.loadTiles();
		Landscape landscape = new Landscape(new Dimension(21,21));
		landscape.placeRect(0, 0, 20, 20, tf.GRASS);
		landscape.placeRect(0, 0, 0, 20, tf.ROCK);
		landscape.placeRect(0, 0, 20, 0, tf.ROCK);
		landscape.placeRect(0, 20, 20, 20, tf.ROCK);
		landscape.placeRect(20, 0, 20, 20, tf.ROCK);
		landscape.placeRect(8, 8, 12, 12, tf.EARTH);
		
		Player player = new Player();
		player.setPosition(new Point(10,10));
		
		Level level = new Level(landscape,player);
		
		gui = new MainFrame();
		gui.initLevelUI(level);
		gui.setSize(1000, 500);	
		mainContr = new MainController(gui,level);

		//gui.pack();
		gui.setVisible(true); // 
		mainContr.mainAction();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		App app = new App();
		
		
	}

}
