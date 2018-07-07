package beepBoop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.EventListener;
import java.util.HashMap;

import javax.swing.JPanel;

import beepBoop.model.Robot;
import beepBoop.ui.AbstractRobotTerminalUI;
import beepBoop.ui.MainFrame;
import beepBoop.ui.RTConstrUI;
import beepBoop.ui.RTMainUI;
import beepBoop.ui.RTManageUI;
import beepBoop.ui.RobotTerminalUI;

public class RobotTerminalController extends AbstractController {

	private RobotQueue robotQueue;
	private AbstractRobotTerminalUI robotTerminalUI;
	private MainFrame mainFrame;

	public RobotTerminalController(AbstractRobotTerminalUI robotTerminalUI, MainFrame mainFrame, RobotQueue robotQueue)
	{
		this.robotQueue = robotQueue;
		this.mainFrame = mainFrame;
		this.robotTerminalUI = robotTerminalUI;
	}

	public void navigateTo(String destination) {
		switch(destination) {
		case("main"):
			this.robotTerminalUI = new RTMainUI();
		    addRTMainUIListeners();
		    break;
		case("constr"):
			this.robotTerminalUI = new RTConstrUI();
		    addRTConstrUIListeners();
		    break;
		case("manage"):
			this.robotTerminalUI = new RTManageUI();
		    addRTManageUIListeners();
		}

		mainFrame.setTerminalUI(robotTerminalUI);
	}

	private void addRTManageUIListeners() {
		if (robotTerminalUI instanceof RTManageUI) {
			HashMap<String, EventListener> listeners = new HashMap<String, EventListener>();
			listeners.put("robDrop", new RobDropListener());
			listeners.put("infDrop", new InfDropListener());
			listeners.put("back", new NavToMainListener());
		}
		else {
			System.out.println("Called addRTManageUIListeners() on an instance of the wron class.");
		}
	}

	private void addRTConstrUIListeners() {
		if (robotTerminalUI instanceof RTConstrUI) {
			HashMap<String, ActionListener> listeners = new HashMap<String, ActionListener>();

		}
		else {
			System.out.println("Called addRTConstrUIListeners() on an instance of the wron class.");
		}

	}

	private void addRTMainUIListeners() {
		if (robotTerminalUI instanceof RTMainUI) {
			HashMap<String, ActionListener> listeners = new HashMap<String, ActionListener>();
			listeners.put("constr", new NavToConstrListener()); 
			listeners.put("manage", new NavToManageListener());
			robotTerminalUI.addListeners(listeners);
		}
		else {
			System.out.println("Called addRTRTMainUIListeners() on an instance of the wron class.");
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

	/*
	 * Following are some EventListener implementations to be passed to the AbstractRobotTerminal
	 * implementations via the add*Listeners methods.
	 */

	/**
	 * This Listener navigates to the RTMainUI. 
	 * @author ptp18-d06(Pawel Rasch, Tim Runge)
	 *
	 */
	class NavToMainListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			navigateTo("main");

		}

	}

	/**
	 * This Listener navigates to the RTConstrUI. 
	 * @author ptp18-d06(Pawel Rasch, Tim Runge)
	 *
	 */
	class NavToConstrListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			navigateTo("constr");

		}

	}

	/**
	 * This Listener navigates to the RTManageUI. 
	 * @author ptp18-d06(Pawel Rasch, Tim Runge)
	 *
	 */
	class NavToManageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			navigateTo("manage");

		}

	}
	
	/**
	 * This Listener reacts to changes of a RTManageUI's robotsDropdown. 
	 * @author ptp18-d06(Pawel Rasch, Tim Runge)
	 *
	 */
	class RobDropListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			((RTManageUI) robotTerminalUI).setCurrentRobot((Robot) e.getItem());
			((RTManageUI) robotTerminalUI).updateInfoField();
		}

	}
	
	/**
	 * This Listener reacts to changes of a RTManageUI's infoChooserDropdown. 
	 * @author ptp18-d06(Pawel Rasch, Tim Runge)
	 *
	 */
	class InfDropListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			((RTManageUI) robotTerminalUI).setCurrentInfo((String) e.getItem());
			((RTManageUI) robotTerminalUI).updateInfoField();
		}

	}

}
