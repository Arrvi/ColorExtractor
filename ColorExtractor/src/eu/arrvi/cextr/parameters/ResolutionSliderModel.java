package eu.arrvi.cextr.parameters;
import javax.swing.DefaultBoundedRangeModel;


public class ResolutionSliderModel extends DefaultBoundedRangeModel {
	protected final static int DEFAULT_MAX=1920;
	protected final static int DEFAULT = 50;
	protected final static double DEFAULT_RATIO = 16d/9d; 
	
	private double ratio = DEFAULT_RATIO;
	private int fakeMax = DEFAULT_MAX;
	
	public ResolutionSliderModel() {
		super();
		super.setMinimum(10);
		super.setMaximum(100);
		setMaximum(DEFAULT_MAX);
		setValue(DEFAULT);
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
		fireStateChanged();
	}
	
	public void setMaximum(int max) {
		fakeMax = max;
		fireStateChanged();
	}
	
	public int getFakeMinimum() {
		return fakeMax/10;
	}
	
	public int getFakeMaximum() {
		return fakeMax;
	}
	
	public int getFakeValue() {
		return getValue()*getFakeMaximum()/100;
	}
}
