package beepBoop;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

public class MainController extends AbstractController {
	
	MainFrame gui;
	boolean exit;
	private PlayerController playerController;
	Level level;
	
	public MainController(MainFrame gui, Level level) {
		super();
		this.gui = gui;
		this.exit = false;
		this.level = level;
		this.playerController = new PlayerController(gui.levelUI);
	}

	public void mainAction() {
		initKeyBindings();
	}
	
	public void initKeyBindings(){
		Action leftAction = new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerController.leftAction();
			}
			
		};
		Action rightAction = new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerController.rightAction();
			}
			
		};
		Action upAction = new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerController.upAction();
			}
		
		};
		Action downAction = new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerController.downAction();
			}
		
		};
		gui.levelUI.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),
                "left");
		gui.levelUI.getActionMap().put("left",
                 leftAction);
		gui.levelUI.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),
                "right");
		gui.levelUI.getActionMap().put("right",
                 rightAction);
		gui.levelUI.getInputMap().put(KeyStroke.getKeyStroke("UP"),
                "up");
		gui.levelUI.getActionMap().put("up",
                 upAction);
		gui.levelUI.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),
                "down");
		gui.levelUI.getActionMap().put("down",
                 downAction);
		gui.levelUI.requestFocus();
	}
	
}
