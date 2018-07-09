package beepBoop.ui;

import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import beepBoop.model.Inventory;
import beepBoop.model.Level;

public class MainFrame extends JFrame {
	private LevelUI levelUI;
	private InventoryUI inventoryUI;
	private AbstractRobotTerminalUI terminalUI;
	

	public MainFrame(){
		super();
		this.setLayout(new GridBagLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public LevelUI getLevelUI() {
		return levelUI;
	}

	public void setLevelUI(LevelUI levelUI) {
		this.levelUI = levelUI;
	}

	public void initLevelUI(Level level) {
		setLevelUI(new LevelUI(level));
		GridBagConstraints c = new GridBagConstraints();		
		c.weighty = 1;
		c.weightx = 0.6;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.EAST;
		this.add(getLevelUI(),c);
	}

	public void initInventoryUI(Inventory inventory) {
		this.inventoryUI = new InventoryUI(inventory);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weightx = 0.2;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 0;
		add(inventoryUI,c);
		
	}
	
	public void initTerminalUI() {
		this.terminalUI = new RTMainUI();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 0.2;
		c.weighty = 1;
		c.gridx = 2;
		c.gridy = 0;
		add(terminalUI,c);
		
	}

	public InventoryUI getInventoryUI() {
		return inventoryUI;
	}

	public AbstractRobotTerminalUI getTerminalUI() {
		return terminalUI;
	}

	public void setTerminalUI(AbstractRobotTerminalUI terminalUI) {
		this.remove(this.terminalUI);
		this.terminalUI = terminalUI;
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 0.2;
		c.weighty = 1;
		c.gridx = 2;
		c.gridy = 0;
		add(terminalUI,c);
		this.setVisible(true);
		this.repaint();
	}
	
	public void showMessage(String str) {
		JDialog messageBox = new JDialog(this, "Message", Dialog.ModalityType.DOCUMENT_MODAL);
		messageBox.add(new JLabel(str));
		messageBox.setLocation(this.getWidth()/2, this.getHeight()/2);
		messageBox.setLayout(new FlowLayout());
		messageBox.pack();
		messageBox.setVisible(true);
	}
	
}
