package beepBoop.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.EventListener;
import java.util.HashMap;

import javax.swing.JTextArea;

public class RTInactiveUI extends AbstractRobotTerminalUI {

	private static final long serialVersionUID = -9220864977067569381L;
	
	public RTInactiveUI() {
		super();
		this.setLayout(new GridBagLayout());

		String text = "Access the nearest Robot Terminal. A WiFi connection to your brain implant will be established automatically.";
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setText(text);
		GridBagConstraints c = new GridBagConstraints();        
		c.weighty = 0;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		this.add(textArea, c);

	}

	@Override
	public void addListeners(HashMap<String, EventListener> listeners) {}

}
