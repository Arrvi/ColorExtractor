package eu.arrvi.cextr.parameters;

import java.util.Hashtable;

import javax.swing.BoundedRangeModel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ResolutionSlider extends JSlider implements ChangeListener {
	private JLabel middleLabel;
	private JLabel minLabel;
	private JLabel maxLabel;
	
	public ResolutionSlider() {
		this(new ResolutionSliderModel());
	}
	
	public ResolutionSlider(BoundedRangeModel model) {
		super(model);
		
		this.setPaintLabels(true);
		
		this.addChangeListener(this);
		
		minLabel = new JLabel(getVerticalResolutionString(getFakeMaximum(), getRatio()));
		middleLabel = new JLabel(getResolutionString(getFakeMaximum(), getRatio()));
		maxLabel = new JLabel(getVerticalResolutionString(getFakeMaximum(), getRatio()));
		
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		
		labelTable.put(
			this.getMinimum(), 
			minLabel
		);
		labelTable.put(
			this.getMaximum()/2, 
			middleLabel
		);
		labelTable.put(
			this.getMaximum(), 
			maxLabel
		);
		
		this.setLabelTable(labelTable);
		
		updateLabels();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		updateLabels();
	}

	private void updateLabels() {
		minLabel.setText(getVerticalResolutionString(getFakeMinimum(), getRatio()));
		middleLabel.setText(getResolutionString());
		maxLabel.setText(getVerticalResolutionString(getFakeMaximum(), getRatio()));
		repaint();
	}

	private static String getResolutionString(int value, double ratio) {
		return value+"x"+(int)((double)value/ratio);
	}
	private static String getVerticalResolutionString(int value, double ratio) {
		return "<html>"+value+"<br>"+(int)((double)value/ratio)+"</html>";
	}
	
	private String getResolutionString() {
		return getResolutionString(this.getFakeValue(), this.getRatio());
	}

	public int getFakeValue() {
		return ((ResolutionSliderModel)getModel()).getFakeValue();
	}

	public int getFakeMinimum() {
		return ((ResolutionSliderModel)getModel()).getFakeMinimum();
	}
	
	public int getFakeMaximum() {
		return ((ResolutionSliderModel)getModel()).getFakeMaximum();
	}

	public double getRatio() {
		return ((ResolutionSliderModel)getModel()).getRatio();
	}
}
