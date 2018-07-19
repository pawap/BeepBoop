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

public class RTConstrUI extends AbstractRobotTerminalUI{

	private static final long serialVersionUID = 4276533727373179235L;
	private JComboBox<String> robotClassDropDown;
	private JTextArea infoField;
	private JButton backButton;
	private JButton constructButton;
	
	public RTConstrUI() {
		super();
		this.setLayout(new GridBagLayout());
		
		//Add label
		JLabel classDropDownLabel = new JLabel("Choose a robot class to construct!");
		GridBagConstraints c = new GridBagConstraints();        
		c.weighty = 0;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		this.add(classDropDownLabel);
		
		//Add robotClassDropdown
		robotClassDropDown = new JComboBox<String>();
		robotClassDropDown.setModel(new DefaultComboBoxModel<String>());
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridy = 1;
		this.add(robotClassDropDown,c);
		
		//AddinfoField
		infoField = new JTextArea(20, 10);
		infoField.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 2;
		this.add(infoField,c);
		JScrollPane scrollPane = new JScrollPane(infoField);
        this.add(scrollPane,c);
		
		//Add buttons
        constructButton = new JButton("Construct");
        backButton = new JButton("Back");
        c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 3;
		this.add(constructButton);
		c.gridx = 1;
		this.add(backButton);
	}

	@Override
	public void addListeners(HashMap<String, EventListener> listeners) {
		EventListener rcDropDownL = listeners.get("rcDropDown");
		EventListener consL = listeners.get("constr");
		EventListener backL = listeners.get("back");
		
		if (rcDropDownL instanceof ItemListener &&
			consL instanceof ActionListener &&
			backL instanceof ActionListener) {
			robotClassDropDown.addItemListener((ItemListener) rcDropDownL);
			constructButton.addActionListener((ActionListener) consL);
			backButton.addActionListener((ActionListener) backL);
		}
		
	}

	public void setInfoText(String text) {
		this.infoField.setText(text);		
	}

}
