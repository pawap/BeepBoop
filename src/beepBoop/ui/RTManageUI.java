package beepBoop.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import beepBoop.controller.RobotQueue;
import beepBoop.model.Robot;

public class RTManageUI extends AbstractRobotTerminalUI implements Observer{
	private JComboBox<Robot> robotsDropDown;
	private JComboBox<String> infoChooserDropDown;
	private JLabel cargoLabel;
	private JTextArea infoField;
	private JButton backButton;
	private JButton applyButton;
	private JButton importButton;
	private JButton exportButton;
	private Robot currentRobot;
	private String currentInfoType;

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
		robotsDropDown = new JComboBox<Robot>();
		robotsDropDown.setModel(new DefaultComboBoxModel<Robot>());
		c.gridwidth = GridBagConstraints.REMAINDER;
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
        
        //Add infoChooserDropDown and set currentInfoType
        infoChooserDropDown = new JComboBox<String>();
        infoChooserDropDown.setModel(new DefaultComboBoxModel<String>());
        infoChooserDropDown.addItem("Error Log");
        infoChooserDropDown.addItem("Load Program");
        c.gridy = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        this.add(infoChooserDropDown, c);
        this.currentInfoType = "Error Log"; 
        
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
		EventListener robDropL = listeners.get("robDrop");
		EventListener infDropL = listeners.get("infDrop");
		EventListener impL = listeners.get("import");
		EventListener expL = listeners.get("export");
		EventListener appL = listeners.get("apply");
		EventListener backL = listeners.get("back");
		
		if(robDropL instanceof ItemListener &&
		   infDropL instanceof ItemListener &&
		   impL instanceof ActionListener &&
		   expL instanceof ActionListener &&
		   appL instanceof ActionListener &&
		   backL instanceof ActionListener) {
			robotsDropDown.addItemListener((ItemListener) robDropL);
			infoChooserDropDown.addItemListener((ItemListener) infDropL);
			importButton.addActionListener((ActionListener) impL);
			exportButton.addActionListener((ActionListener) expL);
			applyButton.addActionListener((ActionListener) appL);
			backButton.addActionListener((ActionListener) backL);
		}
		else {
			System.out.println("Tried to pass wrong Listener type to RTManageUI");
		}
	}

	/**
	 * 
	 * @param robot the Robot to be managed
	 */
	public void setCurrentRobot(Robot robot) {
		this.currentRobot.deleteObserver(this);
		this.currentRobot = robot;
		this.currentRobot.addObserver(this);
		update(null, null);
		
	}

	public void setCurrentInfoType(String infoType) {
		this.currentInfoType = infoType;
		if (infoType.equals("Error Log")) {
			this.infoField.setEditable(false);
		}
		else {
			this.infoField.setEditable(true);
		}
		update(null, null);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		//update cargoLabel
		String cargoString = (currentRobot.getCargo() != null)?
				currentRobot.getCargo().getName()+": "+currentRobot.getCargo().getAmount():"No Cargo!";
        this.cargoLabel.setText(cargoString);
        
        switch(currentInfoType) {
        case("Error Log"):		
        	setInfoText(currentRobot.getErrorLog()); 
            break;
        case("Load Program"):
        	setInfoText(currentRobot.getMemory());
            this.setCurrentInfoType("Program");
//        	break;
//        case("Import Script"):
//        	this.infoField.setText("");
//            for (String str : importScript()) {
//        	    this.infoField.append(str+"\n");
//            }
//
//            this.setCurrentInfoType("editing");
//        	break;
//        case("Export Script"):
//        	exportScript(this.infoField.getText());
        }

        this.repaint();
	}
	
	/**
	 * Adds the robots in a RobotQueue to this UI's robotDropDown
	 * @param robots the RobotQueue containing the robots to be added
	 */
	public void fillRobotsDropDown(RobotQueue robots) {
		for (Robot robot: robots) {
			robotsDropDown.addItem(robot);
        }
		this.currentRobot = robotsDropDown.getItemAt(0);
		this.currentRobot.addObserver(this);
	}

//	private void exportScript(String text) {
//		JFileChooser chooser = new JFileChooser();
//		String fileExtension = ".bbp";
//		String fileType = "BeepBoopProgram File";
//		if (currentInfoType.equals("Show Error Log")) {
//			fileExtension = ".txt";
//			fileType = "Textfile";
//		}
//		chooser.setFileFilter(new FileNameExtensionFilter(fileType, fileExtension));
//		int result = chooser.showOpenDialog(this);
//		if(result == JFileChooser.APPROVE_OPTION) {
//			String name = chooser.getSelectedFile().getPath();
//			if (!name.endsWith(fileExtension)) {
//				name += fileExtension;
//			}
//			try (FileWriter writer = new FileWriter(name)){	
//				writer.write(text);
//				writer.flush();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}

//	private List<String> importScript() {
//		JFileChooser chooser = new JFileChooser();
//		chooser.setFileFilter(new FileNameExtensionFilter("BeepBoopProgram File", "bbp"));
//		int result = chooser.showOpenDialog(this);
//		List<String> imported = new ArrayList<String>();
//		if(result == JFileChooser.APPROVE_OPTION) {
//			try {
//				imported = Files.readAllLines(Paths.get(chooser.getSelectedFile().getPath()));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return imported;
//	}

	/**
	 * Sets the text of the infoField
	 * @param text each String will be a row in the infoField
	 */
	public void setInfoText(List<String> text) {
		this.infoField.setText("");
        for (String row : text) {
        	this.infoField.append(row + "\n");
        }
		
	}

	public String getInfoText() {
		return infoField.getText();
	}
	
	public String getCurrentInfoType() {
		return currentInfoType;
	}

	public Robot getCurrentRobot() {
		return currentRobot;
	}

}
