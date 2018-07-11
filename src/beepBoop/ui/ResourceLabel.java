package beepBoop.ui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import beepBoop.model.resource.Resource;

public class ResourceLabel extends JPanel {
	private Resource resource;

	public ResourceLabel(Resource resource) {
		super();
		JLabel text = new JLabel(resource.getName()+": ");
		text.setForeground(Color.LIGHT_GRAY);
		this.add(text);
		this.add(new JLabel(new ImageIcon(resource.getImage())));
		
		this.resource = resource;
		this.setBackground(Color.DARK_GRAY);
		
	}
	
	
	
	
}
