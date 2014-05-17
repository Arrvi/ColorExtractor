package eu.arrvi.cextr.parameters;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;

import eu.arrvi.cextr.Controller;

public class ParametersPane extends JPanel {
	private final Controller controller;
	public ParametersPane(Controller contr) {
		super();
		
		controller = contr;
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("Parameters"));
		this.setPreferredSize(new Dimension(200, 0));
		
		JTabbedPane tabs = new JTabbedPane();
		
		JPanel analysis = new JPanel();
		analysis.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		ResolutionSlider resSlider = new ResolutionSlider();
		analysis.add(resSlider);
		tabs.addTab("Analysis", analysis);
		
		JPanel sorting = new JPanel();
		
		tabs.addTab("Sorting", sorting);
		
		this.add(tabs);
	}
}
