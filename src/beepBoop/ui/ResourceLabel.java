package beepBoop.ui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import beepBoop.model.Resource;

/**
 * Displays the Image of a Resource and its amount.
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class ResourceLabel extends JPanel {
	private static final long serialVersionUID = -6940160337854773940L;
	
	/**
	 * Constructor
	 * @param resource the Resource to be displayed
	 */
	public ResourceLabel(Resource resource) {
		super();
		JLabel text = new JLabel(resource.getName()+": ");
		text.setForeground(Color.LIGHT_GRAY);
		this.add(text);
		this.add(new JLabel(new ImageIcon(resource.getImage())));
		
		this.setBackground(Color.DARK_GRAY);
		
	}
	
}
