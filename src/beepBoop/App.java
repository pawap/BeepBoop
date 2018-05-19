package beepBoop;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class App {

	
	
	
	private MainFrame gui;
	private boolean exit;

	public App() {
		TileFactory tf = TileFactory.getInstance();
		tf.loadTiles();
		Landscape landscape = new Landscape(new Dimension(20,20));
		landscape.placeRect(0, 0, 19, 19, tf.GRASS);
		landscape.placeRect(0, 0, 0, 19, tf.ROCK);
		landscape.placeRect(0, 0, 19, 0, tf.ROCK);
		landscape.placeRect(0, 19, 19, 19, tf.ROCK);
		landscape.placeRect(19, 0, 19, 19, tf.ROCK);
		landscape.placeRect(8, 8, 12, 12, tf.EARTH);
		
		
		gui = new MainFrame();
		gui.initLandscapeUI(landscape);
		gui.setSize(1000, 400);		 
		//gui.pack();
		gui.setVisible(true); // 
		startMainLoop();
	}

	private void startMainLoop() {
		exit = false;
		
		Action leftAction = new AbstractAction(){
			int i = 0;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Left");// TODO Auto-generated method stub
				if (i == 3){
					exit = true;
				}
				i++;
			}
			
		};
		Action rightAction = new AbstractAction(){
			int i = 0;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Right");// TODO Auto-generated method stub
				if (i == 3){
					exit = true;
				}
				i++;
			}
			
		};
		Action upAction = new AbstractAction(){
			int i = 0;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Left");// TODO Auto-generated method stub
				if (i == 3){
					exit = true;
				}
				i++;
			}
			
		};
		Action downAction = new AbstractAction(){
			int i = 0;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Left");// TODO Auto-generated method stub
				if (i == 3){
					exit = true;
					System.out.println("X");
					gui.setVisible(false);
					gui.dispose();
				}
				i++;
			}
			
		};
		gui.landscapeUI.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),
                "left");
		gui.landscapeUI.getActionMap().put("left",
                 leftAction);
		gui.landscapeUI.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),
                "right");
		gui.landscapeUI.getActionMap().put("right",
                 rightAction);
		gui.landscapeUI.getInputMap().put(KeyStroke.getKeyStroke("UP"),
                "up");
		gui.landscapeUI.getActionMap().put("up",
                 upAction);
		gui.landscapeUI.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),
                "down");
		gui.landscapeUI.getActionMap().put("down",
                 downAction);
		gui.landscapeUI.requestFocus();


	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		App app = new App();
		
		
	}

}
