package beepBoop.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

import beepBoop.model.Inventory;
import beepBoop.model.Level;

public class MainFrame extends JFrame {
	private LevelUI levelUI;
	private InventoryUI inventoryUI;
	private RobotTerminalUI terminalUI;
	

	public MainFrame(){
		super();
		this.setLayout(new GridBagLayout());
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
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		this.add(getLevelUI(),c);
	}

	public void initInventoryUI(Inventory inventory) {
		this.inventoryUI = new InventoryUI(inventory);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 0;
		add(inventoryUI,c);
		
	}
	
	public void initTerminalUI() {
		this.terminalUI = new RobotTerminalUI();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 1;
		add(terminalUI,c);
		
	}

	public InventoryUI getInventoryUI() {
		return inventoryUI;
	}

	public RobotTerminalUI getTerminalUI() {
		return terminalUI;
	}

	public void setTerminalUI(RobotTerminalUI terminalUI) {
		this.terminalUI = terminalUI;
	}
	
}
