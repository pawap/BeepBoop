package beepBoop.ui;

import java.awt.Color;
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
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import beepBoop.model.Inventory;
import beepBoop.model.Level;

/**
 * PTP 2018 BeepBoop - the RobotGame
 * 
 * The parent frame of the BeepBoop GUI.
 * 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = -2588683308034838956L;

	/**
	 * the default width of the BeepBoop GUI
	 */
	public final static int DEFAULT_WIDTH = 850;

	/**
	 * the default height of the BeepBoop GUI
	 */
	public final static int DEFAULT_HEIGHT = 510;
	private LevelUI levelUI;
	private InventoryUI inventoryUI;
	private AbstractRobotTerminalUI terminalUI;

	/**
	 * Constructor
	 */
	public MainFrame() {
		super();
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.DARK_GRAY);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	/**
	 * Initialize the MenuBar with the necessary ActionListeners.
	 * 
	 * @param loadListener
	 *            the ActionListener for the "Load" menu item
	 * @param saveListener
	 *            the ActionListener for the "Save as" menu item
	 * @param exitListener
	 *            the ActionListener for the "Exit" menu item
	 */
	public void initMenuBar(ActionListener loadListener, ActionListener saveListener, ActionListener exitListener) {
		// the bar
		JMenuBar menuBar = new JMenuBar();
		// the file menu
		menuBar.setBackground(Color.DARK_GRAY);
		menuBar.setForeground(Color.LIGHT_GRAY);
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.setForeground(Color.LIGHT_GRAY);
		// the load item
		JMenuItem loadMI = new JMenuItem("Load...");
		loadMI.setMnemonic(KeyEvent.VK_O);
		loadMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		loadMI.addActionListener(loadListener);
		fileMenu.add(loadMI);
		// the save item
		JMenuItem saveMI = new JMenuItem("Save as...");
		saveMI.setMnemonic(KeyEvent.VK_S);
		saveMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveMI.addActionListener(saveListener);
		fileMenu.add(saveMI);
		// the exit item
		JMenuItem exitMI = new JMenuItem("Exit");
		exitMI.setMnemonic(KeyEvent.VK_E);
		exitMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		exitMI.addActionListener(exitListener);
		fileMenu.add(exitMI);

		menuBar.add(fileMenu);
		this.setJMenuBar(menuBar);
	}

	/**
	 * @return the Level UI of the GUI
	 */
	public LevelUI getLevelUI() {
		return levelUI;
	}

	/**
	 * Set the LevelUI of the GUI.
	 * 
	 * @param levelUI
	 *            the new LevelUI
	 */
	public void setLevelUI(LevelUI levelUI) {
		this.levelUI = levelUI;
	}

	/**
	 * Initialize the LevelUI with the corresponding Level.
	 * 
	 * @param level
	 *            the Level to be displayed
	 */
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
		this.add(getLevelUI(), c);
	}

	/**
	 * Initialize the InventoryUI with the corresponding Inventory.
	 * 
	 * @param inventory
	 *            the Inventory to be displayed
	 */
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
		add(inventoryUI, c);

	}

	/**
	 * Initialize the TerminalUI.
	 */
	public void initTerminalUI() {
		this.terminalUI = new RTInactiveUI();
		Dimension dim = new Dimension(300, 400);
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
		add(terminalUI, c);

	}

	/**
	 * @return the InventoryUI of the GUI
	 */
	public InventoryUI getInventoryUI() {
		return inventoryUI;
	}

	/**
	 * @return the TerminalUI of the GUI
	 */
	public AbstractRobotTerminalUI getTerminalUI() {
		return terminalUI;
	}

	/**
	 * Set the TerminalUI of the GUI.
	 * 
	 * @param terminalUI
	 *            the new TerminalUI.
	 */
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
		add(terminalUI, c);
		this.setVisible(true);
		this.repaint();
	}

	/**
	 * Open a modal Dialog displaying the given String. Auto-closes the message
	 * after 2 seconds
	 * 
	 * @param message
	 *            the message to be displayed
	 */
	public void showMessage(String message) {
		JDialog messageBox = new JDialog(this, "Message", Dialog.ModalityType.DOCUMENT_MODAL);
		messageBox.add(new JLabel(message));
		messageBox.setLocation(this.getWidth() / 2, this.getHeight() / 2);
		messageBox.setLayout(new FlowLayout());
		messageBox.pack();
		// use swing timer, which works on eventDispatchThread, to avoid
		// complication, bugs and frustration
		Timer timer = new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				messageBox.setVisible(false);
				messageBox.dispose();
			}
		});
		timer.setRepeats(false);
		timer.start();
		messageBox.setVisible(true);
	}

}
