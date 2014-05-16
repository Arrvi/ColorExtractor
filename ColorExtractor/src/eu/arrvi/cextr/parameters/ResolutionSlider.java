package eu.arrvi.cextr.parameters;

import java.util.Hashtable;

import javax.swing.BoundedRangeModel;
import javax.swing.JLabel;

import eu.arrvi.cextr.common.LabeledSlider;

public class ResolutionSlider extends LabeledSlider {
	private final static double DEFAULT_RATIO = 16d/9d;
	private double ratio=0;
	
	public ResolutionSlider() {
		this(new ResolutionSliderModel());
	}
	
	public ResolutionSlider(BoundedRangeModel model) {
		super("Resolution", model);
		
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(
			new Integer(this.getMinimum()), 
			new JLabel(getResolutionString(this.getMinimum(), this.getRatio()))
		);
		labelTable.put(
			new Integer(this.getMaximum()), 
			new JLabel(getResolutionString(this.getMaximum(), this.getRatio()))
		);
		this.setLabelTable(labelTable);
	}
	
	private static String getResolutionString(int value, double ratio) {
		return value+"x"+(int)((double)value/ratio);
	}
	
	private String getResolutionString() {
		return getResolutionString(this.getValue(), this.getRatio());
	}

	private double getRatio() {
		if ( ratio == 0 ) 
			return DEFAULT_RATIO;
		else
			return ratio;
	}
}
