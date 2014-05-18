package eu.arrvi.cextr.beans;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class ImageBean implements Serializable {
	private BufferedImage image;
	private PropertyChangeSupport pcs;
	
	public ImageBean() {
		pcs = new PropertyChangeSupport(this);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		BufferedImage oldImage = this.image;
		this.image = image;
		pcs.firePropertyChange("image", oldImage, image);
	}
	
	
}
