package beepBoop.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

<<<<<<< HEAD
import beepBoop.model.ressource.Ressource;

public class Robot extends Actor {
=======
public class Robot extends Thing {
>>>>>>> bba70bfa4ee644138ec910f950687ba1f0d5b22a

//	private BufferedImage img;
	private List<String> memory;
	private int pc;
	private Point nextPosition;
	private boolean blocked;
	private boolean moved;
	private Ressource cargo;
	
	public Robot() {
		super(TileFactory.ROBOT_0);
		this.memory = new LinkedList<String>();
		pc = 0;
		blocked = false;
		moved = false;
//		img = new BufferedImage(Tile.SIZE.width,Tile.SIZE.height,BufferedImage.TYPE_INT_ARGB);
//		Graphics g = img.getGraphics();
//		g.setColor(Color.BLACK);
//		g.fillRect(0,0,Tile.SIZE.width, Tile.SIZE.height);
//		g.setColor(Color.WHITE);
//		g.drawOval(8,3,4,3);
//		g.drawRect(6, 7, 8, 7);
//		g.drawOval(3, 15, 14, 4);
	}
	@Override
	public void setPosition(Point position) {
		super.setPosition(position);
		moved = true;
	}
	
//	@Override
//	public Image getImage() {
//		return img;
//	}
	
	
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
			nextPosition = calcPositionFromCommand(command);
			moved = false;
		}
		return nextPosition;
	}

	private Point calcPositionFromCommand(String command) {
		switch(command.substring(0,Math.min(2, command.length()))) {
			case "L": return new Point(getPosition().x-1,getPosition().y);
			case "R": return new Point(getPosition().x+1,getPosition().y);
			case "U": return new Point(getPosition().x,getPosition().y-1);
			case "D": return new Point(getPosition().x,getPosition().y+1);
			case "DP":
			case "LD": return getPosition();
			case "IF": 
		}
		return getPosition();
	}

	
	private void process(String command) {
		switch(command.substring(0,Math.min(2, command.length()))) {
			case "DP": dumpRessource(command.substring(3));
			case "LD": loadRessource(command.substring(3));
		}	
	}	
	
	private void loadRessource(String substring) {
		if (cargo == nu)		
	}
	private void dumpRessource(String substring) {
		// TODO Auto-generated method stub
		
	}
	public void setBlocked(boolean b) {
		this.blocked = b;
		
	}
	
	public void move() {
		this.setPosition(nextPosition);
		incrementPc();
		moved = true;
	}
	
	public void act() {
		String command = memory.get(pc);
		process(command);
	}
	

	private void incrementPc() {
		pc = (pc >= memory.size()-1)?0:pc+1;
	}

}
