package beepBoop;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	LandscapeUI landscapeUI;

	public MainFrame(){
		super();
	}
	
	public void initLandscapeUI(Landscape landscape) {
		landscapeUI = new LandscapeUI(landscape);
		this.add(landscapeUI);
	}
	
	
	
}
