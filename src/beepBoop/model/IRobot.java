package beepBoop.model;

import java.awt.Point;
import java.util.List;

/**
 * All robots should implement this interface. 
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 */
public interface IRobot {

	/**
	 * @return the current program of the robot, 1 String per Command
	 */
	public List<String> getMemory();
	
	/**
	 * Set the current program of the robot. 
	 * @param memory 1 String per Command
	 */
	public void setMemory(List<String> memory);
	
	/**
	 * @return the program counter of the robot
	 */
	public int getPc();
	
	/**
	 * Set the program counter of the robot.
	 * @param pc the desired value for the program counter
	 */
	public void setPc(int pc);
	
	/**
	 * @return the robots error log
	 */
	public List<String> getErrorLog();
	
	/**
	 * Set the error log of the robot.
	 * @param errorLog a list of the desired entries
	 */
	public void setErrorLog(List<String> errorLog);
	
	/**
	 * Move the robot to a point in the Level.
	 * @param p target position
	 */
	public void move(Point p);
	
	/**
	 * @return the command currently pointed at by the program counter
	 */
	public Command getCurrentCommand();
	
	/**
	 * Increment the program counter.
	 */
	public void incrementPc();
	
	/**
	 * Add an entry to the error log.
	 * @param errorMsg
	 */
	public void setError(String errorMsg);
	
	/**
	 * @param name the identifier for a specific sensor
	 * @return true if the robot has the specified sensor
	 */
	public boolean hasSensor(String name);
	
	/**
	 * @return the name of the robot
	 */
	public String getName();
}
