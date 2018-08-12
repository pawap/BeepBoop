package beepBoop.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import beepBoop.model.AbstractRobot;
import beepBoop.model.BasicRobot;
import beepBoop.model.Level;
import beepBoop.model.Resource;
import beepBoop.service.RobotQueue;
import beepBoop.ui.AbstractRobotTerminalUI;
import beepBoop.ui.MainFrame;
import beepBoop.ui.RTConstrUI;
import beepBoop.ui.RTInactiveUI;
import beepBoop.ui.RTMainUI;
import beepBoop.ui.RTManageUI;

/**
 * PTP 2018 BeepBoop - the RobotGame
 * 
 * Controller for the RobotTerminal Manipluates the Level by adding
 * 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class RobotTerminalController implements Observer {

	private RobotQueue robotQueue;
	private AbstractRobotTerminalUI robotTerminalUI;
	private MainFrame mainFrame;
	private AbstractRobot currentRobot;
	private String currentInfoType;
	private Level level;

	/**
	 * Constructor
	 * 
	 * @param robotTerminalUI
	 *            the current robot terminal ui
	 * @param mainFrame
	 *            the main gui
	 * @param level
	 *            the current Level
	 */
	public RobotTerminalController(AbstractRobotTerminalUI robotTerminalUI, MainFrame mainFrame, Level level) {
		this.robotQueue = level.getRobotQueue();
		this.mainFrame = mainFrame;
		this.robotTerminalUI = robotTerminalUI;
		this.level = level;
		addUIListeners();
	}

	/**
	 * Navigates through the Terminal UI menu
	 * 
	 * @param destination
	 *            id of the submenu to display, element of {"main", "constr",
	 *            "manage"}
	 */
	public void navigateTo(String destination) {
		switch (destination) {
		case ("main"):
			this.robotTerminalUI = new RTMainUI();
			break;
		case ("constr"):
			this.robotTerminalUI = new RTConstrUI();
			RTConstrUI ui = (RTConstrUI) robotTerminalUI;
			updateRTConstrUIInfoField((String) ui.getRobotClassDropDown().getSelectedItem());
			break;
		case ("manage"):
			RTManageUI rtUI = new RTManageUI();
			this.robotTerminalUI = rtUI;
			rtUI.fillRobotsDropDown(robotQueue);
			if (currentRobot == null) {
				AbstractRobot robot = robotQueue.peekFirst();
				robot.addObserver(this);
				this.currentRobot = robot;
			}
			if (currentRobot != null) {
				rtUI.getRobotsDropDown().setSelectedItem(currentRobot.getName());
			}
			if (currentInfoType == null) {
				currentInfoType = (String) rtUI.getInfoChooserDropDown().getSelectedItem();
			}
			rtUI.getInfoChooserDropDown().setSelectedItem(currentInfoType);
		}
		addUIListeners();
		mainFrame.setTerminalUI(robotTerminalUI);
	}

	/**
	 * @return the last info type set for the info field of a RTManageUI
	 */
	public String getCurrentInfoType() {
		return currentInfoType;
	}

	/**
	 * set the info type for the RTManageUI info field
	 * 
	 * @param infoType
	 *            element of {"Error Log", "Program", "Load Program"}
	 */
	public void setCurrentInfoType(String infoType) {
		this.currentInfoType = infoType;
	}

	// set the robot to be managed with RTManageUI
	private void setCurrentRobot(AbstractRobot robot) {
		if (robot != null) {
			this.currentRobot.deleteObserver(this);
			this.currentRobot = robot;
			this.currentRobot.addObserver(this);
			if (this.currentInfoType.equals("Program")) { // if the program of
															// the last robot is
															// being shown,
															// prepare to load
															// this one's
				setCurrentInfoType("Load Program");
			}
			update(null, null);
		}

	}

	// adds EventListeners based on the current terminal ui
	private void addUIListeners() {
		HashMap<String, EventListener> listeners = new HashMap<String, EventListener>();

		if (robotTerminalUI instanceof RTManageUI) {
			listeners.put("robDrop", new RobDropListener());
			listeners.put("infDrop", new InfDropListener());
			listeners.put("import", new ImportListener());
			listeners.put("export", new ExportListener());
			listeners.put("apply", new ApplyListener());
			listeners.put("back", new NavToMainListener());
			robotTerminalUI.addListeners(listeners);
		} else if (robotTerminalUI instanceof RTConstrUI) {
			listeners.put("rcDropDown", new RCDropListener());
			listeners.put("constr", new ConstructListener());
			listeners.put("back", new NavToMainListener());
			robotTerminalUI.addListeners(listeners);
		} else if (robotTerminalUI instanceof RTMainUI) {
			listeners.put("constr", new NavToConstrListener());
			listeners.put("manage", new NavToManageListener());
			robotTerminalUI.addListeners(listeners);
		} else if (robotTerminalUI instanceof RTInactiveUI) {
			return;
		} else {
			System.out.println("Unsuccessfully tried to add Listeners to " + robotTerminalUI.getClass()
					+ ". Modify RobotTerminalController.addUIListeners to handle " + robotTerminalUI.getClass() + ".");
		}
	}

	/**
	 * This method should be called when the player interacts with a terminal.
	 */
	public void openTerminal() {
		navigateTo("main");
	}

	// import a program from disk
	private List<String> importProgram() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("BeepBoopProgram File", "bbp"));
		chooser.setDialogTitle("Import a BeepBoopProgram for your Robot");
		int result = chooser.showDialog(this.mainFrame, "Import");
		List<String> imported = new ArrayList<String>();
		if (result == JFileChooser.APPROVE_OPTION) {
			try {
				imported = Files.readAllLines(Paths.get(chooser.getSelectedFile().getPath()));
			} catch (IOException e) {
				e.printStackTrace();
				mainFrame.showMessage("File could not be read.");
			}
			return imported;
		}
		return null;
	}

	// export a program to disk
	private void export(String type, String text) {
		JFileChooser chooser = new JFileChooser();
		String fileExtension = ".bbp";
		String fileType = "BeepBoopProgram File";
		chooser.setDialogTitle("Export the BeepBoopProgram of your Robot");
		if (type.equals("Error Log")) {
			chooser.setDialogTitle("Export the Error Log of your Robot");
			fileExtension = ".txt";
			fileType = "Textfile";
		}
		chooser.setFileFilter(new FileNameExtensionFilter(fileType, fileExtension));
		int result = chooser.showDialog(this.mainFrame, "Export");
		if (result == JFileChooser.APPROVE_OPTION) {
			String name = chooser.getSelectedFile().getPath();
			if (!name.endsWith(fileExtension)) {
				name += fileExtension;
			}
			try (FileWriter writer = new FileWriter(name)) {
				writer.write(text);
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
				mainFrame.showMessage("File could not be written.");
			}
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		if (this.robotTerminalUI instanceof RTManageUI) {
			RTManageUI rtUI = (RTManageUI) this.robotTerminalUI;
			// update cargoLabel
			String cargoString = (currentRobot.getCargo() != null) ? currentRobot.getCargo().getName() + ": "
					: "No Cargo!";
			JLabel cargoLabel = rtUI.getCargoLabel();
			cargoLabel.setText(cargoString);
			cargoLabel.setIcon(
					(currentRobot.getCargo() != null) ? new ImageIcon(currentRobot.getCargo().getImage()) : null);
			// adjust infoText if necessary
			switch (currentInfoType) {
			case ("Error Log"):
				rtUI.setInfoText(currentRobot.getErrorLog());
				break;
			case ("Load Program"):
				rtUI.setInfoText(currentRobot.getMemory());
				setCurrentInfoType("Program");
			}

			rtUI.repaint();

		}

	}

	// update the info field of a RTConstrUI
	private void updateRTConstrUIInfoField(String chosenRobotClass) {
		if (robotTerminalUI instanceof RTConstrUI) {
			String info = "";
			switch (chosenRobotClass) {
			case "Basic Robot":
				info = BasicRobot.getInfo();
			}
			((RTConstrUI) robotTerminalUI).setInfoText(info);
			robotTerminalUI.repaint();
		}
	}

	// constructs a robot of the desired type next to the player if possible.
	private boolean construct(String desiredType) {
		AbstractRobot newBot;
		// choose class
		switch (desiredType) {
		case "Basic Robot":
			newBot = new BasicRobot();
			break;
		default:
			return false;
		}
		// find free position
		Point playerPosition = level.getPlayer().getPosition();
		int x, y;
		for (int i = 0; i < 9; i++) {
			x = (i % 3) - 1 + playerPosition.x;
			y = (i / 3) - 1 + playerPosition.y;
			if (level.isPositionFree(x, y)) {
				List<Resource> costs = newBot.getCosts();
				if (this.level.getInventory().pay(costs)) { // check inventory
															// and pay if
															// possible
					newBot.setPosition(new Point(x, y));
					return this.level.addRobot(newBot);
				}
			}
		}
		return false;
	}

	/*
	 * Following are some EventListener implementations to be passed to the
	 * AbstractRobotTerminal subclasses via the addListeners method.
	 */

	/**
	 * This Listener navigates to the RTMainUI.
	 */
	class NavToMainListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			navigateTo("main");
		}

	}

	/**
	 * This Listener navigates to the RTConstrUI.
	 */
	class NavToConstrListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			navigateTo("constr");
		}

	}

	/**
	 * This Listener navigates to the RTManageUI.
	 */
	class NavToManageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			navigateTo("manage");

		}

	}

	/**
	 * This Listener is used to import .bbp files for the RTManageUI.
	 */
	class ImportListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			((RTManageUI) robotTerminalUI).setInfoText(importProgram());
			setCurrentInfoType("Program");

		}

	}

	/**
	 * This Listener exports text from RTManageUI's infoField.
	 */
	class ExportListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String text = ((RTManageUI) robotTerminalUI).getInfoText();
			export(currentInfoType, text);

		}

	}

	/**
	 * This Listener navigates to the RTManageUI.
	 */
	class ApplyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (currentInfoType.equals("Load Program") || currentInfoType.equals("Program")) {
				List<String> program = Arrays.asList(((RTManageUI) robotTerminalUI).getInfoText().split("\n"));
				currentRobot.setMemory(program);
				currentRobot.setPc(0);

			}
		}

	}

	/**
	 * This Listener tries to have a robot of the chosen Type constructed.
	 */
	class ConstructListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			RTConstrUI ui = (RTConstrUI) robotTerminalUI;
			String desiredType = (String) ui.getRobotClassDropDown().getSelectedItem();
			construct(desiredType);

		}

	}

	/**
	 * This Listener reacts to changes of a RTManageUI's robotsDropdown.
	 */
	class RobDropListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			AbstractRobot robot = robotQueue.getRobot((String) e.getItem());
			setCurrentRobot(robot);
			robotTerminalUI.repaint();
		}

	}

	/**
	 * This Listener reacts to changes of a RTManageUI's infoChooserDropdown.
	 */
	class InfDropListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			RTManageUI rtUI = ((RTManageUI) robotTerminalUI);
			String infoType = (String) e.getItem();
			setCurrentInfoType(infoType);
			JTextArea infoField = rtUI.getInfoField();
			if (infoType.equals("Error Log")) {
				infoField.setEditable(false);
			} else {
				infoField.setEditable(true);
			}
			update(null, null);
		}

	}

	/**
	 * This Listener reacts to the choosing of a new class of robot in the
	 * RTConstrUI and shows some information about the chosen class in the
	 * infoField.
	 */
	class RCDropListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			updateRTConstrUIInfoField((String) e.getItem());
		}

	}

}
