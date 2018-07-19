package beepBoop.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import beepBoop.controller.RobotQueue;
import beepBoop.model.Robot;


public class RTManageUI extends AbstractRobotTerminalUI {

	private static final long serialVersionUID = 8732469449519407942L;
	private JComboBox<String> robotsDropDown;
	private JComboBox<String> infoChooserDropDown;
	private JLabel cargoLabel;
	private JTextArea infoField;
	private JButton backButton;
	private JButton applyButton;
	private JButton importButton;
	private JButton exportButton;

	public RTManageUI() {
		super();
		this.setLayout(new GridBagLayout());

		// Add robotsDropDownLabel
		JLabel robotsDropDownLabel = new JLabel("Choose a robot!");
		GridBagConstraints c = new GridBagConstraints();        
		c.weighty = 0;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;

		this.add(robotsDropDownLabel,c);

		// Add robotsDropDown
		robotsDropDown = new JComboBox<String>();
		robotsDropDown.setModel(new DefaultComboBoxModel<String>());
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridy = 1;
		this.add(robotsDropDown,c);
		
		//Add cargoLabel
		cargoLabel = new JLabel();
		c.gridy = 2;
		cargoLabel.setBackground(Color.DARK_GRAY);
		this.add(cargoLabel);
		
		// Add infoField and its scrollPane
		infoField = new JTextArea(20, 10);
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 3;
		this.add(infoField,c);
		JScrollPane scrollPane = new JScrollPane(infoField);
        scrollPane.setMinimumSize(new Dimension(200,300));
		this.add(scrollPane,c);
        
        //Add infoChooserDropDown and set currentInfoType
        infoChooserDropDown = new JComboBox<String>();
        infoChooserDropDown.setModel(new DefaultComboBoxModel<String>());
        infoChooserDropDown.addItem("Error Log");
        infoChooserDropDown.addItem("Load Program");
        c.gridy = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        this.add(infoChooserDropDown, c);
        
        //Add Buttons
        importButton = new JButton("Import Program");
        exportButton = new JButton("Export");
        applyButton = new JButton("Apply");
        backButton = new JButton("Back");
        c.gridwidth = 1;
        c.gridx = 3;
		this.add(importButton, c);
		c.gridx = 4;
		this.add(exportButton, c);
		c.gridx = 5;
		this.add(applyButton, c);
		c.gridx = 6;
		this.add(backButton, c);
        
	}

	/**
	 * @param listeners has to contain "robDrop" -> ItemListener, "infDrop" -> ItemListener and "back" -> ActionListener 
	 */
	@Override
	public void addListeners(HashMap<String, EventListener> listeners) {		
		if(correctListeners(listeners)) {
			robotsDropDown.addItemListener((ItemListener) listeners.get("robDrop"));
			infoChooserDropDown.addItemListener((ItemListener) listeners.get("infDrop"));
			importButton.addActionListener((ActionListener) listeners.get("import"));
			exportButton.addActionListener((ActionListener) listeners.get("export"));
			applyButton.addActionListener((ActionListener) listeners.get("apply"));
			backButton.addActionListener((ActionListener) listeners.get("back"));
		}
		else {
			System.out.println("Tried to pass wrong Listener type to RTManageUI");
		}
	}
	
	/**
	 * Adds the robots in a RobotQueue to this UI's robotDropDown
	 * @param robots the RobotQueue containing the robots to be added
	 */
	public void fillRobotsDropDown(RobotQueue robots) {
		for (Robot robot: robots) {
			robotsDropDown.addItem(robot.getName());
        }
	}


	/**
	 * Sets the text of the infoField
	 * @param text each String will be a row in the infoField
	 */
	public void setInfoText(List<String> text) {
		if (text == null) {
			return;
		}
		this.infoField.setText("");
        for (String row : text) {
        	this.infoField.append(row + "\n");
        }
		
	}

	public String getInfoText() {
		return infoField.getText();
	}
	
	public JComboBox<String> getRobotsDropDown() {
		return this.robotsDropDown;
	}

	public JLabel getCargoLabel() {
		return this.cargoLabel;
	}

	public JTextArea getInfoField() {
		return this.infoField;
	}

	public JComboBox<String> getInfoChooserDropDown() {
		return infoChooserDropDown;
		
	}
	
	private boolean correctListeners(HashMap<String, EventListener> listeners) {
		EventListener robDropL = listeners.get("robDrop");
		EventListener infDropL = listeners.get("infDrop");
		EventListener impL = listeners.get("import");
		EventListener expL = listeners.get("export");
		EventListener appL = listeners.get("apply");
		EventListener backL = listeners.get("back");
		
		return robDropL instanceof ItemListener &&
			   infDropL instanceof ItemListener &&
			   impL instanceof ActionListener &&
			   expL instanceof ActionListener &&
			   appL instanceof ActionListener &&
			   backL instanceof ActionListener;
	}

}
