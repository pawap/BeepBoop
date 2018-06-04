package beepBoop.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import beepBoop.model.Inventory;
import beepBoop.model.Tile;
import beepBoop.model.resource.Resource;

public class InventoryUI extends JPanel {
	Inventory inventory;
	
	public InventoryUI (Inventory inventory) {
		super();
		this.inventory = inventory;
		inventory.addObserver(new Observer(){

			@Override
			public void update(Observable arg0, Object arg1) {
				
				
			}});
		this.setPreferredSize(new Dimension(500,500));
		this.setSize(new Dimension(500,500));
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setFont(new Font("Verdana", Font.BOLD, 15));
		g2d.setColor(Color.BLACK);
		int y = 15;
		for (Resource res: inventory.getRessources()){ 
				g2d.drawString(res.getName()+": "+res.getAmount(),0, y);
				y += 20;
		}
		

	}
	
}
