package eu.arrvi.cextr.beans;

import java.io.Serializable;

/**
 * Parameters for analyzer algorithm. Supposed to be set 
 * in {@link eu.arrvi.cextr.parameters.ParametersPane}.  
 * 
 * @author Arrvi
 *
 */
public class ParametersBean extends AbstractBean implements Serializable {
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
	private double temperature;
	private double brightness;
	private double saturation;
	private double difference;
	private double modelSnap;
	private double patchSize;
	
	
	public ParametersBean() {
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
		
		firePropertyChange("resolution", 
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
		
		firePropertyChange("tolerance", 
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
		
		firePropertyChange("blurRadius", 
				new Integer(oldValue), 
				new Integer(blurRadius)
		);
	}

	/**
	 * @return the intensity
	 */
	public double getTemperature() {
		return temperature;
	}
	/**
	 * @param temperature the intensity to set
	 */
	public synchronized void setTemperature(double temperature) {
		double oldValue = this.temperature;
		this.temperature = temperature;
		
		firePropertyChange("temperature", 
				new Double(oldValue), 
				new Double(temperature)
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
		double oldValue = this.brightness;
		this.brightness = brightness;
		
		firePropertyChange("brightness", 
				new Double(oldValue), 
				new Double(brightness)
		);
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
		double oldValue = this.saturation;
		this.saturation = saturation;
		
		firePropertyChange("saturation", 
				new Double(oldValue), 
				new Double(saturation)
		);
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
		double oldValue = this.difference;
		this.difference = difference;
		
		firePropertyChange("difference", 
				new Double(oldValue), 
				new Double(difference)
		);
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
		double oldValue = this.modelSnap;
		this.modelSnap = modelSnap;
		
		firePropertyChange("modelSnap", 
				new Double(oldValue), 
				new Double(modelSnap)
		);
	}
	
	/**
	 * @return the patchSize
	 */
	public double getPatchSize() {
		return patchSize;
	}
	/**
	 * @param patchSize the patchSize to set
	 */
	public void setPatchSize(double patchSize) {
		double oldValue = this.patchSize;
		this.patchSize = patchSize;
		
		firePropertyChange("patchSize", 
				new Double(oldValue), 
				new Double(patchSize)
		);
	}


	public void setProperty(String property, int value) {
		switch (property) {
		case "resolution": setResolution(value); break;
		case "tolerance": setTolerance(value/100d); break;
		case "blurRadius": setBlurRadius(value); break;
		case "temperature": setTemperature(value/100d); break;
		case "brightness": setBrightness(value/100d); break;
		case "saturation": setSaturation(value/100d); break;
		case "difference": setDifference(value/100d); break;
		case "modelSnap": setModelSnap(value/100d); break;
		case "patchSize": setPatchSize(value/100d); break;
		default: throw new IllegalArgumentException();
		}
	}


	public String[] getSortingProperties() {
		String[] props = { "temperature", "brightness", "saturation", "difference", /* "modelSnap", */ "patchSize" };
		return props;
	}
	
	@Override
	public String toString() {
		return "Params ["+
				"res "+getResolution()+", tol "+getTolerance()+", blu "+getBlurRadius()+
				", tem "+getTemperature()+", bri "+getBrightness()+", sat "+getSaturation()+
				", dif "+getDifference()+", mds "+getModelSnap()+", pcs "+getPatchSize()+"]";
	}
}
