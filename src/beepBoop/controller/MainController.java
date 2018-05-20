package beepBoop.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import beepBoop.model.Level;
import beepBoop.ui.MainFrame;

/**
 * The BeepBoop MainController propagates input to all reevant controllers.
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class MainController extends AbstractController {
	
	MainFrame gui;
	boolean exit;
	private PlayerController playerController;
	Level level;
	
	/**
	 * Constructor
	 * @param gui the Mainframe
	 * @param level the current level
	 */
	public MainController(MainFrame gui, Level level) {
		super();
		this.gui = gui;
		this.exit = false;
		this.level = level;
		this.playerController = new PlayerController(gui.getLevelUI(),gui.getInventoryUI());
	}

	public void mainAction() {
		initKeyBindings();
	}
	
	/**
	 * Initialize key bindings.
	 */
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
		gui.getLevelUI().getInputMap().put(KeyStroke.getKeyStroke("LEFT"),
                "left");
		gui.getLevelUI().getActionMap().put("left",
                 leftAction);
		gui.getLevelUI().getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),
                "right");
		gui.getLevelUI().getActionMap().put("right",
                 rightAction);
		gui.getLevelUI().getInputMap().put(KeyStroke.getKeyStroke("UP"),
                "up");
		gui.getLevelUI().getActionMap().put("up",
                 upAction);
		gui.getLevelUI().getInputMap().put(KeyStroke.getKeyStroke("DOWN"),
                "down");
		gui.getLevelUI().getActionMap().put("down",
                 downAction);
		gui.getLevelUI().requestFocus();
	}
	
}
