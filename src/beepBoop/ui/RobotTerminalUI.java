package beepBoop.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import beepBoop.controller.RobotQueue;
import beepBoop.model.Robot;

/**
 * The UI class for the robot terminal. The graphical interface that lets the 
 * player construct and then program robots 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class RobotTerminalUI extends JPanel {
	private Boolean active;
	private JComboBox<Robot> robotsDropDown;
	private RobotUI robotUI;

	/**
	 * Constructor
	 */
	public RobotTerminalUI() {
		super();
		this.setActive(false);
		this.setLayout(new GridBagLayout());
		
		// Add RobotsDropDown
        JLabel robotsDropDownLabel = new JLabel("Available Robots");
        GridBagConstraints c = new GridBagConstraints();        
        c.weighty = 0;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.WEST;
        
        this.add(robotsDropDownLabel,c);
        
		robotsDropDown = new JComboBox<Robot>();
		robotsDropDown.setModel(new DefaultComboBoxModel<Robot>());
		robotsDropDown.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e)
            {
                viewRobot((Robot) e.getItem());
                
            }
		    
		});
		c.gridy = 1;
        this.add(robotsDropDown,c);
    
               
	
	}

	/**
	 * Returns whether the terminal is currently active
	 * @return true if the terminal is currently active
	 */
	public Boolean isActive() {
		return active;
	}

	/**
	 * Set the terminal to active or inactive
	 * @param active true if the terminal is supposed to be active, false otherwise
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

    public void fillRobotsDropDown(RobotQueue robotQueue)
    {      
        for (Robot robot: robotQueue) {
            robotsDropDown.addItem(robot);
        }
        
    }
	
    public void viewRobot(Robot robot) {
        if (this.robotUI == null) {
            this.robotUI = new RobotUI(robot);
            GridBagConstraints c = new GridBagConstraints();        
            c.weighty = 0;
            c.weightx = 1;
            c.gridx = 0;
            c.gridy = 2;
            c.fill = GridBagConstraints.BOTH;
            c.anchor = GridBagConstraints.WEST;           
            this.add(robotUI,c);
        } else {
            this.robotUI.setRobot(robot);
        }
    }


}
