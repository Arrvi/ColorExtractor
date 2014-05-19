package eu.arrvi.cextr.beans;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * Parameters for analyzer algorithm. Supposed to be set 
 * in {@link eu.arrvi.cextr.parameters.ParametersPane}.  
 * 
 * @author Arrvi
 *
 */
public class ParametersBean implements Serializable {
	// Analysis
	
	/**
	 * (Analysis param)
	 * Width of destination image size.
	 */
	private int resolution;
	
	/**
	 * (Analysis param)
	 * Tolerance between pixels that should be treated as color patch
	 */
	private double tolerance;
	
	/**
	 * (Analysis param)
	 * Amount of blur to be applied before analysis
	 */
	private int blurRadius;
	
	// Sorting
	private double intensity;
	private double brightness;
	private double saturation;
	private double difference;
	private double modelSnap;
	
	private PropertyChangeSupport pcs;
	
	public ParametersBean() {
		pcs = new PropertyChangeSupport(this);
	}
	
	
	/**
	 * Return resolution of analyzed image. In order to decrease
	 * analysis time image should be scaled down. As original aspect
	 * ratio is preserved only width of destination size is stored.
	 * 
	 * @return width of destination image size
	 */
	public int getResolution() {
		return resolution;
	}
	/**
	 * Sets the width of destination image size
	 * 
	 * @param resolution new width of destination image size 
	 * @see {@link #getResolution()}
	 */
	public synchronized void setResolution(int resolution) {
		int oldValue = this.resolution;
		this.resolution = resolution;
		
		pcs.firePropertyChange("resolution", 
				new Integer(oldValue),
				new Integer(resolution)
		);
	}
	
	/**
	 * @return the tolerance
	 */
	public double getTolerance() {
		return tolerance;
	}
	/**
	 * @param tolerance the tolerance to set
	 */
	public synchronized void setTolerance(double tolerance) {
		double oldValue = this.tolerance;
		this.tolerance = tolerance;
		
		pcs.firePropertyChange("tolerance", 
				new Double(oldValue), 
				new Double(tolerance)
		);
	}
	
	/**
	 * @return the blur radius
	 */
	public int getBlurRadius() {
		return blurRadius;
	}
	/**
	 * @param blurRadius the blur radius to set
	 */
	public synchronized void setBlurRadius(int blurRadius) {
		int oldValue = this.blurRadius;
		this.blurRadius = blurRadius;
		
		pcs.firePropertyChange("blurRadius", 
				new Integer(oldValue), 
				new Integer(blurRadius)
		);
	}

	/**
	 * @return the intensity
	 */
	public double getIntensity() {
		return intensity;
	}
	/**
	 * @param intensity the intensity to set
	 */
	public synchronized void setIntensity(double intensity) {
		double oldValue = this.intensity;
		this.intensity = intensity;
		
		pcs.firePropertyChange("intensity", 
				new Double(oldValue), 
				new Double(intensity)
		);
	}
	
	/**
	 * @return the brightness
	 */
	public double getBrightness() {
		return brightness;
	}
	/**
	 * @param brightness the brightness to set
	 */
	public synchronized void setBrightness(double brightness) {
		this.brightness = brightness;
	}
	/**
	 * @return the saturation
	 */
	public double getSaturation() {
		return saturation;
	}
	/**
	 * @param saturation the saturation to set
	 */
	public synchronized void setSaturation(double saturation) {
		this.saturation = saturation;
	}
	/**
	 * @return the difference
	 */
	public double getDifference() {
		return difference;
	}
	/**
	 * @param difference the difference to set
	 */
	public synchronized void setDifference(double difference) {
		this.difference = difference;
	}
	/**
	 * @return the modelSnap
	 */
	public double getModelSnap() {
		return modelSnap;
	}
	/**
	 * @param modelSnap the modelSnap to set
	 */
	public synchronized void setModelSnap(double modelSnap) {
		this.modelSnap = modelSnap;
	}
}
