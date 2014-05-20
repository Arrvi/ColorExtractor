package eu.arrvi.cextr.parameters;

import java.util.Hashtable;

import javax.swing.BoundedRangeModel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ResolutionSlider extends JSlider implements ChangeListener {
	private final static double DEFAULT_RATIO = 16d/9d;
	private double ratio=0;
	private JLabel middleLabel;
	
	public ResolutionSlider() {
		this(new ResolutionSliderModel());
	}
	
	public ResolutionSlider(BoundedRangeModel model) {
		super(model);
		
		this.setBorder(new EmptyBorder(0, 10, 0, 10));
		this.setPaintLabels(true);
		this.setPaintTicks(true);
		
		this.addChangeListener(this);
		
		this.setMajorTickSpacing((getMaximum()-1)/10);
		
		middleLabel = new JLabel(getResolutionString(getMaximum(), getRatio()));
		
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(
			new Integer(this.getMinimum()), 
			new JLabel(getVerticalResolutionString(this.getMinimum(), this.getRatio()))
		);
		labelTable.put(this.getMaximum()/2, middleLabel);
		labelTable.put(
			new Integer(this.getMaximum()), 
			new JLabel(getVerticalResolutionString(this.getMaximum(), this.getRatio()))
		);
		this.setLabelTable(labelTable);
		
		updateMiddleLabel();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		updateMiddleLabel();
	}

	private static String getResolutionString(int value, double ratio) {
		return value+"x"+(int)((double)value/ratio);
	}
	private static String getVerticalResolutionString(int value, double ratio) {
		return "<html>"+value+"<br>"+(int)((double)value/ratio)+"</html>";
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

	private void updateMiddleLabel() {
		middleLabel.setText(getResolutionString());
	}
}
