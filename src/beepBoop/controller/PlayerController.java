package beepBoop.controller;

import java.awt.Point;

import beepBoop.model.Level;
import beepBoop.model.Resource;
import beepBoop.service.TileFactory;
import beepBoop.ui.InventoryUI;
import beepBoop.ui.LevelUI;
import beepBoop.ui.MainFrame;

/**
 * PTP 2018
 * BeepBoop - the RobotGame
 * 
 * Controller for the BeepBoop player.
 * Reacts on input it receives via the MainController.
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class PlayerController {
	private LevelUI levelUI;
	private InventoryUI inventoryUI;
	private RobotTerminalController terminalContr;
	
	/**
	 * Constructor
	 * @param mf the gui
	 * @param robotTerminalController RobotTerminalController
	 */
	public PlayerController(MainFrame mf,RobotTerminalController robotTerminalController) {
		super();
		this.levelUI = mf.getLevelUI();
		this.inventoryUI = mf.getInventoryUI();
		terminalContr = robotTerminalController;	
	}

	/*
	 * Moves the player avatar to the specified new position if it is free or interacts with
	 * the thing at the specified position
	 * @param x x coordinate of the desired position
	 * @param y y coordinate of the desired position
	 * @return true if the player avatar moved
	 */
	private boolean moveTo(int x, int y) {
		Level level = levelUI.getLevel();
		if (!level.isPositionFree(x,y)) {
			//if player tries to step on a resource, take a little amount instead.
			if (level.isRessource(x,y)) {
				Resource resource = (Resource) level.getThing(x,y);
				Resource transfer = new Resource(resource.takeAmount(10),TileFactory.getTileIdForResource(resource.getName()), resource.getName());
				if (resource.getAmount() == 0) {
					level.removeThing(resource);
				}
				inventoryUI.getInventory().addResource(transfer);
				levelUI.repaint();				
			}
			//if player tries to step on a robotTerminal, interact instead.
			if (level.isRobotTerminal(x,y)) { 
				terminalContr.openTerminal();
				this.levelUI.getLevel().getPlayer().setTerminalAccess(true);
			}
			return false;
		}	
		return true;
	}

	/**
	 * Moves the player avatar to the left if possible or interacts with the Thing to
	 * the left of the player if there is a Thing that can be interacted with.
	 */
	public void leftAction(){
		Level level = levelUI.getLevel();
		Point position = level.getPlayer().getPosition();
		int x = position.x - 1;
		int y = position.y;
		
		if (moveTo(x,y)) {
			position.x--;
			levelUI.repaint();			
		}
	}
	
	/**
	 * Moves the player avatar to the right if possible or interacts with the Thing to
	 * the right of the player if there is a Thing that can be interacted with.
	 */
	public void rightAction(){
		Level level = levelUI.getLevel();
		Point position = level.getPlayer().getPosition();
		int x = position.x + 1;
		int y = position.y;	
		
		if (moveTo(x,y)) {
			position.x++;
			levelUI.repaint();
		}
	}
	
	/**
	 * Moves the player avatar up if possible or interacts with the Thing above
	 * the player if there is a Thing that can be interacted with.
	 */
	public void upAction(){
		Level level = levelUI.getLevel();
		Point position = level.getPlayer().getPosition();
		int x = position.x;
		int y = position.y - 1;		
		
		if (moveTo(x,y)) {
			position.y--;
			levelUI.repaint();
		}
	}
	
	/**
	 * Moves the player avatar down if possible or interacts with the Thing below
	 * the player if there is a Thing that can be interacted with.
	 */
	public void downAction(){
		Level level = levelUI.getLevel();
		Point position = level.getPlayer().getPosition();
		int x = position.x;
		int y = position.y + 1;		
		
		if (moveTo(x,y)) {
			position.y++;
			levelUI.repaint();
		}
	}
}
