package beepBoop;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * Loads and holds images for Things
 * @author ptp18-d06(Pawel Rasch, Tim Runge)
 *
 */
final public class ImageFactory {
    private HashMap<String, Image> images;
    private static ImageFactory instance = null;
    
	/**
	 * Grants access to the singleton instance of ImageFactory. Instantiates it if necessary.
	 * @return the singleton instance of ImageFactory
	 */
	public static ImageFactory getInstance() {
	      if(instance == null) {
	         instance = new ImageFactory();
	      }
	      return instance;
	   }
    
	//private constructor defeats instantiation
    private ImageFactory() {
    	images = new HashMap<String, Image>();
    }
    
    public Image getImage(String name) throws IOException{
    	System.out.println(name);
    	String lowName = name.toLowerCase();
    	Image image = images.get(lowName);
    	if (image == null) {
    		image = ImageIO.read(new File("assets" + File.separator + lowName + ".bmp"));
    		images.put(lowName, image);
    	}
    	return  image;
    }
    
}
