package eu.arrvi.cextr.parameters;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;

import eu.arrvi.cextr.Controller;
import eu.arrvi.cextr.beans.ImageBean;

public class ParametersPane extends JPanel {
	private final Controller controller;
	public ParametersPane(Controller contr) {
		super();
		
		controller = contr;
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("Parameters"));
		this.setPreferredSize(new Dimension(200, 0));
		
		JTabbedPane tabs = new JTabbedPane();
		
		tabs.addTab("Analysis", createAnalysisPane());
		tabs.addTab("Sorting", createSortingPane());
		
		this.add(tabs);
	}
	
	private JPanel createAnalysisPane() {
		JPanel analysis = new JPanel();
		analysis.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		analysis.setLayout(new BoxLayout(analysis, BoxLayout.PAGE_AXIS));
		
		analysis.add(new JLabel("Resolution"));
		ResolutionSlider resSlider = new ResolutionSlider(new BeanControlledResolutionModel());
		analysis.add(resSlider);
		
		JSlider slider;
		
		analysis.add(new JLabel("Tolerance"));
		slider = new JSlider(new BeanControlledModel("tolerance"));
		analysis.add(slider);
		
		analysis.add(new JLabel("Blur radius"));
		slider = new JSlider(new BeanControlledModel("blurRadius"));
		analysis.add(slider);
		
		return analysis;
	}
	
	private JPanel createSortingPane() {
		JPanel sorting = new JPanel();
		
		return sorting;
	}
	
	private class BeanControlledResolutionModel extends ResolutionSliderModel implements PropertyChangeListener {

		public BeanControlledResolutionModel() {
			controller.getImageBean().addPropertyChangeListener("image", this);
		}
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (controller.getImageBean().getStatus() == ImageBean.LOADED) {
				setMaximum(((BufferedImage)evt.getNewValue()).getWidth());
			}
		}
		
	}
	
	private class BeanControlledModel extends DefaultBoundedRangeModel implements PropertyChangeListener {
		
		public BeanControlledModel(String property) {
			controller.getParametersBean().addPropertyChangeListener(property, this);
		}
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if ( (int)evt.getNewValue() != getValue() ) {
				setValue((int)evt.getNewValue());
			}
		}
		
	}
}
