package beepBoop.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import beepBoop.model.resource.Resource;

public class Robot extends Thing {

    public final static int MAX_CAPACITY = 1000;
    
//	private BufferedImage img;
	private List<String> memory;
	private int pc;
	private Point nextPosition;
	private boolean blocked;
	private boolean moved;
	private Resource cargo;

    private List<String> errorLog;
	
	
	public Robot() {
		super(TileFactory.ROBOT_0);
		this.memory = new LinkedList<String>();
	    this.errorLog = new LinkedList<String>();
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

	public Resource getCargo()
    {
        return cargo;
    }
    public void setCargo(Resource cargo)
    {
        this.cargo = cargo;
        this.setChanged();
        this.notifyObservers();
    }
    public List<String> getErrorLog()
    {
        return errorLog;
    }
    public void setErrorLog(List<String> errorLog)
    {
        this.errorLog = errorLog;
        this.setChanged();
        this.notifyObservers();
    }
    public Point calcNextPosition() {
		if (moved) {
			String command = memory.get(pc);
			nextPosition = calcPositionFromCommand(command);
			moved = false;
		}
		return nextPosition;
	}

	private Point calcPositionFromCommand(String commandStr) {
		Command command = new Command(commandStr);
	    Point newPosition = Command.getPointFromDirection(getPosition(), command.getType());
		return newPosition;
	}

	
	private Command process(String commandStr) {
        return new Command(commandStr);
	}	
	

	public void setBlocked(boolean b) {
		this.blocked = b;
		
	}
	
	public void move() {
		this.setPosition(nextPosition);
		incrementPc();
		moved = true;
	}
	
	public Command act() {
		String command = memory.get(pc);
		moved = true;
		incrementPc();
		return process(command);
	}
	

	private void incrementPc() {
		pc = (pc >= memory.size()-1)?0:pc+1;
	}
	
    public void setError(String errorMsg)
    {
        if (errorMsg.equals("NoError")) return;
        this.errorLog.add(errorMsg);
        this.setChanged();
        this.notifyObservers();
        
    }
    public void addCargo(int load)
    {
        this.cargo.increaseAmount(load);
        this.setChanged();
        this.notifyObservers();
    }

}
