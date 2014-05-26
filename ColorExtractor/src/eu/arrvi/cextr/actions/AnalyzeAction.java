package eu.arrvi.cextr.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.SwingWorker;

import eu.arrvi.cextr.Controller;
import eu.arrvi.cextr.beans.ImageBean;
import eu.arrvi.cextr.common.ColorPatch;

public class AnalyzeAction extends AbstractAction implements PropertyChangeListener {
	private final Controller controller;

	public AnalyzeAction(Controller contr) {
		controller = contr;
		
		controller.getImageBean().addPropertyChangeListener("status", this);
		
		putValue(MNEMONIC_KEY, KeyEvent.VK_A);
		putValue(NAME, "Analyze");
		setEnabled(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		controller.getImageBean().setStatus(ImageBean.ANALYZING);
		
		PatchFinder worker = new PatchFinder(controller.getImageBean().getImage(), 40, 40);
		worker.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ( evt.getPropertyName().equals("progress") ) {
					controller.setStatus(
						"Analyzing: "+(double)Math.round((double)evt.getNewValue()*10000)/100
					);
				}
				controller.getMainJFrame().repaint();
			}
		});
		worker.execute();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		setEnabled( evt.getNewValue().equals(ImageBean.LOADED) );
	}
}
