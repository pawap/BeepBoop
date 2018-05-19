package beepBoop.controller;

import beepBoop.ui.LevelUI;

public class PlayerController extends AbstractController {
	LevelUI levelUI;
	
	
	
	public PlayerController(LevelUI levelUI) {
		super();
		this.levelUI = levelUI;
	}



	public void leftAction(){
		levelUI.getLevel().getPlayer().getPosition().x--;
		levelUI.repaint();
	}
	public void rightAction(){
		levelUI.getLevel().getPlayer().getPosition().x++;
		levelUI.repaint();
	}
	public void upAction(){
		levelUI.getLevel().getPlayer().getPosition().y--;
		levelUI.repaint();
	}
	public void downAction(){
		levelUI.getLevel().getPlayer().getPosition().y++;
		levelUI.repaint();
	}
}
