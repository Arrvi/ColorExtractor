package eu.arrvi.cextr.beans;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * Container for image storing.
 */
public class ImageBean extends AbstractBean implements Serializable {
	public final static int NOT_LOADED = 0;
	public final static int LOADING = 1;
	public final static int LOADED = 2;
	public final static int ERROR_NOT_AN_IMAGE = -1;
	
	private BufferedImage image;
	private int status = NOT_LOADED;
	
	public ImageBean() {
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
		
		firePropertyChange("image", oldImage, image);
	}

	public int getStatus() {
		return status;
	}

	public synchronized void setStatus(int status) {
		int oldValue = this.status;
		this.status = status;
		firePropertyChange("status", 
				new Integer(oldValue), 
				new Integer(status)
		);
	}
	
	
}
