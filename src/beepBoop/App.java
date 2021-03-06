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
import beepBoop.model.ResourceDropEvent;
import beepBoop.model.BasicRobot;
import beepBoop.model.Event;
import beepBoop.model.RobotTerminal;
import beepBoop.service.TileFactory;
import beepBoop.ui.MainFrame;

/**
 * PTP 2018 BeepBoop - the RobotGame
 * 
 * App offers the main entry point to BeepBoop. Creates a Demo level that lets a
 * user experience all available BeepBoop features.
 * 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class App {
	private MainFrame gui;
	private MainController mainContr;

	/**
	 * Constructor initialises a demo-level, sets up the gui & the main
	 * controller and finally starts the game loop
	 */
	public App() {
		Level level = initializeDemoLevel(true);
		initGui(level);

		// create main controller
		mainContr = new MainController(gui, level);

		// add menu bar to gui
		gui.initMenuBar(mainContr.getLoadListener(), 
				        mainContr.getSaveListener(), 
				        mainContr.getExitListener());

		gui.setVisible(true);

		// start the game
		mainContr.mainAction();
	}

	/*
	 * Initializes the gui with the given level
	 */
	private void initGui(Level level) {
		// create gui
		gui = new MainFrame();
		gui.initLevelUI(level);
		gui.initInventoryUI(level.getInventory());
		gui.initTerminalUI();

		gui.setSize(MainFrame.DEFAULT_WIDTH, MainFrame.DEFAULT_HEIGHT);
		gui.setMaximumSize(new Dimension(MainFrame.DEFAULT_WIDTH, MainFrame.DEFAULT_HEIGHT));

	}

	/*
	 * Initializes a Demo Level showcasing the current BeepBoop features.
	 * Necessary as there is no LevelEditor as of version 1.0.0. Beware!
	 * Excessive use of literals.
	 * 
	 * @param showWelcomeMessages allows disabling of the welcome messages for
	 * an enhanced testing experience (We do not recommend to disable
	 * WelcomeMessages though, if feeling welcomed is an essential objective)
	 */
	private Level initializeDemoLevel(boolean showWelcomeMessages) {
		// create Landscape
		Landscape landscape = new Landscape(new Dimension(50, 50));
		landscape.placeRect(0, 0, 49, 49, TileFactory.GRASS_OFFSET);
		landscape.placeRect(0, 0, 0, 49, TileFactory.ROCK_OFFSET);
		landscape.placeRect(0, 0, 49, 0, TileFactory.ROCK_OFFSET);
		landscape.placeRect(0, 49, 49, 49, TileFactory.ROCK_OFFSET);
		landscape.placeRect(49, 0, 49, 49, TileFactory.ROCK_OFFSET);
		landscape.placeRect(8, 8, 12, 12, TileFactory.EARTH_OFFSET);
		landscape.placeRect(5, 15, 15, 15, TileFactory.ROCK_OFFSET);
		landscape.placeRect(11, 1, 11, 3, TileFactory.ROCK_OFFSET);

		Player player = new Player();
		player.setPosition(new Point(10, 10));

		Inventory inventory = new Inventory();

		Level level = new Level(landscape, player, inventory);

		// add Terminal
		RobotTerminal terminal = new RobotTerminal();
		terminal.setPosition(new Point(10, 7));
		level.addThing(terminal);

		// add Resources
		Resource copper = new Resource(200, TileFactory.COPPER, "copper");
		copper.setPosition(new Point(3, 3));
		level.addThing(copper);
		Resource gold = new Resource(200, TileFactory.GOLD, "gold");
		gold.setPosition(new Point(3, 6));
		level.addThing(gold);
		Resource iron = new Resource(200, TileFactory.IRON, "iron");
		iron.setPosition(new Point(3, 9));
		level.addThing(iron);
		Resource platinum = new Resource(200, TileFactory.PLATINUM, "platinum");
		platinum.setPosition(new Point(3, 12));
		level.addThing(platinum);
		Resource silicon = new Resource(200, TileFactory.SILICON, "silicon");
		silicon.setPosition(new Point(3, 15));
		level.addThing(silicon);

		// add a robot
		BasicRobot robot = new BasicRobot();
		robot.setPosition(new Point(15, 5));
		List<String> program = new LinkedList<String>();
		program.add("IF FREE R");
		program.add("R");
		program.add("GOTO 0");
		program.add("END");
		program.add("LD R 50");
		program.add("U");
		program.add("U");
		program.add("U");
		program.add("U");
		program.add("U");
		program.add("IF FREE L");
		program.add("DP R 1");
		program.add("L");
		program.add("GOTO 10");
		program.add("END");
		program.add("DP L 50");
		robot.setMemory(program);
		level.addRobot(robot);

		// add another robot
		BasicRobot robot2 = new BasicRobot();
		robot2.setPosition(new Point(16, 16));
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
		robot2.setActivityCounter(5);
		level.addRobot(robot2);

		BasicRobot robot3 = new BasicRobot();
		robot3.setPosition(new Point(6, 5));
		program = new LinkedList<String>();

		program.add("IF FREE D");
		program.add("D");
		program.add("GOTO 0");
		program.add("END");
		program.add("IF RESOURCE D");
		program.add("LD D 50");
		program.add("GOTO 0");
		program.add("END");
		program.add("DP D 100");
		program.add("IF FREE R");
		program.add("R");
		program.add("GOTO 17");
		program.add("END");
		program.add("IF RESOURCE R");
		program.add("LD R 50");
		program.add("GOTO 9");
		program.add("END");
		program.add("IF FREE U");
		program.add("U");
		program.add("GOTO 17");
		program.add("END");
		program.add("IF RESOURCE U");
		program.add("LD U 50");
		program.add("GOTO 17");
		program.add("END");
		program.add("DP U 100");
		program.add("IF FREE R");
		program.add("R");
		program.add("GOTO 34");
		program.add("END");
		program.add("IF RESOURCE R");
		program.add("LD R 50");
		program.add("GOTO 26");
		program.add("END");
		program.add("GOTO 0");
		robot3.setMemory(program);
		robot3.setActivityCounter(10);
		level.addRobot(robot3);

		// randomly place some more Resources
		for (String tileName : new String[] { "silicon", "platinum", "iron", "gold", "copper" }) {
			for (int i = 0; i < 20; i++) {
				int tileId = 0;
				switch (tileName) {
				case "silicon":
					tileId = TileFactory.SILICON;
					break;
				case "platinum":
					tileId = TileFactory.PLATINUM;
					break;
				case "iron":
					tileId = TileFactory.IRON;
					break;
				case "gold":
					tileId = TileFactory.GOLD;
					break;
				case "copper":
					tileId = TileFactory.COPPER;
					break;
				}
				Resource resource = new Resource((int) Math.round(Math.random() * 100) + 1, tileId, tileName);
				resource.setPosition(new Point((int) Math.round(Math.random() * 40) + 11,
						(int) Math.round(Math.random() * 40) + 10));
				if (!level.addThing(resource)) {
					i--;
				}
			}
		}
		// place some gold left of terminal for demo purposes;
		for (int i = 0; i < 30; i++) {
			Resource resource = new Resource((int) Math.round(Math.random() * 100) + 1, TileFactory.GOLD, "gold");
			resource.setPosition(new Point((int) Math.round(Math.random() * 10), (int) Math.round(Math.random() * 20)));
			if (!level.addThing(resource)) {
				i--;
			}
		}
		// possibly set up welcome messages
		if (showWelcomeMessages) {
			Event msg = new MsgEvent("This is rather confusing, isn't it?");
			msg.setTimeout(15);
			level.addEvent(msg);
			msg = new MsgEvent("Welcome!");
			msg.setTimeout(3);
			level.addEvent(msg);
			msg = new MsgEvent("Get to work! Those robots are going beserk!");
			msg.setTimeout(8);
			level.addEvent(msg);
		}

		// add a bunch of resourceDropEvents on the right side of the terminal;
		Event resourceDropEvent;
		for (int i = 0; i < 20; i++) {
			resourceDropEvent = new ResourceDropEvent(i + 1, new Point(11, 1), new Point(49, 49));
			resourceDropEvent.setTimeout(i * 8);
			level.addEvent(resourceDropEvent);
		}
		Event msg = new MsgEvent(
				"Did you realize that resources seem to spawn randomly to the right of the robot terminal?");
		msg.setTimeout(20);
		level.addEvent(msg);
		msg = new MsgEvent("Oh... the random drops seem to have stopped. So sad. Looser!");
		msg.setTimeout(200);
		level.addEvent(msg);
		return level;
	}

	/**
	 * Main entry point
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new App();
	}

}
