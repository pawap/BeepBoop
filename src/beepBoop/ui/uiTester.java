package beepBoop.ui;

import javax.swing.JFrame;

public class uiTester extends JFrame{

	public static void main (String[] args) {
		JFrame gui = new uiTester();
		MenuUI rt = new RTMainUI();
		gui.add(rt);
		gui.setSize(500, 500);
		gui.setVisible(true);
	}
}
