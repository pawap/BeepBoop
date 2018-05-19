package beepBoop.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Robot extends Actor {

	private BufferedImage img;
	private List<String> memory;
	private int pc;
	private Point nextPosition;

	public Robot() {
		super();
		this.memory = new LinkedList<String>();
		pc = 0;
		img = new BufferedImage(Tile.SIZE.width,Tile.SIZE.height,BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,Tile.SIZE.width, Tile.SIZE.height);
		g.setColor(Color.WHITE);
		g.drawOval(8,3,4,3);
		g.drawRect(6, 7, 8, 7);
		g.drawOval(3, 15, 14, 4);
	}

	@Override
	public Image getImage() {
		return img;
	}
	
	
	public List<String> getMemory() {
		return memory;
	}

	public void setMemory(List<String> memory) {
		this.memory = memory;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public Point calcNextPosition() {
		String command = memory.get(pc);
		nextPosition = process(command);
		return nextPosition;
	}

	private Point process(String command) {
		switch(command.substring(0,Math.min(2, command.length()))) {
			case "L": return new Point(getPosition().x-1,getPosition().y);
			case "R": return new Point(getPosition().x+1,getPosition().y);
			case "U": return new Point(getPosition().x,getPosition().y-1);
			case "D": return new Point(getPosition().x,getPosition().y+1);
			case "IF": 
		}
		return getPosition();
	}
	

}
