package beepBoop.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.HashMap;

import javax.swing.JButton;

public class RTMainUI extends AbstractRobotTerminalUI {

	private static final long serialVersionUID = -6217279559652293388L;
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
	public void addListeners(HashMap<String, EventListener> listeners) {
		EventListener constrL = listeners.get("constr");
		EventListener manageL = listeners.get("manage");
		
		if(constrL instanceof ActionListener &&
		   manageL instanceof ActionListener) {
			constrButton.addActionListener((ActionListener) constrL);
			manageButton.addActionListener((ActionListener) manageL);		
		}
		else {
			System.out.println("Tried to pass wrong Listener type to RTMainUI");
		}
	}


}
