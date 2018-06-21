package beepBoop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import beepBoop.model.Level;
import beepBoop.model.Robot;
import beepBoop.ui.MainFrame;

/**
 * The BeepBoop MainController propagates input to all relevant controllers.
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class MainController extends AbstractController {
	
	MainFrame gui;
	boolean exit;
	private PlayerController playerController;
	Level level;
	private RobotController robotController;
	private RobotTerminalController terminalController;
	
	
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
		this.terminalController = new RobotTerminalController(gui.getTerminalUI(), this.level.getRobotQueue());
		this.playerController = new PlayerController(gui, terminalController);
		this.robotController = new RobotController(level);
		
	}

	public void mainAction() {
		initKeyBindings();
        while(!exit) {
        	//System.out.print("|");
            for (Robot robot: level.getRobotQueue()) {
            	robotController.moveAction(robot);
            	//System.out.print(".");
            }
            gui.getLevelUI().repaint();
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
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
		
		gui.getLevelUI().addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0)
            {
                gui.getLevelUI().requestFocus();
                
            }

            @Override
            public void mouseEntered(MouseEvent arg0)
            {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseExited(MouseEvent arg0)
            {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mousePressed(MouseEvent arg0)
            {
                gui.getLevelUI().requestFocus();
                
            }

            @Override
            public void mouseReleased(MouseEvent arg0)
            {
                // TODO Auto-generated method stub
                
            }
		    
		});
	}
	
}
