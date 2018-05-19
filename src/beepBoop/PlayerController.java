package beepBoop;

public class PlayerController extends AbstractController {
	LevelUI levelUI;
	
	
	
	public PlayerController(LevelUI levelUI) {
		super();
		this.levelUI = levelUI;
	}



	public void leftAction(){
		levelUI.level.player.getPosition().x--;
		levelUI.repaint();
	}
	public void rightAction(){
		levelUI.level.player.getPosition().x++;
		levelUI.repaint();
	}
	public void upAction(){
		levelUI.level.player.getPosition().y--;
		levelUI.repaint();
	}
	public void downAction(){
		levelUI.level.player.getPosition().y++;
		levelUI.repaint();
	}
}
