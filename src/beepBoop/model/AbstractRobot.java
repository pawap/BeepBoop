package beepBoop.model;

import java.awt.Point;
import java.util.List;
import java.util.Observer;

import beepBoop.controller.RobotTerminalController;

/**
 * All robots should extend this class.
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 */
public abstract class AbstractRobot extends Thing{

	private static final long serialVersionUID = -6676887042773099939L;
	protected int maxCapacity;
	protected List<String> memory;
	protected int pc;
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
	}

	/**
	 * @return the current program of the robot, 1 String per Command
	 */
	public abstract List<String> getMemory();
	
	/**
	 * Set the current program of the robot. 
	 * @param memory 1 String per Command
	 */
	public abstract void setMemory(List<String> memory);
	
	/**
	 * @return the program counter of the robot
	 */
	public abstract int getPc();
	
	/**
	 * Set the program counter of the robot.
	 * @param pc the desired value for the program counter
	 */
	public abstract void setPc(int pc);
	
	/**
	 * @return the robots error log
	 */
	public abstract List<String> getErrorLog();
	
	/**
	 * Set the error log of the robot.
	 * @param errorLog a list of the desired entries
	 */
	public abstract void setErrorLog(List<String> errorLog);
	
	/**
	 * Move the robot to a point in the Level.
	 * @param p target position
	 */
	public abstract void move(Point p);
	
	/**
	 * @return the command currently pointed at by the program counter
	 */
	public abstract Command getCurrentCommand();
	
	/**
	 * Increment the program counter.
	 */
	public abstract void incrementPc();
	
	/**
	 * Add an entry to the error log.
	 * @param errorMsg
	 */
	public abstract void setError(String errorMsg);
	
	/**
	 * @param name the identifier for a specific sensor
	 * @return true if the robot has the specified sensor
	 */
	public abstract boolean hasSensor(String name);
	
	/**
	 * @return the name of the robot
	 */
	public abstract String getName();
	
	/**
	 * @return the cargo of the robot
	 */
	public abstract Resource getCargo();
	
	/**
	 * Set the cargo of the robot.
	 * @param cargo
	 */
	public abstract void setCargo(Resource cargo);
	
	/**
	 * Increase the amount of the robot's cargo.
	 * @param load the value the cargo should be increased by
	 */
	public abstract void addCargo(int load);
	
	/**
	 * Decrease the amount of the robot's cargo
	 * @param load the amount the cargo should be reduced by
	 * @return the actual amount the cargo was reduced by 
	 */
	public abstract int removeCargo(int load);

	/**
	 * @return the maximum amount of cargo this robot can carry
	 */
	public int getMaxCapacity() {
		return this.maxCapacity;
	}

}
