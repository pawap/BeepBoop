package beepBoop.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;

public class RTMainUI extends AbstractRobotTerminalUI {
	private JButton constrButton;
	private JButton manageButton;

	public RTMainUI() {
		super();
		this.setLayout(new GridBagLayout());

		//set layout constraints
		GridBagConstraints constraints = new GridBagConstraints();        		
		constraints.weighty = 0;
		constraints.weightx = 1;
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.PAGE_START;

		//create and add buttons
		constrButton = new JButton("Construct Robots");
		manageButton = new JButton("Manage Robots");
		this.add(constrButton, constraints);
		this.add(manageButton, constraints);

	}

	/**
	 * @param listeners the keys should be "constr" and "manage" 
	 */
	@Override
	public void addListeners(HashMap<String, ActionListener> listeners) {
		constrButton.addActionListener(listeners.get("constr"));
		manageButton.addActionListener(listeners.get("manage"));		
	}


}
