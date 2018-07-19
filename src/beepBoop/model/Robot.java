package beepBoop.model;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class Robot extends Thing {

	private static int robotCounter;
    public final static int MAX_CAPACITY = 1000;
    
	private List<String> memory;
	private int pc;
	private boolean blocked;
	private Resource cargo;
    private List<String> errorLog;
    private List<String> sensors;
	private String name;
	
	static {
		robotCounter = 0;
	}
	
	public Robot() {
		this("BeepBot " + Robot.robotCounter);
	}
	
	public Robot(String name) {
		super(TileFactory.ROBOT_0);
		this.memory = new LinkedList<String>();
		this.errorLog = new LinkedList<String>();
		this.sensors = new LinkedList<String>();
		this.name = name;
		pc = 0;
		blocked = false;
		sensors.add("FREE");
		sensors.add("RESOURCE");
		Robot.robotCounter += 1;
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


	
	public void move(Point p) {
		this.setPosition(p);
		incrementPc();
	}
	
	public Command getCurrentCommand() {
		String command = memory.get(pc);
		return new Command(command);
	}

	public void incrementPc() {
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
    public int removeCargo(int load)
    {       
        this.setChanged();
        this.notifyObservers();
        return  this.cargo.takeAmount(load);
    }


    public boolean hasSensor(String name)
    {
        return sensors.contains(name);
    }



	public String getName() {
		return this.name;
	}



}
