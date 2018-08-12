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
 * PTP 2018 BeepBoop - the RobotGame
 * 
 * The BeepBoop MainController propagates input to all relevant controllers.
 * Takes care of loading and saving a level.
 * 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class MainController {

	private MainFrame gui;
	private PlayerController playerController;
	private RobotController robotController;
	private RobotTerminalController terminalController;
	private EventController eventController;
	private Level level;
	private boolean exit;
	private boolean paused;

	/**
	 * Constructor
	 * 
	 * @param gui
	 *            the Mainframe
	 * @param level
	 *            the current level
	 */
	public MainController(MainFrame gui, Level level) {
		super();
		this.gui = gui;
		this.terminalController = new RobotTerminalController(gui.getTerminalUI(), gui, level);
		this.playerController = new PlayerController(gui, terminalController);
		this.eventController = new EventController(level, gui);
		this.robotController = new RobotController(level, eventController);

		this.level = level;
		this.exit = false;
		this.paused = false;
	}

	/**
	 * Initializes necessary keybindingd and controllers. Starts the game loop
	 * and dispatches Actions to other controllers.
	 */
	public void mainAction() {
		initKeyBindings();
		eventController.initAction(level.getEventQueue());
		while (!exit) {
			for (AbstractRobot robot : level.getRobotQueue()) {
				robotController.processAction(robot);
			}
			Event event;
			while ((event = level.getEventQueue().poll()) != null) {
				eventController.processAction(event);
			}
			gui.getLevelUI().repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while (paused) {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Initialize key bindings.
	 */
	public void initKeyBindings() {
		Action leftAction = new AbstractAction() {

			private static final long serialVersionUID = 4969451003156101086L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerController.leftAction();
			}

		};
		Action rightAction = new AbstractAction() {

			private static final long serialVersionUID = -3168530161376412358L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerController.rightAction();
			}

		};
		Action upAction = new AbstractAction() {

			private static final long serialVersionUID = -5486971388403323250L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerController.upAction();
			}

		};
		Action downAction = new AbstractAction() {

			private static final long serialVersionUID = -581806568284033528L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerController.downAction();
			}

		};
		gui.getLevelUI().getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "left");
		gui.getLevelUI().getActionMap().put("left", leftAction);
		gui.getLevelUI().getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "right");
		gui.getLevelUI().getActionMap().put("right", rightAction);
		gui.getLevelUI().getInputMap().put(KeyStroke.getKeyStroke("UP"), "up");
		gui.getLevelUI().getActionMap().put("up", upAction);
		gui.getLevelUI().getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "down");
		gui.getLevelUI().getActionMap().put("down", downAction);
		gui.getLevelUI().requestFocus();

		gui.getLevelUI().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				gui.getLevelUI().requestFocus();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				gui.getLevelUI().requestFocus();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

		});
	}

	/**
	 * @return an ActionListener that allows the loading of a saved level
	 */
	public ActionListener getLoadListener() {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadLevel();
			}

		};
		return listener;
	}

	/**
	 * @return an ActionListener that allows the saving of a level
	 */
	public ActionListener getSaveListener() {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveLevel();
			}

		};
		return listener;
	}

	/**
	 * @return an ActionListener that closes the App
	 */
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

	// Method which saves the current Level to disk via a JFileChooser dialog
	private void saveLevel() {
		paused = true;
		JFileChooser chooser = new JFileChooser();
		String fileExtension = "bbs";
		String fileType = "BeepBoop Save File";
		chooser.setDialogTitle("Save your game");
		chooser.setFileFilter(new FileNameExtensionFilter(fileType, fileExtension));
		int result = chooser.showSaveDialog(gui);
		if (result == JFileChooser.APPROVE_OPTION) {
			String path = chooser.getSelectedFile().getPath();
			if (!path.endsWith(fileExtension)) {
				path += "." + fileExtension;
			}
			try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
				out.writeObject(level);
			} catch (IOException e) {
				e.printStackTrace();
				gui.showMessage("File could not be written.");
			}
		}
		paused = false;
		gui.getLevelUI().requestFocus();
	}

	// Method which loads a Level from disk via a JFileChooser dialog
	private void loadLevel() {
		paused = true;
		JFileChooser chooser = new JFileChooser();
		String fileExtension = "bbs";
		String fileType = "BeepBoop Save File";
		chooser.setDialogTitle("Load a game");
		chooser.setFileFilter(new FileNameExtensionFilter(fileType, fileExtension));
		int result = chooser.showDialog(gui, "Load");
		if (result == JFileChooser.APPROVE_OPTION) {
			String path = chooser.getSelectedFile().getPath();
			Level loaded;
			try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {

				loaded = loadLevelFromStream(in);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				gui.showMessage("File not found.");
				paused = false;
				return;
			} catch (IOException e) {
				e.printStackTrace();
				gui.showMessage("File could not be read.");
				paused = false;
				return;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				gui.showMessage("Class not found.");
				paused = false;
				return;
			}

			this.level = loaded;
			// initialise new gui to avoid side-effects
			initNewGui();
			// initialise new controllers with the loaded level
			initNewSubcontrollers();

			// choose the correct TerminalUI to be shown
			if (this.level.getPlayer().hasTerminalAccess()) {
				this.terminalController.navigateTo("main");
			}
		}
		paused = false;
		gui.getLevelUI().requestFocus();
	}

	// initialises a fresh new gui
	private void initNewGui() {
		this.gui.dispose();
		this.gui = new MainFrame();
		gui.initLevelUI(this.level);
		gui.initInventoryUI(level.getInventory());
		gui.initTerminalUI();

		// finish gui setup
		gui.initMenuBar(getLoadListener(), getSaveListener(), getExitListener());
		initKeyBindings();
		gui.setSize(MainFrame.DEFAULT_WIDTH, MainFrame.DEFAULT_HEIGHT);
		gui.setVisible(true);
	}

	// creates new subcontrollers and initialises them with the loaded level
	private void initNewSubcontrollers() {
		this.terminalController = new RobotTerminalController(gui.getTerminalUI(), gui, this.level);
		this.playerController = new PlayerController(gui, terminalController);
		this.eventController = new EventController(level, gui);
		this.robotController = new RobotController(level, eventController);

	}

	// returns a level created from the given ObjectInputStream
	private Level loadLevelFromStream(ObjectInputStream in)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		Level loaded = (Level) in.readObject();
		// give each thing its tile
		for (Thing thing : loaded.getThings()) {
			thing.setTile(thing.getTileId());
		}
		// give the player their tile
		loaded.getPlayer().setTile(loaded.getPlayer().getTileId());
		// give each inventory item its tile
		for (Resource resource : loaded.getInventory().getResources()) {
			resource.setTile(resource.getTileId());
		}
		// give each robot's cargo its tile
		for (AbstractRobot robot : loaded.getRobotQueue()) {
			Resource resource = robot.getCargo();
			if (resource != null) {
				resource.setTile(resource.getTileId());
			}
		}
		return loaded;
	}

}
