package beepBoop.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import beepBoop.model.Inventory;
import beepBoop.model.Resource;
import beepBoop.model.Tile;

public class InventoryUI extends JPanel {

	private static final long serialVersionUID = 109164110187359015L;
	Inventory inventory;
	List<ResourceLabel> resourceLabels;
	
	public InventoryUI (Inventory inventory) {
		super();
		this.inventory = inventory;
		this.setLayout(new FlowLayout());
		resourceLabels = new LinkedList<ResourceLabel>();
		inventory.addObserver(new Observer(){

			@Override
			public void update(Observable arg0, Object arg1) {
				updateResourceLabels();
				repaint();
			}});
		this.setPreferredSize(new Dimension(500,50));
		this.setSize(new Dimension(500,50));
		this.setMinimumSize(new Dimension(500,50));
		JLabel text = new JLabel("Inventory");
		text.setForeground(Color.LIGHT_GRAY);
		this.add(text);
		this.setBackground(Color.DARK_GRAY);
		updateResourceLabels();
		
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	private void updateResourceLabels() {
		for (ResourceLabel resLabel : resourceLabels) {
			remove(resLabel);
		}
		resourceLabels = new LinkedList<ResourceLabel>();    
		for (Resource res: inventory.getRessources()){ 
			resourceLabels.add(new ResourceLabel(res));
		}
		for (ResourceLabel resLabel : resourceLabels) {
			add(resLabel);
		}
		this.validate();
		repaint();
	}
	
}
