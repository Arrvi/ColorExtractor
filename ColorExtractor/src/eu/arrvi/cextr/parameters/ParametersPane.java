package eu.arrvi.cextr.parameters;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import eu.arrvi.cextr.Controller;
import eu.arrvi.cextr.beans.ImageBean;

public class ParametersPane extends JPanel {
	private final Controller controller;
	public ParametersPane(Controller contr) {
		super();
		
		controller = contr;
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("Parameters"));
		this.setPreferredSize(new Dimension(250, 0));
		
		JTabbedPane tabs = new JTabbedPane();
		
		tabs.addTab("Analysis", createAnalysisPane());
		tabs.addTab("Sorting", createSortingPane());
		
		this.add(tabs, BorderLayout.CENTER);
		
		JButton analyzeButton = new JButton(controller.getAnalyzeAction());
		JPanel buttonContainer = new JPanel(new GridBagLayout());
		buttonContainer.add(analyzeButton);
		this.add(buttonContainer, BorderLayout.SOUTH);
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
		
		return analysis;
	}
	
	private JPanel createSortingPane() {
		JPanel sorting = new JPanel();
		sorting.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		sorting.setLayout(new BoxLayout(sorting, BoxLayout.PAGE_AXIS));
		JSlider slider;
		
		String[] props = controller.getParametersBean().getSortingProperties();
		for (String property : props) {
			sorting.add(new JLabel(property));
			slider = new JSlider(new BeanControlledModel(property));
			sorting.add(slider);
		}
		
		return sorting;
	}
	
	private class BeanControlledResolutionModel extends ResolutionSliderModel implements PropertyChangeListener, ChangeListener {

		public BeanControlledResolutionModel() {
			controller.getImageBean().addPropertyChangeListener("status", this);
			this.addChangeListener(this);
		}
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if ((int)evt.getNewValue() == ImageBean.LOADED) {
				setMaximum(controller.getImageBean().getImage().getWidth());
				setRatio((double)controller.getImageBean().getImage().getWidth()/controller.getImageBean().getImage().getHeight());
			}
			else if ((int)evt.getNewValue() == ImageBean.NOT_LOADED) {
				setMaximum(DEFAULT_MAX);
				setRatio(DEFAULT_RATIO);
			}
		}
		
		public void setValue(int n) {
			super.setValue(n);
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			if ( !getValueIsAdjusting() ) {
				controller.getParametersBean().setResolution(getFakeValue());
			}
		}
		
		
		
	}
	
	private class BeanControlledModel extends DefaultBoundedRangeModel implements PropertyChangeListener, ChangeListener {
		private final static int DEFAULT = 50;
		
		private String property; 
		
		public BeanControlledModel(String property) {
			this.property = property;
			controller.getParametersBean().setProperty(property, DEFAULT);
//			controller.getParametersBean().addPropertyChangeListener(property, this);
			this.addChangeListener(this);
			
			setValue(DEFAULT);
		}
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			
			if ( ((Number)evt.getNewValue()).intValue() != getValue() ) {
				setValue(((Number)evt.getNewValue()).intValue());
			}
		}
		
		public void setValue(int n) {
			super.setValue(n);
			controller.getParametersBean();
		}

		public void stateChanged(ChangeEvent e) {
			if ( !getValueIsAdjusting() ) {
				controller.getParametersBean().setProperty(property, getValue());
			}
		}
	}
}
