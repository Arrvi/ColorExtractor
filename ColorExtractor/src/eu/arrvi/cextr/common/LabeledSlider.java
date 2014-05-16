package eu.arrvi.cextr.common;

import java.awt.Font;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeListener;
import java.util.Dictionary;

import javax.swing.BoundedRangeModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

public class LabeledSlider extends JPanel {
	private JLabel label;
	private JSlider slider;
	
	public LabeledSlider() {
		this("", new DefaultBoundedRangeModel());
	}
	public LabeledSlider(String title, BoundedRangeModel model) {
//		super();
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		label = new JLabel(title);
		this.add(label);
		
		slider = new JSlider(model);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		this.add(slider);
	}
	
	
	// Delegated methods

	public Font getFont() {
		if (label == null)
			return super.getFont();
		return label.getFont();
	}

	public Icon getIcon() {
		return label.getIcon();
	}

	public int getIconTextGap() {
		return label.getIconTextGap();
	}

	public String getText() {
		return label.getText();
	}

	public void setFont(Font font) {
		if ( label == null )
			super.setFont(font);
		else
			label.setFont(font);
	}

	public void setText(String text) {
		label.setText(text);
	}

	public void addChangeListener(ChangeListener l) {
		slider.addChangeListener(l);
	}

	public void firePropertyChange(String propertyName, boolean oldValue,
			boolean newValue) {
		if ( slider == null )
			super.firePropertyChange(propertyName, oldValue, newValue);
		else
			slider.firePropertyChange(propertyName, oldValue, newValue);
	}

	public void firePropertyChange(String propertyName, byte oldValue,
			byte newValue) {
		if ( slider == null )
			super.firePropertyChange(propertyName, oldValue, newValue);
		else
			slider.firePropertyChange(propertyName, oldValue, newValue);
	}

	public void firePropertyChange(String propertyName, char oldValue,
			char newValue) {
		if ( slider == null )
			super.firePropertyChange(propertyName, oldValue, newValue);
		else
			slider.firePropertyChange(propertyName, oldValue, newValue);
	}

	public void firePropertyChange(String propertyName, double oldValue,
			double newValue) {
		if ( slider == null )
			super.firePropertyChange(propertyName, oldValue, newValue);
		else
			slider.firePropertyChange(propertyName, oldValue, newValue);
	}

	public void firePropertyChange(String propertyName, float oldValue,
			float newValue) {
		if ( slider == null )
			super.firePropertyChange(propertyName, oldValue, newValue);
		else
			slider.firePropertyChange(propertyName, oldValue, newValue);
	}

	public void firePropertyChange(String propertyName, int oldValue,
			int newValue) {
		if ( slider == null )
			super.firePropertyChange(propertyName, oldValue, newValue);
		else
			slider.firePropertyChange(propertyName, oldValue, newValue);
	}

	public void firePropertyChange(String propertyName, long oldValue,
			long newValue) {
		if ( slider == null )
			super.firePropertyChange(propertyName, oldValue, newValue);
		else
			slider.firePropertyChange(propertyName, oldValue, newValue);
	}

	public void firePropertyChange(String propertyName, short oldValue,
			short newValue) {
		if ( slider == null )
			super.firePropertyChange(propertyName, oldValue, newValue);
		else
			slider.firePropertyChange(propertyName, oldValue, newValue);
	}

	public int getExtent() {
		return slider.getExtent();
	}

	public FocusListener[] getFocusListeners() {
		return slider.getFocusListeners();
	}

	public int getMajorTickSpacing() {
		return slider.getMajorTickSpacing();
	}

	public int getMaximum() {
		return slider.getMaximum();
	}

	public int getMinimum() {
		return slider.getMinimum();
	}

	public int getMinorTickSpacing() {
		return slider.getMinorTickSpacing();
	}

	public BoundedRangeModel getModel() {
		return slider.getModel();
	}

	public String getName() {
		return slider.getName();
	}

	public boolean getPaintLabels() {
		return slider.getPaintLabels();
	}

	public boolean getPaintTicks() {
		return slider.getPaintTicks();
	}

	public boolean getPaintTrack() {
		return slider.getPaintTrack();
	}

	public PropertyChangeListener[] getPropertyChangeListeners() {
		return slider.getPropertyChangeListeners();
	}

	public PropertyChangeListener[] getPropertyChangeListeners(
			String propertyName) {
		return slider.getPropertyChangeListeners(propertyName);
	}

	public boolean getSnapToTicks() {
		return slider.getSnapToTicks();
	}

	public int getValue() {
		return slider.getValue();
	}

	public boolean getValueIsAdjusting() {
		return slider.getValueIsAdjusting();
	}

	public boolean isEnabled() {
		return slider.isEnabled();
	}

	public boolean isFocusOwner() {
		return slider.isFocusOwner();
	}

	public boolean isFocusable() {
		return slider.isFocusable();
	}

	public void requestFocus() {
		slider.requestFocus();
	}

	public boolean requestFocus(boolean temporary) {
		return slider.requestFocus(temporary);
	}

	public boolean requestFocusInWindow() {
		return slider.requestFocusInWindow();
	}

	public void setEnabled(boolean enabled) {
		slider.setEnabled(enabled);
	}

	public void setExtent(int extent) {
		slider.setExtent(extent);
	}

	public void setFocusable(boolean focusable) {
		slider.setFocusable(focusable);
	}

	public void setMajorTickSpacing(int n) {
		slider.setMajorTickSpacing(n);
	}

	public void setMaximum(int maximum) {
		slider.setMaximum(maximum);
	}

	public void setMinimum(int minimum) {
		slider.setMinimum(minimum);
	}

	public void setMinorTickSpacing(int n) {
		slider.setMinorTickSpacing(n);
	}

	public void setModel(BoundedRangeModel newModel) {
		slider.setModel(newModel);
	}

	public void setName(String name) {
		slider.setName(name);
	}

	public void setPaintLabels(boolean b) {
		slider.setPaintLabels(b);
	}

	public void setPaintTicks(boolean b) {
		slider.setPaintTicks(b);
	}

	public void setPaintTrack(boolean b) {
		slider.setPaintTrack(b);
	}

	public void setSnapToTicks(boolean b) {
		slider.setSnapToTicks(b);
	}

	public void setValue(int n) {
		slider.setValue(n);
	}

	public void setValueIsAdjusting(boolean b) {
		slider.setValueIsAdjusting(b);
	}

	public void transferFocus() {
		slider.transferFocus();
	}

	public void transferFocusBackward() {
		slider.transferFocusBackward();
	}

	public void transferFocusDownCycle() {
		slider.transferFocusDownCycle();
	}

	public void transferFocusUpCycle() {
		slider.transferFocusUpCycle();
	}
	public Dictionary getLabelTable() {
		return slider.getLabelTable();
	}
	public void setLabelTable(Dictionary arg0) {
		slider.setLabelTable(arg0);
	}
}
