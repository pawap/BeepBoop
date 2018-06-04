package beepBoop.test;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import beepBoop.controller.PlayerController;
import beepBoop.model.Inventory;
import beepBoop.model.Landscape;
import beepBoop.model.Level;
import beepBoop.model.Player;
import beepBoop.model.RobotTerminal;
import beepBoop.model.Thing;
import beepBoop.model.TileFactory;
import beepBoop.model.resource.Gold;
import beepBoop.model.resource.Iron;
import beepBoop.model.resource.Resource;
import beepBoop.ui.InventoryUI;
import beepBoop.ui.LevelUI;
import beepBoop.ui.MainFrame;
import beepBoop.ui.RobotTerminalUI;

public class IntegrationTest {
	private MainFrame gui;
	private Player player;
	private Landscape landscape;
	private PlayerController plrCtrl;
	private LevelUI lvlUI;
	private Inventory inv;
	private InventoryUI invUI;
	private Level lvl;
	private TileFactory tiFa;
	private RobotTerminal terminal;
	private RobotTerminalUI terminalUI;
	
	@Before
	public void setup() {
		tiFa = TileFactory.getInstance();
		player = new Player();
		landscape = new Landscape(new Dimension(4,4));
		landscape.placeRect(0, 0, 3, 3, TileFactory.EARTH_OFFSET);
		lvl = new Level(landscape, player);
		lvlUI = new LevelUI(lvl);
		inv = new Inventory();
		invUI = new InventoryUI(inv);
		gui = new MainFrame();
		gui.initInventoryUI(inv);
		gui.initLevelUI(lvl);
		gui.initTerminalUI();
		plrCtrl = new PlayerController(gui);		
		terminal = new RobotTerminal();
		terminalUI = gui.getTerminalUI();
	}
	
	/**
	 * Tests the Landscape class.
	 * Placing tiles, getting tiles and manipulating the size.
	 */
	@Test
	public void landscapeTest() {
		assertTrue("Landscape's size differs from the size declared at its initialization",
				landscape.getSize().equals(new Dimension(4,4)));		
		landscape.setSize(new Dimension(7,8));
		assertTrue("Landscape's size differs from the one set via setSize(Dimension).",
				landscape.getSize().equals(new Dimension(7,8)));
		landscape.placeRect(1, 1, 5, 5, TileFactory.GRASS_OFFSET);
		landscape.place(2, 2, TileFactory.ROCK_1);
		assertTrue("Wrong tile at 0,0. one of the earth types.",
				landscape.getTile(0,0) == tiFa.get(TileFactory.EARTH_0) ||
				landscape.getTile(0,0) == tiFa.get(TileFactory.EARTH_1) ||
				landscape.getTile(0,0) == tiFa.get(TileFactory.EARTH_2));
		assertTrue("Wrong tile at 1,1. Should be one of the grass tiles.",
				landscape.getTile(1,1) == tiFa.get(TileFactory.GRASS_0) ||
				landscape.getTile(1,1) == tiFa.get(TileFactory.GRASS_1) ||
				landscape.getTile(1,1) == tiFa.get(TileFactory.GRASS_2));
		assertTrue("Wrong tile at 2,2. Should be EARTH_1",
				landscape.getTile(2,2) == tiFa.get(TileFactory.ROCK_1));
		landscape.setSize(new Dimension(3,3));
		assertTrue("Wrong tile at 2,2 after shrinking the landscape. Should be ROCK_1",
				landscape.getTile(2,2) == tiFa.get(TileFactory.ROCK_1));		
	}
	
	@Test
	public void levelTest() {
		setUpLevel();
		assertTrue("3,2 should be occupied by Gold, but there is non.",
				lvl.isRessource(3, 2));
		assertFalse("3,2 should be occupied by Gold but was wrongly declared free",
				lvl.isPositionFree(3, 2));
		Thing thing = new Iron(0);
		thing.setPosition(new Point(3,2));
		assertFalse("3,2 should be occupied by Gold but a new thing was just placed there.",
				lvl.addThing(thing));
	}
	
	@Test
	public void playerActionTest() {
		setUpLevel();
		plrCtrl.rightAction();
		assertTrue("Player did not move right when they were supposed to",
				player.getPosition().equals(new Point(2,2)));
		plrCtrl.rightAction();
		assertFalse("Player moved into resource.",
				player.getPosition().equals(new Point(3,2)));
		
		plrCtrl.upAction();
		assertTrue("Player did not move up when they were supposed to",
				player.getPosition().equals(new Point(2,1)));
		plrCtrl.upAction();
		assertTrue("Player moved into resource.",
				player.getPosition().equals(new Point(2,1)));
		
		plrCtrl.leftAction();
		assertTrue("Player did not move left when they were supposed to",
				player.getPosition().equals(new Point(1,1)));
		plrCtrl.leftAction();
		assertTrue("Player moved into resource.",
				player.getPosition().equals(new Point(1,1)));

		
		plrCtrl.downAction();
		assertTrue("Player did not move down when they were supposed to",
				player.getPosition().equals(new Point(1,2)));
		plrCtrl.downAction();
		assertTrue("Player moved into resource.",
				player.getPosition().equals(new Point(1,2)));
		
		plrCtrl.leftAction();
		assertTrue("Terminal is inactive. Should be active",
				terminalUI.isActive());
	}
	
	//helper methods
	
	/*Set up the following level(I:Iron, G:Gold, T:Terminal, P:Player):
	 *   0|1|2|3
	 *  ---------
	 * 0| | |I| |
	 * 1|G| | | |
	 * 2|T|P| |G|
	 * 3| |G| | |
	 */
	private void setUpLevel() {
		Resource res0 = new Iron(100);
		res0.setPosition(new Point(2,0));
		Resource res1 = new Iron(100);
		res1.setPosition(new Point(0,1));
		Resource res2 = new Gold(100);
		res2.setPosition(new Point(3,2));
		Resource res3 = new Gold(100);
		res3.setPosition(new Point(1,3));
		player.setPosition(new Point(1,2));
		terminal.setPosition(new Point(0,2));
		
		lvl.addThing(res0);
		lvl.addThing(res1);
		lvl.addThing(res2);
		lvl.addThing(res3);
		lvl.addThing(terminal);
	}

}