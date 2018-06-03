package beepBoop.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Robot extends Thing {

	private BufferedImage img;
	private List<String> memory;
	private int pc;
	private Point nextPosition;
	private boolean blocked;
	private boolean moved;
	
	public Robot() {
		super(TileFactory.ROBOT_0);
		this.memory = new LinkedList<String>();
		pc = 0;
		blocked = false;
		moved = false;
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
	public void setPosition(Point position) {
		super.setPosition(position);
		moved = true;
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
		if (moved) {
			String command = memory.get(pc);
			nextPosition = process(command);
			moved = false;
		}
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

	public void setBlocked(boolean b) {
		this.blocked = b;
		
	}
	public void move() {
		this.setPosition(nextPosition);
		pc = (pc >= memory.size()-1)?0:pc+1;
		moved = true;
	}
	

}
