package beepBoop;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import beepBoop.controller.MainController;
import beepBoop.controller.RobotTerminalController;
import beepBoop.model.Inventory;
import beepBoop.model.Landscape;
import beepBoop.model.Level;
import beepBoop.model.Player;
import beepBoop.model.Robot;
import beepBoop.model.RobotTerminal;
import beepBoop.model.TileFactory;
import beepBoop.model.ressource.Copper;
import beepBoop.model.ressource.Gold;
import beepBoop.model.ressource.Iron;
import beepBoop.model.ressource.Platinum;
import beepBoop.model.ressource.Silicon;
import beepBoop.ui.MainFrame;

public class App {
	
	private MainFrame gui;
	private boolean exit;
	private MainController mainContr;

	public App() {
		TileFactory tf = TileFactory.getInstance();
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

		Inventory inventory = new Inventory();
		//inventory.addRessource(new Gold(0));		
		gui = new MainFrame();
		gui.initLevelUI(level);
		gui.initInventoryUI(inventory);		
		mainContr = new MainController(gui,level);
		
		Copper copper = new Copper(200);
		copper.setPosition(new Point(3,3));
		level.addThing(copper);
		
		Gold gold = new Gold(200);
		gold.setPosition(new Point(3,6));
		level.addThing(gold);
		
		Iron iron = new Iron(200);
		iron.setPosition(new Point(3,9));
		level.addThing(iron);
		
		Platinum platinum = new Platinum(200);
		platinum.setPosition(new Point(3,12));
		level.addThing(platinum);
		
		Silicon silicon = new Silicon(200);
		silicon.setPosition(new Point(3,15));
		level.addThing(silicon);
		
		Robot robot = new Robot();
		robot.setPosition(new Point(10,12));
		List<String> program = new LinkedList<String>();
		program.add("U");
		program.add("L");
		program.add("D");
		program.add("R");
		robot.setMemory(program);
		level.addThing(robot);
		level.addRobot(robot);
		
		Robot robot2 = new Robot();
		robot2.setPosition(new Point(16,16));
		program = new LinkedList<String>();
		program.add("D");
		program.add("R");
		program.add("R");
		program.add("U");
		program.add("U");
		program.add("L");
		program.add("L");
		program.add("D");	
		robot2.setMemory(program);
		level.addThing(robot2);
		level.addRobot(robot2);
		
		RobotTerminal terminal = new RobotTerminal();
		terminal.setPosition(new Point(10, 7));
		level.addThing(terminal);

		gui.setSize(1000, 500);	
		
		//gui.pack();
		gui.setVisible(true); // 
		mainContr.mainAction();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		App app = new App();
		
		
	}

}
