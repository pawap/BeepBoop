package beepBoop.ui;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
	
	public void initMenuBar(ActionListener loadListener, ActionListener saveListener, ActionListener exitListener) {
		//the bar
		JMenuBar menuBar = new JMenuBar();
		//the file menu
		JMenu fileMenu= new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		//the load item
		JMenuItem loadMI = new JMenuItem("Load...");
		loadMI.setMnemonic(KeyEvent.VK_O);
		loadMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		loadMI.addActionListener(loadListener);
		fileMenu.add(loadMI);
		//the save item
		JMenuItem saveMI = new JMenuItem("Save as...");
		saveMI.setMnemonic(KeyEvent.VK_S);
		saveMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveMI.addActionListener(saveListener);
		fileMenu.add(saveMI);
		//the exit item
		JMenuItem exitMI = new JMenuItem("Exit");
		exitMI.setMnemonic(KeyEvent.VK_E);
		exitMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		exitMI.addActionListener(exitListener);
		fileMenu.add(exitMI);
		
		menuBar.add(fileMenu);
		this.setJMenuBar(menuBar);	
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
		c.weighty = 0.9;
		c.weightx = 0.7;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.EAST;
		this.add(getLevelUI(),c);
	}

	public void initInventoryUI(Inventory inventory) {
		this.inventoryUI = new InventoryUI(inventory);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1;
		add(inventoryUI,c);
		
	}
	
	public void initTerminalUI() {
		this.terminalUI = new RTMainUI();
		Dimension dim = new Dimension(300,400);
		terminalUI.setPreferredSize(dim);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 0.3;
		c.weighty = 0.9;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 1;
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
		c.anchor = GridBagConstraints.ABOVE_BASELINE_TRAILING;
		c.weightx = 0.3;
		c.weighty = 0.9;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 1;
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
