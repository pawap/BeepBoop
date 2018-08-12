package beepBoop.model;

import java.awt.Point;
import java.util.List;

/**
 * PTP 2018
 * BeepBoop - the RobotGame
 * 
 * All robots should extend this class.
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 */
public abstract class AbstractRobot extends Thing{

	private static final long serialVersionUID = -6676887042773099939L;
	protected int maxCapacity;
	protected List<String> memory;
	protected int pc;
	protected int activityCounter;
	protected Resource cargo;
	protected List<String> errorLog;
	protected List<String> sensors;
	protected String name;

	/**
	 * Constructor
	 * @param tileId ID of the desired tile
	 */
	public AbstractRobot(int tileId) {
		super(tileId);
		pc = 0;
		activityCounter = 0;
	}

	/**
	 * @return the current activity-counter of the robot
	 */	
	public int getActivityCounter() {
		return activityCounter;
	}
	
	/**
	 * Set the current activity-counter of the robot. 
	 * @param activityCounter the desired value of the counter
	 */
	public void setActivityCounter(int activityCounter) {
		this.activityCounter = activityCounter;
	}
	
	/**
	 * Increment the activity-counter.
	 */
	public void incrementAvtivityCounter() {
		activityCounter++;
	}

	/**
	 * @return the current program of the robot, 1 String per Command
	 */
	public List<String> getMemory() {
		return memory;
	}
	
	/**
	 * Set the current program of the robot. 
	 * @param memory 1 String per Command
	 */
	public void setMemory(List<String> memory) {
		this.memory = memory;
	}
	
	/**
	 * @return the program counter of the robot
	 */
	public int getPc() {
		return pc;
	}
	
	/**
	 * Set the program counter of the robot.
	 * @param pc the desired value for the program counter
	 */
	public void setPc(int pc) {
		this.pc = pc;
	}
	
	/**
	 * @return the robots error log
	 */
    public List<String> getErrorLog() {
        return errorLog;
    }
	
	/**
	 * Set the error log of the robot.
	 * @param errorLog a list of the desired entries
	 */
    public void setErrorLog(List<String> errorLog) {
        this.errorLog = errorLog;
        this.setChanged();
        this.notifyObservers();
    }
	
	/**
	 * Move the robot to a point in the Level.
	 * @param p target position
	 */
	public void move(Point p) {
		this.setPosition(p);
		incrementPc();
	}
	
	/**
	 * @return the command currently pointed at by the program counter
	 */
	public Command getCurrentCommand() {
		String command = memory.get(pc);
		return new Command(command);
	}
	
	/**
	 * Increment the program counter.
	 */
	public void incrementPc() {
		pc = (pc >= memory.size()-1)?0:pc+1;
	}
	
	/**
	 * Add an entry to the error log.
	 * @param errorMsg
	 */
    public void setError(String errorMsg) {
        if (errorMsg.equals("NoError")) return;
        this.errorLog.add(errorMsg);
        this.setChanged();
        this.notifyObservers();      
    }
	
	/**
	 * @param name the identifier for a specific sensor
	 * @return true if the robot has the specified sensor
	 */
    public boolean hasSensor(String name) {
        return sensors.contains(name);
    }
	
	/**
	 * @return the name of the robot
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return the cargo of the robot
	 */
	public Resource getCargo()
    {
        return cargo;
    }
	
	/**
	 * Set the cargo of the robot.
	 * @param cargo
	 */
    public void setCargo(Resource cargo)
    {
        this.cargo = cargo;
        this.setChanged();
        this.notifyObservers();
    }
	
	/**
	 * Increase the amount of the robot's cargo.
	 * @param load the value the cargo should be increased by
	 */
    public void addCargo(int load) {
    	if (cargo != null) {
    		this.cargo.increaseAmount(load);
    		this.setChanged();
    		this.notifyObservers();
    	}
    }
	
	/**
	 * Decrease the amount of the robot's cargo
	 * @param load the amount the cargo should be reduced by
	 * @return the actual amount the cargo was reduced by 
	 */
    public int removeCargo(int load) {       
        this.setChanged();
        this.notifyObservers();
        return  this.cargo.takeAmount(load);
    }

	/**
	 * @return the maximum amount of cargo this robot can carry
	 */
	public int getMaxCapacity() {
		return this.maxCapacity;
	}

	/**
	 * @return the cost for producing a new robot of this type.
	 */
	public abstract List<Resource> getCosts();
	
	/**
	 * @return the value, which the activity-counter of the 
	 * robot-type needs to reach in order for the next command to be processed.
	 */
	public abstract int getCriticalActivity();
	
}
