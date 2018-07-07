package beepBoop.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.EventListener;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import beepBoop.model.Robot;

public class RTManageUI extends AbstractRobotTerminalUI {
	private JComboBox<Robot> robotsDropDown;
	private JComboBox<String> infoChooserDropDown;
	private JLabel cargoLabel;
	private JTextArea infoField;
	private JButton backButton;
	private Robot currentRobot;
	private String currentInfo;

	public RTManageUI() {
		super();
		this.setLayout(new GridBagLayout());

		// Add robotsDropDownLabel
		JLabel robotsDropDownLabel = new JLabel("Available Robots");
		GridBagConstraints c = new GridBagConstraints();        
		c.weighty = 0;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;

		this.add(robotsDropDownLabel,c);

		// Add robotsDropDown
		robotsDropDown = new JComboBox<Robot>();
		robotsDropDown.setModel(new DefaultComboBoxModel<Robot>());
		
		c.gridy = 1;
		this.add(robotsDropDown,c);
		
		//Add cargoLabel
		cargoLabel = new JLabel();
		c.gridy = 2;
		this.add(cargoLabel);
		
		// Add infoField and its scrollPane
		infoField = new JTextArea(20, 10);
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 3;
		this.add(infoField,c);
		JScrollPane scrollPane = new JScrollPane(infoField);
        this.add(scrollPane,c);
        
        //Add infoChooserDropDown
        infoChooserDropDown = new JComboBox<String>();
        infoChooserDropDown.setModel(new DefaultComboBoxModel<String>());
        infoChooserDropDown.addItem("Show Error Log");
        infoChooserDropDown.addItem("Edit Script");
        infoChooserDropDown.addItem("Import Script");
        infoChooserDropDown.addItem("Export Script");
        
        //Add backButton
        backButton = new JButton("Back");
        c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(backButton);
        
	}

	/**
	 * @param listeners has to contain "robDrop" -> ItemListener, "infDrop" -> ItemListener and "back" -> ActionListener 
	 */
	@Override
	public void addListeners(HashMap<String, EventListener> listeners) {
		EventListener robDropL = listeners.get("robDrop");
		EventListener infDropL = listeners.get("infDrop");
		EventListener backL = listeners.get("back");
		
		if(robDropL instanceof ItemListener &&
		   infDropL instanceof ItemListener &&
		   backL instanceof ActionListener) {
			robotsDropDown.addItemListener((ItemListener) listeners.get("robDrop"));
			infoChooserDropDown.addItemListener((ItemListener) listeners.get("infDrop"));
			backButton.addActionListener((ActionListener) listeners.get("back"));
		}
	}

	public void setCurrentRobot(Robot robot) {
		this.currentRobot = robot;
		
	}

	public void setCurrentInfo(String info) {
		this.currentInfo = info;
		
	}

	public void updateInfoField() {
		// TODO Auto-generated method stub
		
	}

}
