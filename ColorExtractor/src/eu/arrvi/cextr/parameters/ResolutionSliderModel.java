package eu.arrvi.cextr.parameters;
import javax.swing.DefaultBoundedRangeModel;


public class ResolutionSliderModel extends DefaultBoundedRangeModel {
	private final static int ABSOLUTE_MIN=10;
	private final static int DEFAULT_MAX=1920;
	private final static int DEFAULT = 300;
	
	public ResolutionSliderModel() {
		super();
		setMinimum(ABSOLUTE_MIN);
		setMaximum(DEFAULT_MAX);
		setValue(DEFAULT);
	}
}
