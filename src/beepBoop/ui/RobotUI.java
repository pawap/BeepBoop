package beepBoop.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import beepBoop.model.Robot;

public class RobotUI extends JPanel implements Observer
{
    private Robot robot;
    private JLabel robotName;
    private JLabel robotCargo;
    private JTextArea robotErrorLog;
    
    public RobotUI(Robot robot)
    {
        super();
        this.robot = robot;
        
        robot.addObserver(this);
        this.setLayout(new GridBagLayout());
        
        // Add RobotData
        robotName = new JLabel(robot.toString());
        GridBagConstraints c = new GridBagConstraints();        
        c.weighty = 0.1;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.WEST;
        
        this.add(robotName,c);
        String cargoString = (robot.getCargo() != null)?robot.getCargo().getName()+": "+robot.getCargo().getAmount():"No Cargo!";
        robotCargo = new JLabel(cargoString);
        c.gridy = 1;
        this.add(robotCargo,c);
        
        JLabel errorLogLabel = new JLabel("ErrorLog:");
        c.gridy = 2;
        this.add(errorLogLabel,c);
        
        robotErrorLog = new JTextArea(20,10);
        for (String str: robot.getErrorLog()) {
            robotErrorLog.append(str+"\n"); 
        }
        JScrollPane errorScrollPane = new JScrollPane(robotErrorLog);
        c.gridy = 3;
        this.add(errorScrollPane,c);       
        
    }

    public Robot getRobot()
    {
        return robot;
    }

    public void setRobot(Robot robot)
    {
        this.robot.deleteObserver(this);
        this.robot = robot;
        robot.addObserver(this);
        this.robotName.setText(robot.toString());
        String cargoString = (robot.getCargo() != null)?robot.getCargo().getName()+": "+robot.getCargo().getAmount():"No Cargo!";
        this.robotCargo.setText(cargoString);
        this.robotErrorLog.setText("");
        for (String str: robot.getErrorLog()) {
            robotErrorLog.append(str+"\n"); 
        }
        this.repaint();
        
    }

    @Override
    public void update(Observable o, Object arg)
    {
        setRobot((Robot) o);
        
    }
    
    
    
}
