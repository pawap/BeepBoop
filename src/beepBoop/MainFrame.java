package beepBoop;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	LevelUI levelUI;

	public MainFrame(){
		super();
	}
	
	public void initLevelUI(Level level) {
		levelUI = new LevelUI(level);
		this.add(levelUI);
	}
	
	
	
}
