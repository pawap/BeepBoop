package beepBoop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import beepBoop.model.Event;
import beepBoop.model.AbstractRobot;
import beepBoop.model.Level;
import beepBoop.model.Resource;
import beepBoop.model.Thing;
import beepBoop.ui.MainFrame;

/**
 * The BeepBoop MainController propagates input to all relevant controllers.
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class MainController extends AbstractController {
	
	private MainFrame gui;
	private boolean exit;
	private PlayerController playerController;
	private Level level;
	private RobotController robotController;
	private RobotTerminalController terminalController;
	private EventController eventController;
	
	
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
		this.terminalController = new RobotTerminalController(gui.getTerminalUI(), gui, this.level);
		this.playerController = new PlayerController(gui, terminalController);
		this.robotController = new RobotController(level);
		this.eventController = new EventController(level,gui);
	}

	public void mainAction() {
		initKeyBindings();
		eventController.initAction(level.getEventQueue());
        while(!exit) {
            for (AbstractRobot robot: level.getRobotQueue()) {
            	robotController.processAction(robot);
            }
            Event event;
            while ((event = level.getEventQueue().poll()) != null) {
            	eventController.processAction(event);
            }
            gui.getLevelUI().repaint();
            try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
	}
	
	/**
	 * Initialize key bindings.
	 */
	public void initKeyBindings(){
		Action leftAction = new AbstractAction(){

			private static final long serialVersionUID = 4969451003156101086L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerController.leftAction();
			}
			
		};
		Action rightAction = new AbstractAction(){

			private static final long serialVersionUID = -3168530161376412358L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerController.rightAction();
			}
			
		};
		Action upAction = new AbstractAction(){

			private static final long serialVersionUID = -5486971388403323250L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerController.upAction();
			}
		
		};
		Action downAction = new AbstractAction(){

			private static final long serialVersionUID = -581806568284033528L;

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
                
            }

            @Override
            public void mouseExited(MouseEvent arg0)
            {
                
            }

            @Override
            public void mousePressed(MouseEvent arg0)
            {
                gui.getLevelUI().requestFocus();
                
            }

            @Override
            public void mouseReleased(MouseEvent arg0)
            {
                
            }
		    
		});
	}

	public ActionListener getLoadListener() {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
					loadLevel();				
			}
			
		};
		return listener;
	}

	public ActionListener getSaveListener() {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveLevel();
				
			}
			
		};
		return listener;
	}

	public ActionListener getExitListener() {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				exit = true;
				gui.dispose();
				
			}
			
		};
		return listener;
	}
	
	private void saveLevel() {
		JFileChooser chooser = new JFileChooser();
		String fileExtension = ".bbs";
		String fileType = "BeepBoop Save File";
		chooser.setDialogTitle("Save your game");
		chooser.setFileFilter(new FileNameExtensionFilter(fileType, fileExtension));
		int result = chooser.showSaveDialog(gui);
		if(result == JFileChooser.APPROVE_OPTION) {
			String path = chooser.getSelectedFile().getPath();
			if (!path.endsWith(fileExtension)) {
				path += fileExtension;
			}
			try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
				out.writeObject(level);
			} catch (IOException e) {
		         e.printStackTrace();
		         gui.showMessage("File could not be written.");
		      }
		}
	}
	
	private void loadLevel() {
		JFileChooser chooser = new JFileChooser();
		String fileExtension = "bbs";
		String fileType = "BeepBoop Save File";
		chooser.setDialogTitle("Load a game");
		chooser.setFileFilter(new FileNameExtensionFilter(fileType, fileExtension));
		int result = chooser.showDialog(gui, "Load");
		if(result == JFileChooser.APPROVE_OPTION) {
			String path = chooser.getSelectedFile().getPath();
			try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
				
				Level loaded = (Level) in.readObject();
				
				//give each thing its tile
				for(Thing thing: loaded.getThings()) {
					thing.setTile(thing.getTileId());
				}		
				//give the player their tile
				loaded.getPlayer().setTile(loaded.getPlayer().getTileId());
				//give each inventory item its tile
				for (Resource resource : loaded.getInventory().getResources()) {
					resource.setTile(resource.getTileId());
				}
				//give each robot's cargo its tile
				for (AbstractRobot robot : loaded.getRobotQueue()) {
					Resource resource = robot.getCargo();
					if (resource != null) {
						resource.setTile(resource.getTileId());
					}
				}			
				this.level = loaded;
				this.gui.dispose();
				this.gui = new MainFrame();
				gui.initLevelUI(this.level);
				gui.initInventoryUI(level.getInventory());
				gui.initTerminalUI();
				this.terminalController = new RobotTerminalController(gui.getTerminalUI(), gui, this.level);
				if (this.level.getPlayer().hasTerminalAccess()) {
					this.terminalController.navigateTo("main");
				}
				this.playerController = new PlayerController(gui, terminalController);
				this.robotController = new RobotController(level);
				this.eventController = new EventController(level,gui);
				gui.initMenuBar(getLoadListener(),
				                getSaveListener(),
				                getExitListener());
				initKeyBindings();
				gui.setSize(MainFrame.DEFAULT_WIDTH, MainFrame.DEFAULT_HEIGHT);	
				gui.setVisible(true);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				gui.showMessage("File not found.");
			} catch (IOException e) {
				e.printStackTrace();
				gui.showMessage("File could not be read.");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				gui.showMessage("Class not found.");
			}
		}
	}
	
}
