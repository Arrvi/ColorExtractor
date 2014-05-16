package eu.arrvi.cextr.imagepane;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * ImagePane displays given image stretched to component's size preserving aspect ratio and centered.
 * 
 * @author Kris
 */
public class ImagePane extends JPanel {
	private BufferedImage image;
	
	/**
	 * Creates ImagePane with no image. Renders as JPanel
	 */
	public ImagePane() {
		super();
	}
	
	/**
	 * Creates new ImagePane with specified image.
	 * 
	 * @param im Image to display
	 */
	public ImagePane(BufferedImage im) {
		super();
		image = im;
	}
	
	/**
	 * Overrides image to display. If null, component will render as JPanel 
	 * 
	 * @param im Image to override with
	 */
	public void setImage(BufferedImage im) {
		image = im;
	}
	
	/**
	 * Get displayed image.
	 * 
	 * @return Displayed image
	 */
	public BufferedImage getImage() {
		return image;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if ( image == null ) {
			super.paintComponent(g);
			return;
		}
		
		Dimension size = getSize();
		int iw = image.getWidth();
		int ih = image.getHeight();
		
		if ( iw > size.width ) {
			ih *= (double)size.width/iw;
			iw = size.width;
		}
		if ( ih > size.height ) {
			iw *= (double)size.height/ih;
			ih = size.height;
		}
		
		int ix=0;
		int iy=0;
		
		if ( iw < size.width ) {
			ix = (size.width-iw)/2;
		}
		if ( ih < size.height ) {
			iy = (size.height-ih)/2;
		}
		
		g.drawImage(image, ix, iy, iw, ih, this);
	}
}
