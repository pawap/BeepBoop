package beepBoop.controller;

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

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import beepBoop.model.Robot;
import beepBoop.ui.AbstractRobotTerminalUI;
import beepBoop.ui.MainFrame;
import beepBoop.ui.RTConstrUI;
import beepBoop.ui.RTMainUI;
import beepBoop.ui.RTManageUI;

public class RobotTerminalController extends AbstractController {

	private RobotQueue robotQueue;
	private AbstractRobotTerminalUI robotTerminalUI;
	private MainFrame mainFrame;

	public RobotTerminalController(AbstractRobotTerminalUI robotTerminalUI, MainFrame mainFrame, RobotQueue robotQueue)
	{
		this.robotQueue = robotQueue;
		this.mainFrame = mainFrame;
		this.robotTerminalUI = robotTerminalUI;
		addUIListeners();
	}

	public void navigateTo(String destination) {
		switch(destination) {
		case("main"):
			this.robotTerminalUI = new RTMainUI();
		    break;
		case("constr"):
			this.robotTerminalUI = new RTConstrUI();
		    break;
		case("manage"):
			this.robotTerminalUI = new RTManageUI();
		    ((RTManageUI) this.robotTerminalUI).fillRobotsDropDown(robotQueue);
		}
		addUIListeners();
		mainFrame.setTerminalUI(robotTerminalUI);
	}

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
		}
		else if (robotTerminalUI instanceof RTConstrUI) {
			listeners.put("rcDropDown", new RCDropListener());
			listeners.put("constr", new ConstructListener());
			listeners.put("back", new NavToMainListener());
			robotTerminalUI.addListeners(listeners);
		}
		else if (robotTerminalUI instanceof RTMainUI) {
			listeners.put("constr", new NavToConstrListener()); 
			listeners.put("manage", new NavToManageListener());
			robotTerminalUI.addListeners(listeners);
		}
		else {
			System.out.println("Tried to add Listeners to an unknown subclass of AbstractTerminalUI.");
		}
	}


	/**
	 * This method should be called when the player interacts with a terminal. Sets the terminalUI to active.
	 */
	public void openTerminal() {
		//		robotTerminalUI.setActive(true);
		//		robotTerminalUI.fillRobotsDropDown(robotQueue);

		System.out.println("RobotTerminal Interaction");
		for (Robot robot: robotQueue) {
			if (robot.getCargo() != null) {
				System.out.println("Robot Cargo:");
				System.out.println("    "+robot.getCargo().getName()+": "+robot.getCargo().getAmount()); 
			}
			System.out.println("Robot ErrorLog");
			for (String str: robot.getErrorLog()) {
				System.out.println("    "+str); 
			}
		}

	}
	
	private List<String> importProgram() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("BeepBoopProgram File", "bbp"));
		chooser.setApproveButtonText("Import");
		chooser.setDialogTitle("Import a BeepBoopProgram for your Robot.");
		int result = chooser.showOpenDialog(this.robotTerminalUI);
		List<String> imported = new ArrayList<String>();
		if(result == JFileChooser.APPROVE_OPTION) {
			try {
				imported = Files.readAllLines(Paths.get(chooser.getSelectedFile().getPath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return imported;
		}
		return null;
	}
	
	private void export(String type, String text) {
		JFileChooser chooser = new JFileChooser();
		String fileExtension = ".bbp";
		String fileType = "BeepBoopProgram File";
		chooser.setApproveButtonText("Export");
		chooser.setDialogTitle("Export the BeepBoopProgram of your Robot.");
		if (type.equals("Error Log")) {
			chooser.setDialogTitle("Export the Error Log of your Robot.");
			fileExtension = ".txt";
			fileType = "Textfile";
		}
		chooser.setFileFilter(new FileNameExtensionFilter(fileType, fileExtension));
		int result = chooser.showOpenDialog(this.robotTerminalUI);
		if(result == JFileChooser.APPROVE_OPTION) {
			String name = chooser.getSelectedFile().getPath();
			if (!name.endsWith(fileExtension)) {
				name += fileExtension;
			}
			try (FileWriter writer = new FileWriter(name)){	
				writer.write(text);
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	/*
	 * Following are some EventListener implementations to be passed to the AbstractRobotTerminal
	 * implementations via the addListeners method.
	 */

	/**
	 * This Listener navigates to the RTMainUI. 
	 */
	class NavToMainListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			navigateTo("main");
			System.out.println("navigate to main");
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
			((RTManageUI) robotTerminalUI).setCurrentInfoType("Program");

		}

	}
	
	/**
	 * This Listener exports text from RTManageUI's infoField. 
	 */
	class ExportListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String text = ((RTManageUI) robotTerminalUI).getInfoText();
			String type = ((RTManageUI) robotTerminalUI).getCurrentInfoType();
			export(type, text);

		}

	}
	
	/**
	 * This Listener navigates to the RTManageUI. 
	 */
	class ApplyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			RTManageUI ui = ((RTManageUI) robotTerminalUI);
			if (ui.getCurrentInfoType().equals("Load Program") || ui.getCurrentInfoType().equals("Program")) {
				Robot robot = ((RTManageUI) robotTerminalUI).getCurrentRobot();
				//String splitRegex = "\\s*" + System.getProperty("line.separator") + "\\s*";
				List<String> program = Arrays.asList(((RTManageUI) robotTerminalUI).getInfoText().split("\n"));
				System.out.println("Program:"+program.get(2));
				robot.setMemory(program);
				robot.setPc(0);
				
			}
			System.out.println(Character.getName("\n".toCharArray()[0])+"RLSE"+((RTManageUI) robotTerminalUI).getCurrentInfoType());
		}

	}
	
	/**
	 * This Listener tries to have a robot of the chosen Type constructed. 
	 */
	class ConstructListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Robot construction is a thing of the future. Try to live in the now while we work on implementing this feature.");

		}

	}

	
	/**
	 * This Listener reacts to changes of a RTManageUI's robotsDropdown. 
	 */
	class RobDropListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			((RTManageUI) robotTerminalUI).setCurrentRobot((Robot) e.getItem());
			((RTManageUI) robotTerminalUI).update(null, null);
		}

	}
	
	/**
	 * This Listener reacts to changes of a RTManageUI's infoChooserDropdown. 
	 */
	class InfDropListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			((RTManageUI) robotTerminalUI).setCurrentInfoType((String) e.getItem());
			((RTManageUI) robotTerminalUI).update(null, null);
		}

	}
	
	/**
	 * This Listener reacts to the choosing of a new class of robot in the RTConstrUI and shows
	 * some information about the chosen class in the infoField. 
	 */
	class RCDropListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			((RTConstrUI) robotTerminalUI).setInfoText((String) e.getItem());
			robotTerminalUI.repaint();
		}

	}
	
}
