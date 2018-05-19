package beepBoop.ui;

import javax.swing.JFrame;

import beepBoop.model.Level;

public class MainFrame extends JFrame {
	private LevelUI levelUI;

	public MainFrame(){
		super();
	}
	
	public LevelUI getLevelUI() {
		return levelUI;
	}

	public void setLevelUI(LevelUI levelUI) {
		this.levelUI = levelUI;
	}

	public void initLevelUI(Level level) {
		setLevelUI(new LevelUI(level));
		this.add(getLevelUI());
	}
	
	
	
}
