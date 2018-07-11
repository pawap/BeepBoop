package beepBoop.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class MenuUI extends JPanel {
	protected HashMap<String, JButton> buttons;
	protected String[] buttonLabels;
	
	public MenuUI() {
		super();
		this.setLayout(new GridBagLayout());
		defineLabels(); //fills buttonLabels with desired labels for the buttons (=menu entries)
		buttons = new HashMap<String, JButton>();
		
		//set layout constraints
		GridBagConstraints constraints = new GridBagConstraints();        		
		constraints.weighty = 0;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_START;
        
        //create and add buttons
		for (String label : buttonLabels) {
			JButton button = new JButton(label);
			buttons.put(label, button);
			this.add(button, constraints);
		}
		
		//add a listener to each button
		registerListeners();
	}
	
	/**
	 * Subclasses need to override this method. It should fill the array buttonLabels
	 * with the desired labels which are later turned into the menu entries. 
	 */
	protected abstract void defineLabels();

	/**
	 * Subclasses need to override this method. It should define the desired action
	 * to performed when a button is pressed. 
	 * @param  the label of the button that has been pressed
	 */
	protected abstract void reactToClick(String buttonLabel);
	
	private void registerListeners() {
		for (String label : buttonLabels) {
			buttons.get(label).addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					reactToClick(label);
				}
				
			});
		}
	}
}
