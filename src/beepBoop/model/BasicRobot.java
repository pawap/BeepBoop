package beepBoop.model;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

/**
 * The most basic robot. Can carry up to 1000 of one type of Resource.
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class BasicRobot extends Thing implements IRobot{

	private static final long serialVersionUID = 2698956408529540093L;
	private static int robotCounter;
    public final static int MAX_CAPACITY = 1000;
    
	private List<String> memory;
	private int pc;
	private Resource cargo;
    private List<String> errorLog;
    private List<String> sensors;
	private String name;
	
	static {
		robotCounter = 0;
	}
	
	public BasicRobot() {
		this("BasicBeepBot " + BasicRobot.robotCounter);
	}
	
	public BasicRobot(String name) {
		super(TileFactory.ROBOT_0);
		this.memory = new LinkedList<String>();
		this.errorLog = new LinkedList<String>();
		this.sensors = new LinkedList<String>();
		this.name = name;
		pc = 0;
		sensors.add("FREE");
		sensors.add("RESOURCE");
		BasicRobot.robotCounter += 1;
	}
	

	@Override
	public List<String> getMemory() {
		return memory;
	}

	@Override
	public void setMemory(List<String> memory) {
		this.memory = memory;
	}

	@Override
	public int getPc() {
		return pc;
	}

	@Override
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
    
    @Override
    public List<String> getErrorLog() {
        return errorLog;
    }
    
    @Override
    public void setErrorLog(List<String> errorLog) {
        this.errorLog = errorLog;
        this.setChanged();
        this.notifyObservers();
    }
	
    @Override
	public void move(Point p) {
		this.setPosition(p);
		incrementPc();
	}
	
    @Override
	public Command getCurrentCommand() {
		String command = memory.get(pc);
		return new Command(command);
	}

    @Override
	public void incrementPc() {
		pc = (pc >= memory.size()-1)?0:pc+1;
	}
	
    @Override
    public void setError(String errorMsg) {
        if (errorMsg.equals("NoError")) return;
        this.errorLog.add(errorMsg);
        this.setChanged();
        this.notifyObservers();
        
    }
    
    public void addCargo(int load) {
        this.cargo.increaseAmount(load);
        this.setChanged();
        this.notifyObservers();
    }
    
    public int removeCargo(int load) {       
        this.setChanged();
        this.notifyObservers();
        return  this.cargo.takeAmount(load);
    }

    @Override
    public boolean hasSensor(String name) {
        return sensors.contains(name);
    }

    @Override
	public String getName() {
		return this.name;
	}

}
