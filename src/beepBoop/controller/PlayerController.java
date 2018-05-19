package beepBoop.controller;

import java.awt.Image;
import java.awt.Point;

import beepBoop.model.Actor;
import beepBoop.model.Inventory;
import beepBoop.model.Level;
import beepBoop.model.Ressource;
import beepBoop.ui.InventoryUI;
import beepBoop.ui.LevelUI;

public class PlayerController extends AbstractController {
	LevelUI levelUI;
	InventoryUI inventoryUI;
	private RobotTerminalController terminalContr;
	
	
	public PlayerController(LevelUI levelUI, InventoryUI inventoryUI) {
		super();
		this.levelUI = levelUI;
		this.inventoryUI = inventoryUI;
		terminalContr = new RobotTerminalController();	
	}

	private boolean moveTo(int amount, int x, int y) {
		Level level = levelUI.getLevel();
		if (!level.isPositionFree(x,y)) {
			if (level.isRessource(x,y)) {
				Ressource ressource = (Ressource) level.getThing(x,y);
				Ressource transfer = new Ressource(ressource.takeAmount(10)) {

					@Override
					public String getName() {
;						return ressource.getName();
					}

					@Override
					public Image getImage() {
						return null;
					}
					
				};
				inventoryUI.getInventory().addRessource(transfer);
				levelUI.repaint();
				inventoryUI.repaint();
			}
			if (level.isRobotTerminal(x,y)) {
				terminalContr.openTerminal();
			}
			return false;
		}
		return true;
	}

	public void leftAction(){
		Level level = levelUI.getLevel();
		Point position = level.getPlayer().getPosition();
		int x = position.x - 1;
		int y = position.y;
		if (moveTo(10,x,y)) {
			position.x--;
			levelUI.repaint();			
		}
	}
	public void rightAction(){
		Level level = levelUI.getLevel();
		Point position = level.getPlayer().getPosition();
		int x = position.x + 1;
		int y = position.y;	
		if (moveTo(10,x,y)) {
			position.x++;
			levelUI.repaint();
		}
	}
	public void upAction(){
		Level level = levelUI.getLevel();
		Point position = level.getPlayer().getPosition();
		int x = position.x;
		int y = position.y - 1;			
		if (moveTo(10,x,y)) {
			position.y--;
			levelUI.repaint();
		}
	}
	public void downAction(){
		Level level = levelUI.getLevel();
		Point position = level.getPlayer().getPosition();
		int x = position.x;
		int y = position.y + 1;			
		if (moveTo(10,x,y)) {
			position.y++;
			levelUI.repaint();
		}
	}
}
