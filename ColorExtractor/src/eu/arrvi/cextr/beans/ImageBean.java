package eu.arrvi.cextr.beans;

import java.awt.image.BufferedImage;
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
	
	/**
	 * Returns status of image as integer to be compared with class constants:
	 * <ul>
	 * 	<li><tt>0 / NOT_LOADED</tt> - image is not loaded. This is initial state and is set on close image action.
	 * 	<li><tt>1 / LOADING</tt> - image is being loaded by swing worker. It is being done in separate thread. 
	 * 	This state should not be active for more than couple of seconds. Loading info should be displayed.
	 * 	<li><tt>2 / LOADED</tt> - image is loaded as buffered image in bean and should be displayed.
	 * 	<li><tt>-1/ ERROR_NOT_AN_IMAGE</tt> - error occurred while interpreting file as image. 
	 * 	Appropriate error message should be shown and later it should be treated as <tt>NOT_LOADED</tt> state.
	 * </ul>
	 * 
	 * @return status of an image
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * Sets status code. For codes with description see {@link AbstractBean#getStatus()}
	 * 
	 * @param status status code to be set
	 */
	public synchronized void setStatus(int status) {
		int oldValue = this.status;
		this.status = status;
		firePropertyChange("status", 
				new Integer(oldValue), 
				new Integer(status)
		);
	}
	
	
}
