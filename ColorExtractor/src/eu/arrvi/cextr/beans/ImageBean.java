package eu.arrvi.cextr.beans;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * Container for image storing.
 */
public class ImageBean implements Serializable {
	private BufferedImage image;
	private PropertyChangeSupport pcs;
	
	public ImageBean() {
		pcs = new PropertyChangeSupport(this);
	}

	/**
	 * Returns full resolution preview of loaded image. 
	 * 
	 * @return full size image<br><tt>null</tt> if no image loaded
	 */
	public BufferedImage getImage() {
		return image;
	}
	
	/**
	 * Sets new loaded image
	 * 
	 * @param image
	 */
	public synchronized void setImage(BufferedImage image) {
		BufferedImage oldImage = this.image;
		this.image = image;
		pcs.firePropertyChange("image", oldImage, image);
	}
	
	
}
