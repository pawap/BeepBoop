package beepBoop;

import java.awt.Dimension;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import beepBoop.controller.MainController;
import beepBoop.model.Inventory;
import beepBoop.model.Landscape;
import beepBoop.model.Level;
import beepBoop.model.MsgEvent;
import beepBoop.model.Player;
import beepBoop.model.Resource;
import beepBoop.model.BasicRobot;
import beepBoop.model.RobotTerminal;
import beepBoop.service.TileFactory;
import beepBoop.ui.MainFrame;

/**
 * App offers the main entry point to BeepBoop. 
 * Creates a Demo level that lets a user experience  
 * all available BeepBoop features.
 * @author t
 *
 */
public class App {
	private MainFrame gui;
	private MainController mainContr;

	/**
	 * Constructor
	 */
	public App() {
		Level level = initializeDemoLevel(false);
		//create gui
		gui = new MainFrame();
		gui.initLevelUI(level);
		gui.initInventoryUI(level.getInventory());
		gui.initTerminalUI();

		gui.setSize(MainFrame.DEFAULT_WIDTH, MainFrame.DEFAULT_HEIGHT);	
		gui.setMaximumSize(new Dimension(MainFrame.DEFAULT_WIDTH, MainFrame.DEFAULT_HEIGHT));
		gui.setVisible(true);
		
		//create main controller
		mainContr = new MainController(gui,level);
		mainContr.mainAction();
		
		//add menu bar to gui
		gui.initMenuBar(mainContr.getLoadListener(),
		        mainContr.getSaveListener(),
		        mainContr.getExitListener());
	}

	/**
	 * Main entry point
	 * @param args
	 */
	public static void main(String[] args) {
		new App();		
	}
	
	/*
	 * Initializes a Demo Level showcasing the current BeepBoop features.
	 * @param showWelcomeMessages allows disabling of the welcome messages for an enhanced testing experience
	 * (We do not recommend to disable WelcomeMessages though, if feeling welcomed is an essential objective)
	 */
	private Level initializeDemoLevel(boolean showWelcomeMessages) {
		//create Landscape
		Landscape landscape = new Landscape(new Dimension(50,50));
		landscape.placeRect(0, 0, 49, 49, TileFactory.GRASS_OFFSET);
		landscape.placeRect(0, 0, 0, 49, TileFactory.ROCK_OFFSET);
		landscape.placeRect(0, 0, 49, 0, TileFactory.ROCK_OFFSET);
		landscape.placeRect(0, 49, 49, 49, TileFactory.ROCK_OFFSET);
		landscape.placeRect(49, 0, 49, 49, TileFactory.ROCK_OFFSET);
		landscape.placeRect(8, 8, 12, 12, TileFactory.EARTH_OFFSET);
		
		Player player = new Player();
		player.setPosition(new Point(10,10));
		
		Inventory inventory = new Inventory();
		
		Level level = new Level(landscape,player,inventory);
		
		//add Terminal
		RobotTerminal terminal = new RobotTerminal();
		terminal.setPosition(new Point(10, 7));
		level.addThing(terminal);
		
		//add Resources
		Resource copper = new Resource(200, TileFactory.COPPER, "copper");
		copper.setPosition(new Point(3,3));
		level.addThing(copper);		
		Resource gold = new Resource(200, TileFactory.GOLD, "gold");
		gold.setPosition(new Point(3,6));
		level.addThing(gold);		
		Resource iron = new Resource(200, TileFactory.IRON, "iron");
		iron.setPosition(new Point(3,9));
		level.addThing(iron);		
		Resource platinum = new Resource(200, TileFactory.PLATINUM, "platinum");
		platinum.setPosition(new Point(3,12));
		level.addThing(platinum);		
		Resource silicon = new Resource(200, TileFactory.SILICON, "silicon");
		silicon.setPosition(new Point(3,15));
		level.addThing(silicon);
		
		

		//add a robot
		BasicRobot robot = new BasicRobot();
		robot.setPosition(new Point(10,12));
		List<String> program = new LinkedList<String>();
		program.add("IF FREE L");
		program.add("L");
		program.add("GOTO 0");
		program.add("END");
		program.add("LD L 50");
        program.add("U");
        program.add("U");
        program.add("U");
        program.add("U");
        program.add("U");        
        program.add("IF FREE R");
        program.add("DP L 1"); 
        program.add("R");
        program.add("GOTO 10");
        program.add("END");
        program.add("DP R 50");      
		robot.setMemory(program);
		level.addRobot(robot);
		
		//add another robot
		BasicRobot robot2 = new BasicRobot();
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
		robot2.setActivityCounter(8);
		level.addRobot(robot2);
		//randomly place some more Resources
		for (int i = 0; i < 20; i++) {			
			for (String tileName: new String[]{"silicon","platinum","iron","gold","copper"}) {
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
        //possibly set up welcome messages
		if (showWelcomeMessages) {
			MsgEvent msg = new MsgEvent("This is rather confusing, isn't it?");
			msg.setTimeout(15);
			level.addEvent(msg);
			msg = new MsgEvent("Welcome!");
			msg.setTimeout(3);
			level.addEvent(msg);
			msg = new MsgEvent("Get to work! Those robots are going beserk!");
			msg.setTimeout(8);		
			level.addEvent(msg);
		}
		
		return level;

	}

}
