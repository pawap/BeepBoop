package beepBoop.model;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

/**
 * The most basic robot. Can carry up to 1000 of one type of Resource.
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
public class BasicRobot extends AbstractRobot{

	private static final long serialVersionUID = 2698956408529540093L;
	private static int robotCounter;
	
	static {
		robotCounter = 0;
	}
	
	/**
	 * Constructor
	 * gives the new robot a default name.
	 */
	public BasicRobot() {
		this("BasicBeepBot " + BasicRobot.robotCounter);
	}
	
	/**
	 * Constructor
	 * @param name the name for the new robot.
	 */
	public BasicRobot(String name) {
		super(TileFactory.ROBOT_0);
		this.memory = new LinkedList<String>();
		this.errorLog = new LinkedList<String>();
		this.sensors = new LinkedList<String>();
		this.name = name;
		this.maxCapacity = 1000;
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

	@Override
	public Resource getCargo()
    {
        return cargo;
    }
	
	@Override
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
    
    @Override
    public void addCargo(int load) {
    	if (cargo != null) {
    		this.cargo.increaseAmount(load);
    		this.setChanged();
    		this.notifyObservers();
    	}
    }
    
    @Override
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

	/**
	 * @return information for the user about this class of robot
	 */
	public static String getInfo() {
		return "The most basic robot type.";
	}

}
