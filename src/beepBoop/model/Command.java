package beepBoop.model;

import java.awt.Point;

public class Command
{
    
    private String type;
    private String[] args;

    public Command(String commandStr)
    {
       if (commandStr.isEmpty()) {
           throw new IllegalArgumentException("empty string");
       }
       String[] strArray = commandStr.split(" ");
       this.type = strArray[0]; 
       this.args = new String[strArray.length-1];   
       if (strArray.length > 1) {
           for (int i = 1; i < strArray.length; i++) {
               this.args[i-1] = strArray[i];
           }
       }   
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String[] getArgs()
    {
        return args;
    }

    public void setArgs(String[] args)
    {
        this.args = args;
    }

    public static Point getPointFromDirection(Point position, String direction)
    {
        switch(direction) {
            case "L": return new Point(position.x-1,position.y);
            case "R": return new Point(position.x+1,position.y);
            case "U": return new Point(position.x,position.y-1);
            case "D": return new Point(position.x,position.y+1);
            default: return position;          
        }
    }



    

}
